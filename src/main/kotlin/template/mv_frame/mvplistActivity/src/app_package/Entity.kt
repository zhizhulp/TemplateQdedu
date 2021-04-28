package template.mv_frame.mvplistActivity.src.app_package


fun entityKt(
        entityName:String,
        packageName: String,
        remark: String,
        now:String
) = """
package $packageName.data.model.responses

/**
 * 自动生成：by WaTaNaBe on $now.
 * #$remark#
 */
class $entityName {

}
"""


