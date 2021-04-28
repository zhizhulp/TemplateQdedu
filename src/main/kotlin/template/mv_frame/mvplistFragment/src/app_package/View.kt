package template.mv_frame.mvplistFragment.src.app_package

fun mvpListFragmentViewKt(
        packageName:String,
        viewClass:String,
        now:String,
        remarks:String,
        entity:String
) ="""
package $packageName.ui.view

import $packageName.data.model.responses.$entity
import com.kangraoo.basektlib.ui.mvp.BasePresenter
import com.kangraoo.basektlib.ui.IBaseView
import com.kangraoo.basektlib.ui.impl.IBasePageView

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
interface $viewClass : IBaseView,IBasePageView<$entity> {
   
}
""".trimIndent()