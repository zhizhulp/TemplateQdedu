package template.api.src.app_package.data.service

import util.timeNow
import java.text.DateFormat
import java.util.*

fun apiMethodsKt(
        packageName:String,
        apiName:String,
        apiUrl:String,
        remark:String
) = """
package $packageName.data.service
    
object ApiMethods {

    /**
     * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
     * #$apiName#
     * #$remark#
     */
    const val $apiName = "$apiUrl"

}
"""
