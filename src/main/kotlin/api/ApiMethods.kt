package api

import java.text.DateFormat
import java.util.*

fun apiMethodsKt(
        apiName:String,
        apiUrl:String,
        remark:String
) = """
object ApiMethods {

    /**
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    const val $apiName = $apiUrl

//#06#
}
"""
