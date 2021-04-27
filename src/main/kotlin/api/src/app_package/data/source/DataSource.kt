package api.src.app_package.data.source

import util.firstToUpperCase
import java.text.DateFormat
import java.util.*

fun dataSourceKt(
        remark: String,
        apiName: String,
        packageName: String,
        groupName: String
) = """
package $packageName.data.source
import com.kangraoo.basektlib.data.DataResult
import $packageName.data.model.params.*
import $packageName.data.model.responses.*
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult

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
    suspend fun ${apiName}( param: ${apiName.firstToUpperCase()}Params): DataResult<BasicApiResult<${apiName.firstToUpperCase()}Response>>

}

"""
