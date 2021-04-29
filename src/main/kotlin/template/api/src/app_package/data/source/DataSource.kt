package template.api.src.app_package.data.source

import util.firstToUpperCase
import util.timeNow
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
 * 自动生成：by WaTaNaBe ${Date().timeNow()}.
 * ${groupName}DataSource
 */
interface ${groupName}DataSource {

     /**
     * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
     * #$apiName#
     * #$remark#
     */
    suspend fun ${apiName}( param: ${apiName.firstToUpperCase()}Params): DataResult<BasicApiResult<${apiName.firstToUpperCase()}Response>>

}

"""
