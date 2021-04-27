package api

import java.text.DateFormat
import java.util.*

fun dataSourceKt(
        remark:String,
        apiName:String,
        packageName: String,
        groupName:String
) = """
import $packageName.data.source
/**
 * 自动生成：by WaTaNaBe ${DateFormat.getInstance().format(Date())}.
 * ${groupName}DataSource
 */
interface ${groupName}DataSource {

     /**
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    suspend fun ${apiName}( param: ${apiName}Params): DataResult<BasicApiResult<${apiName}Response>>

//#06#
}

"""
