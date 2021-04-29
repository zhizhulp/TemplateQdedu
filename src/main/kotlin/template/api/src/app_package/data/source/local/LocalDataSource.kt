package template.api.src.app_package.data.source.local


import util.firstToUpperCase
import util.timeNow
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
import $packageName.data.source.${groupName}DataSource
import $packageName.data.model.params.*
import $packageName.data.model.responses.*
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 自动生成：by WaTaNaBe ${Date().timeNow()}.
 * ${groupName}LocalDataSource
 */
public class ${groupName}LocalDataSource internal constructor(
     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseLocalDataSource(), ${groupName}DataSource {

     /**
     * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
     * #$apiName#
     * #$remark#
     */
    override suspend fun $apiName(param: ${apiName.firstToUpperCase()}Params): DataResult<BasicApiResult<${apiName.firstToUpperCase()}Response>> {
        TODO("Not yet implemented")
    }
}

"""
