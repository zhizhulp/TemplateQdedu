package api.src.app_package.data.source.local


import java.text.DateFormat
import java.util.*

fun localDataSourceKt(
        remark:String,
        apiName: String,
        packageName: String,
        groupName: String
) = """
package $packageName.data.source.local

import com.kangraoo.basektlib.data.DataResult
import com.kangraoo.basektlib.data.source.local.BaseLocalDataSource
import $packageName.data.model.params.*
import $packageName.data.model.responses.*
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 自动生成：by WaTaNaBe ${DateFormat.getInstance().format(Date())}.
 * ${groupName}LocalDataSource
 */
public class ${groupName}LocalDataSource internal constructor(
     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseLocalDataSource(), ${groupName}DataSource {

     /**
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    override suspend fun $apiName(param: ${apiName}Params): DataResult<BasicApiResult<${apiName}Response>> {
        TODO("Not yet implemented")
    }
}

"""
