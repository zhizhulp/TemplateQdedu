package api.src.app_package.data.service

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
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    const val $apiName = "$apiUrl"

}
"""
