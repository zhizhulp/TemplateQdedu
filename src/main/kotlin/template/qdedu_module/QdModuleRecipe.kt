package template.qdedu_module

import com.android.tools.idea.wizard.template.*
import template.qdedu_module.androidManifest.libManifest
import template.qdedu_module.java.debug.app.baseApp
import template.qdedu_module.java.debug.arouter.service.dataCenter
import template.qdedu_module.java.debug.ui.mainActivity
import template.qdedu_module.module.androidManifest.moduleManifest
import template.qdedu_module.out.apiConfig
import template.qdedu_module.out.gradle
import template.qdedu_module.out.mvConfig
import template.qdedu_module.out.pages
import template.qdedu_module.res.layout.mainActivityLayout
import template.qdedu_module.res.values.stringXml
import util.AttachmentStore
import util.firstToUpperCase
import java.io.File


fun RecipeExecutor.apiRecipe(
        moduleData: ModuleTemplateData,
        appName:String
) {
    val manifestOut = moduleData.manifestDir
    val rootDir = moduleData.rootDir
    val resOut = moduleData.resDir

    val packageName = moduleData.packageName
    val lastIndexOf = packageName.lastIndexOf(".")
    val moduleName = (packageName.substring(lastIndexOf+1,packageName.length)).firstToUpperCase()//Application

    val baseAppName = "Base${moduleName}App"
    val appResName = "public_${moduleName.toLowerCase()}_app_name"
    val mainActivityName = "${moduleName}Activity"
    val layoutName = activityToLayout(mainActivityName)
    val resPrefix = "public_${moduleName.toLowerCase()}_"

    saveLibManifest(packageName, this, manifestOut,mainActivityName)
    save(moduleManifest(packageName, baseAppName, mainActivityName, appResName), manifestOut.resolve("module/AndroidManifest.xml"))

    val debugDir = "src/main/java/debug"
    save(baseApp(moduleName), rootDir.resolve("$debugDir/${baseAppName}.kt"))
    save(dataCenter(), rootDir.resolve("${debugDir}/arouter/service/DataCenterServiceImpl.kt"))
    save(mainActivity(packageName, mainActivityName, layoutName), rootDir.resolve("$debugDir/ui/$mainActivityName.kt"))

    saveMainActivityXml(mainActivityName, resOut, layoutName, this)
    saveStringXml(appResName, appName, resOut, this)

    save(apiConfig(), rootDir.resolve("api.properties"))
    save(mvConfig(), rootDir.resolve("autotemplate.properties"))
    saveGradle(resPrefix, packageName, rootDir, this)
    save("", rootDir.resolve("consumer-rules.pro"))
    save(pages(), rootDir.resolve("page.xml"))
    save("", rootDir.resolve("proguard-rules.pro"))

    AttachmentStore.deleteDir(resOut.resolve("drawable-v24").absolutePath)
    AttachmentStore.deleteDir(resOut.resolve("mipmap-anydpi-v26").absolutePath)
    AttachmentStore.deleteDir(resOut.resolve("mipmap-hdpi").absolutePath)
    AttachmentStore.deleteDir(resOut.resolve("mipmap-mdpi").absolutePath)
    AttachmentStore.deleteDir(resOut.resolve("mipmap-xhdpi").absolutePath)
    AttachmentStore.deleteDir(resOut.resolve("mipmap-xxxhdpi").absolutePath)
    AttachmentStore.deleteDir(resOut.resolve("values-night").absolutePath)
    AttachmentStore.delete(resOut.resolve("values/themes.xml").absolutePath)
}

fun saveGradle(resPrefix: String, packageName: PackageName, rootDir: File, recipeExecutor: RecipeExecutor) {
    val originFile = rootDir.resolve("build.gradle")
    val content = gradle(resPrefix, packageName)
    if (originFile.exists()) {
        AttachmentStore.save(originFile.absolutePath, content)
    } else {
        recipeExecutor.save(content, originFile)
    }

}

fun saveStringXml(appResName: String, appName: String, resOut: File, recipeExecutor: RecipeExecutor) {
    val originFile = resOut.resolve("values/strings.xml")
    val content = stringXml(appResName, appName)
    if (originFile.exists()) {
        AttachmentStore.save(originFile.absolutePath, content)
    } else {
        recipeExecutor.save(content, originFile)
    }

}

fun saveMainActivityXml(mainActivityName: String, resOut: File, layoutName: String, recipeExecutor: RecipeExecutor) {
    val originFile = resOut.resolve("layout/$layoutName.xml")
    val content = mainActivityLayout(mainActivityName)
    if (originFile.exists()) {
        AttachmentStore.save(originFile.absolutePath, content)
    } else {
        recipeExecutor.save(content, originFile)
    }
}

fun saveLibManifest(packageName: PackageName, recipeExecutor: RecipeExecutor, manifestOut: File,mainActivityName:String) {
    val originFile = manifestOut.resolve("AndroidManifest.xml")
    val content = libManifest(packageName,mainActivityName)
    if (originFile.exists()) {//不能直接删除
        AttachmentStore.save(originFile.absolutePath, content)
    } else {
        recipeExecutor.save(content, originFile)
    }

}

