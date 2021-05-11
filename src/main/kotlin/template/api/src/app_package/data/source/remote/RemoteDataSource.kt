package template.api.src.app_package.data.source.remote


import util.firstToUpperCase
import util.timeNow
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
 * 自动生成：by WaTaNaBe ${Date().timeNow()}.
 * ${groupName}RemoteDataSource
 */
public class ${groupName}RemoteDataSource internal constructor(
     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRemoteDataSource(), ${groupName}DataSource {

    /**
     * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
     * #$apiName#
     * #$remark#
     */
    override suspend fun $apiName(param: ${apiName.firstToUpperCase()}Params)= withContext(ioDispatcher) {
        try {
            val data = AppService.getApiService(AppHuanJingFactory.appModel.apiDomains).${apiName}Async(param.toNetMap())
            if(data.isSuccessful) {
                return@withContext DataResult.Success(data.body()!!).netSuccess()
            }else{
                return@withContext DataResult.Error(LibNetWorkException(data.code(),data.message())).netError()
            }
        } catch (e: Exception) {
            return@withContext DataResult.Error(e).netError()
        }
    }
}

"""
