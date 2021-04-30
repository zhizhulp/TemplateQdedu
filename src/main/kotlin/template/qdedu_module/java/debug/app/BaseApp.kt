package template.qdedu_module.java.debug.app


fun baseApp(
        moduleName:String
)="""
    package debug

    import android.content.Context
    import androidx.multidex.MultiDex
    import com.kangraoo.basektlib.app.BaseLibActivityLifecycleCallbacks
    import com.kangraoo.basektlib.app.SApplication
    import com.kangraoo.basektlib.app.SConfiger
    import com.kangraoo.basektlib.app.init.IInit
    import com.qdedu.baselibcommon.app.init.ArouterInit
    import com.qdedu.baselibcommon.app.init.SampleAppInit

    class Base${moduleName}App : SApplication() {
        override fun configer(): SConfiger = SConfiger.build {
            consoleOutwindow = false
        }

        override fun init() {

        }

        override fun appInit(): IInit {
            return SampleAppInit(
                ArouterInit(
                    super.appInit()
                )
            )
        }

        override fun attachBaseContext(base: Context?) {
            super.attachBaseContext(base)
            MultiDex.install(this)
        }

        override fun getActivityLifecycleCallbacks(): MutableList<ActivityLifecycleCallbacks> {
            var list = super.getActivityLifecycleCallbacks()
            list.add(BaseLibActivityLifecycleCallbacks())
            return list
        }
    }
""".trimIndent()