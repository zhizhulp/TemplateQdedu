package template.api.src.app_package.data.model.responses

import util.firstToUpperCase
import java.text.DateFormat
import java.util.*

fun responseKt(
        packageName: String,
        apiName: String,
        remark: String
) = """
package $packageName.data.model.responses
import com.squareup.moshi.JsonClass

/**
 * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
 * $apiName
 * #$remark#
*/
@JsonClass(generateAdapter=true)
class ${apiName.firstToUpperCase()}Response {

}
"""
