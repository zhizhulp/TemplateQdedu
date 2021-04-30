package template.qdedu_module.out

fun gradle(
        resPrefix:String,
        packageName:String
)="""
    apply from: "../basektlib/common_component_build.gradle"
    apply plugin: 'kotlin-android'

    android {
        resourcePrefix "$resPrefix" //给 Module 内的资源名增加前缀, 避免资源名冲突

        defaultConfig {
            versionCode 1
            versionName "1.0"
        }

        buildTypes {
            release {
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }
        }
    }

    task appTemplate {

        doLast{
            println "Auto Template start"
            def cmdBase = 'java -jar ' + rootDir + File.separator +'android-base'+ File.separator+'basektlib'+ File.separator+ 'autotemplate' + File.separator + 'autotemplate.jar '+ rootDir + ' '  +File.separator +'android-base'+  File.separator+project.name+ File.separator+'src' + File.separator+'main' + ' '+ '$packageName' + ' '+  projectDir +  File.separator + 'autotemplate.properties '+ rootDir + File.separator + 'autorecipe.xml.ftl'+ ' '+ projectDir + File.separator + 'page.xml'+ ' ' + android.resourcePrefix
            def m = '' + rootDir
            executeLibCmd(cmdBase, m)
    //        println cmdBase
            println "Auto Template end"
        }

    }

    task appapi {

        doLast{
            println "Auto API start"
            def cmdBase = 'java -jar ' + rootDir + File.separator +'android-base'+ File.separator+'basektlib'+ File.separator+ 'autoapi' + File.separator + 'autoapi.jar ' + rootDir + ' '  + File.separator+'android-base'+ File.separator+project.name + File.separator+'src'+ File.separator+'main'+ File.separator+'java' + ' '     + '$packageName' + ' '+  projectDir +  File.separator + 'api.properties '+ rootDir + File.separator + 'recipe.xml.ftl'
    //        println cmdBase
            def m = '' + rootDir
            executeLibCmd(cmdBase, m)
            println "Auto API end"
        }

    }

    def executeLibCmd(cmd, m) {
        def cmdResult = cmd.execute(null, new File(m)).text.trim()
        println cmdResult
    }

    dependencies {
        implementation project(path: ':baselib-common')
        implementation project(path: ':base-lib-web')
        implementation rootProject.ext.dependencies.kotlin.kotlin_stdlib
        implementation rootProject.ext.dependencies.androidx.appcompat
        implementation rootProject.ext.dependencies.androidx.constraintlayout
    }
""".trimIndent()