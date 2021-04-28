package template.mv_frame.mvpFragment.src.app_package

fun presenterKt(
        packageName: String,
        viewClass: String,
        now: String,
        remarks: String,
        presenterClass: String
) = """
package $packageName.ui.presenter

import $packageName.ui.view.$viewClass
import com.kangraoo.basektlib.ui.mvp.BasePresenter

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $presenterClass : BasePresenter<$viewClass>(){
   
}
""".trimIndent()