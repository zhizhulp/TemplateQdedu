package template.qdedu_module.java.debug.ui


fun mainActivity(
        packageName:String,
        mainActivityName:String,
        layoutName:String
)="""
    package debug.ui

    import android.os.Bundle
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.gyf.immersionbar.ktx.immersionBar
    import com.kangraoo.basektlib.tools.HString
    import com.kangraoo.basektlib.tools.okhttp.HttpPersistentManager
    import com.kangraoo.basektlib.ui.BActivity
    import com.qdedu.baselibcommon.arouter.ServiceProvider
    import com.qdedu.baselibcommon.data.AppHuanJingFactory
    import com.qdedu.baselibcommon.data.model.entity.BModule
    import com.qdedu.baselibcommon.ui.adapter.ModuleAdapter
    import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarListener
    import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarOptions
    import $packageName.R
    import kotlinx.android.synthetic.main.$layoutName.*
    import java.util.*
    import kotlin.collections.HashMap
    import kotlin.collections.set


    class ${mainActivityName}: BActivity() {

        override fun getLayoutId() = R.layout.$layoutName
        var moduleAdapter: ModuleAdapter? = null
        var baseModules: ArrayList<BModule> = ArrayList()

        override fun onViewCreated(savedInstanceState: Bundle?) {
            immersionBar {
                statusBarDarkFont(true)
            }

            val libToolBarOptions = CommonToolBarOptions()
            libToolBarOptions.titleString = "主页"
            libToolBarOptions.setNeedNavigate(false)
            setToolBar(R.id.toolbar, libToolBarOptions, object : CommonToolBarListener() {})
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(visitActivity())
            moduleAdapter = ModuleAdapter()
            recycle.layoutManager = (layoutManager)
            recycle.adapter = (moduleAdapter)
            val map: HashMap<String, String?> = HashMap()
            map["token"] = ServiceProvider.iDataCenterService!!.appToken()
            map["accessToken"] = ServiceProvider.iDataCenterService!!.appToken()
            HttpPersistentManager.instance.setPersistent(
                HString.host(AppHuanJingFactory.appModel.apiDomains),
                map
            )
            moduleAdapter!!.setNewInstance(baseModules)

        }

    }



""".trimIndent()