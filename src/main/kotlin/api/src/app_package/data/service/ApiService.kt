package api.src.app_package.data.service

import java.text.DateFormat
import java.util.*

fun apiServiceKt(
        packageName:String,
        apiName:String,
        remark:String,
        methodIsGet:Boolean
) = """
package $packageName.data.source
    
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult
import $packageName.data.model.params.${apiName}Params
import $packageName.data.model.responses.${apiName}Response
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    /**
     * 自动生成：by WaTaNaBe on ${DateFormat.getInstance().format(Date())}.
     * #$apiName#
     * #$remark#
     */
    ${if(methodIsGet) "@GET(ApiMethods.$apiName)" else "@POST(ApiMethods.$apiName)"}
    suspend fun ${apiName}Async(${if(methodIsGet) "@QueryMap params: Map<String, String>" else "@Body params: ${apiName}Params"}): Response<BasicApiResult<${apiName}Response>>

}
"""
