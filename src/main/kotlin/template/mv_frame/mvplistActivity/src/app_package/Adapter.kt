package template.mv_frame.mvplistActivity.src.app_package

fun mvpListActivityAdapterKt(
        packageName: String,
        itemlayoutName: String,
        now: String,
        remarks: String,
        adapterClass: String,
        entity:String
) = """
package $packageName.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import $packageName.R
import kotlinx.android.synthetic.main.$itemlayoutName.view.*
import $packageName.data.model.responses.$entity

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $adapterClass :BaseQuickAdapter<${entity}, BaseViewHolder>(R.layout.$itemlayoutName),LoadMoreModule {
    
    override fun convert(holder: BaseViewHolder, item: ${entity}) {
    
    }
    
}
""".trimIndent()