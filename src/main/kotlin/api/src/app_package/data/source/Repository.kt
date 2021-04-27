package api.src.app_package.data.source

import java.text.DateFormat
import java.util.*


fun repositoryKt(
        remark:String,
        apiName: String,
        packageName: String,
        groupName: String
) = """
package $packageName.data.source

import com.kangraoo.basektlib.data.source.BaseRepository
import $packageName.data.source.local.*
import $packageName.data.source.remote.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import $packageName.data.model.params.*
import $packageName.data.model.responses.*

/**
 * 自动生成：by WaTaNaBe ${DateFormat.getInstance().format(Date())}.
 * ${groupName}Repository
 */
class ${groupName}Repository : BaseRepository<${groupName}LocalDataSource,${groupName}RemoteDataSource>(${groupName}LocalDataSource(),${groupName}RemoteDataSource()),${groupName}DataSource {

    companion object{
        val instance: ${groupName}Repository by lazy {
            ${groupName}Repository()
        }
    }
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

     /**
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    override suspend fun  $apiName(param: ${apiName}Params) = remoteDataSource.${apiName}(param)
    
}

"""
