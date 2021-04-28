package template.mv_frame.mvvmActivity.src.app_package

fun mvvmActivityKt(
        packageName: String,
        viewModelClass: String,
        layoutName: String,
        now: String,
        remarks: String,
        activityClass: String
) ="""
package $packageName.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ktx.immersionBar
import com.kangraoo.basektlib.ui.BActivity
import com.kangraoo.basektlib.widget.toolsbar.LibToolBarOptions
import com.kangraoo.basektlib.widget.toolsbar.OnLibToolBarListener
import $packageName.R
import $packageName.ui.viewmodel.$viewModelClass
import kotlinx.android.synthetic.main.$layoutName.*
import com.kangraoo.basektlib.ui.mvvm.BVMActivity
import com.kangraoo.basektlib.tools.launcher.LibActivityLauncher
import android.app.Activity
import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarListener
import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarOptions

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $activityClass : BVMActivity<$viewModelClass>(){

    companion object{

        fun startFrom(activity: Activity) {
            LibActivityLauncher.instance
                .launch(activity, $activityClass::class.java)
        }

    }

    override fun getLayoutId() = R.layout.$layoutName


    override fun onViewCreated(savedInstanceState: Bundle?) {
        immersionBar {
            statusBarDarkFont(true)
            statusBarColor(R.color.color_white)
        }
        val libToolBarOptions = CommonToolBarOptions()
        libToolBarOptions.titleString = "$remarks"
        libToolBarOptions.setNeedNavigate(true)
        setToolBar(R.id.toolbar, libToolBarOptions, object : CommonToolBarListener(){})

    }

    override fun createVMInstance() = $viewModelClass()

}
""".trimIndent()