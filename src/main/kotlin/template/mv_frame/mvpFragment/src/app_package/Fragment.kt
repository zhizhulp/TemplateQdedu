package template.mv_frame.mvpFragment.src.app_package

fun mvpFragmentKt(
        packageName: String,
        viewClass: String,
        presenterClass: String,
        layoutName: String,
        now: String,
        remarks: String,
        activityClass: String,
) = """
package $packageName.ui.fragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ktx.immersionBar
import com.kangraoo.basektlib.ui.BActivity
import com.kangraoo.basektlib.widget.toolsbar.LibToolBarOptions
import com.kangraoo.basektlib.widget.toolsbar.OnLibToolBarListener
import $packageName.R
import $packageName.ui.view.$viewClass
import $packageName.ui.presenter.$presenterClass
import kotlinx.android.synthetic.main.$layoutName.*
import com.kangraoo.basektlib.ui.mvp.BMvpFragment

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $activityClass : BMvpFragment<$viewClass ,$presenterClass>(),$viewClass{

    companion object{

        @JvmStatic
        fun newInstance() = $activityClass()
        
    }

    override fun getLayoutId() = R.layout.$layoutName


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createPresenterInstance(): $presenterClass {
        return $presenterClass()
    }
}
""".trimIndent()