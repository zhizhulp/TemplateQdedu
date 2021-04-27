package template.api.src.app_package.data.source.remote


import util.firstToUpperCase
import java.text.DateFormat
import java.util.*

fun remoteDataSourceKt(
        remark:String,
        apiName: String,
        packageName: String,
        groupName: String
) = """
package $packageName.data.source.remote

import com.kangraoo.basektlib.data.DataResult
import com.kangraoo.basektlib.data.source.remote.BaseRemoteDataSource
import $packageName.data.source.${groupName}DataSource
import $packageName.data.model.params.*
import $packageName.data.model.responses.*
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 自动生成：by WaTaNaBe ${DateFormat.getInstance().format(Date())}.
 * ${groupName}RemoteDataSource
 */
public class ${groupName}RemoteDataSource internal constructor(
     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRemoteDataSource(), ${groupName}DataSource {

     /**
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    override suspend fun $apiName(param: ${apiName.firstToUpperCase()}Params): DataResult<BasicApiResult<${apiName.firstToUpperCase()}Response>> {
        TODO("Not yet implemented")
    }
}

"""
