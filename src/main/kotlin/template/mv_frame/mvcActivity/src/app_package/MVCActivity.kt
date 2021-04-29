package template.mv_frame.mvcActivity.src.app_package

import java.text.DateFormat
import java.util.*

fun mvcActivityKt(
        className: String,
        packageName: String,
        layoutName: String,
        remark:String
) = """
    package $packageName.ui.activity

    import android.content.Context
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import com.gyf.immersionbar.ktx.immersionBar
    import com.kangraoo.basektlib.ui.BActivity
    import com.kangraoo.basektlib.widget.toolsbar.LibToolBarOptions
    import com.kangraoo.basektlib.widget.toolsbar.OnLibToolBarListener
    import $packageName.R;
    import kotlinx.android.synthetic.main.$layoutName.*
    import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarListener
    import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarOptions
    import com.kangraoo.basektlib.tools.launcher.LibActivityLauncher
    import android.app.Activity

     /**
     * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
     * #$remark#
     */
    class $className : BActivity(){

        companion object{

            fun startFrom(activity: Activity) {
                LibActivityLauncher.instance
                    .launch(activity, $className::class.java)
            }

        }

        override fun getLayoutId() = R.layout.$layoutName


        override fun onViewCreated(savedInstanceState: Bundle?) {
            immersionBar {
                statusBarDarkFont(true)
                statusBarColor(R.color.color_white)
            }
            val libToolBarOptions = CommonToolBarOptions()
            libToolBarOptions.titleString = "$remark"
            libToolBarOptions.setNeedNavigate(true)
            setToolBar(R.id.toolbar, libToolBarOptions, object : CommonToolBarListener(){})
        }

    }
""".trimIndent()