package api.src.app_package.data.model.params

import java.text.DateFormat
import java.util.*

fun paramsKt(
        pageRemark:String,
        pageName: String
) = """

import com.kangraoo.basektlib.data.model.BParam

/**
 * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
 * $pageName
 * #${pageRemark}#
*/

class ${pageName}Params():BParam(){

}
"""


