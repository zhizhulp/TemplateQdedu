package template.mv_frame.mvvmFragment.src.app_package

fun mvvmFragmentKt(
        packageName:String,
        layoutName:String,
        viewModelClass:String,
        now:String,
        remarks:String,
        activityClass:String
) ="""
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
import kotlinx.android.synthetic.main.$layoutName.*
import com.kangraoo.basektlib.ui.mvvm.BVMFragment
import $packageName.ui.viewmodel.$viewModelClass

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $activityClass : BVMFragment<$viewModelClass>(){

    companion object{

        @JvmStatic
        fun newInstance() = $activityClass()
        
    }

    override fun getLayoutId() = R.layout.$layoutName


     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

	override fun createVMInstance() = $viewModelClass()

}
""".trimIndent()