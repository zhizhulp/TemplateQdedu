package template.mv_frame.mvplistActivity.src.app_package

fun mvpListActivityViewKt(
        packageName:String,
        viewClass:String,
        now:String,
        remarks:String,
        entity:String
) ="""
package $packageName.ui.view

import com.kangraoo.basektlib.ui.mvp.BasePresenter
import com.kangraoo.basektlib.ui.IBaseView
import com.kangraoo.basektlib.ui.impl.IBasePageView
import $packageName.data.model.responses.$entity

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
interface $viewClass : IBaseView,IBasePageView<$entity> {
   
}
""".trimIndent()