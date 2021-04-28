package template.mv_frame.mvvmActivity.src.app_package

fun viewModelKt(
        packageName:String,
        now:String,
        remarks:String,
        viewModelClass:String
) ="""
package $packageName.ui.viewmodel

import com.kangraoo.basektlib.ui.mvvm.BViewModel
/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $viewModelClass: BViewModel() {

    override fun onCreate() {
        super.onCreate()
    }

}
""".trimIndent()