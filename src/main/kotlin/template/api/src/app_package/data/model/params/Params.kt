package template.api.src.app_package.data.model.params

import util.firstToUpperCase
import util.timeNow
import java.text.DateFormat
import java.util.*

fun paramsKt(
        packageName:String,
        apiName:String,
        remark:String
) = """

package $packageName.data.model.params
import com.kangraoo.basektlib.data.model.BParam

/**
 * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
 * $apiName
 * #$remark#
*/
class ${apiName.firstToUpperCase()}Params():BParam(){

}
"""


