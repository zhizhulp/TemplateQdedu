package template.mv_frame.mvpActivity.src.app_package

fun mvpViewKt(
        packageName: String,
        viewClass: String,
        now: String,
        remarks: String
) = """
package $packageName.ui.view

import com.kangraoo.basektlib.ui.mvp.BasePresenter
import com.kangraoo.basektlib.ui.IBaseView

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
interface $viewClass : IBaseView{
   
}
""".trimIndent()