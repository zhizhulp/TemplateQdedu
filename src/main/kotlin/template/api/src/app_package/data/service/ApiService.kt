package template.api.src.app_package.data.service

import util.firstToUpperCase
import util.timeNow
import java.text.DateFormat
import java.util.*

fun apiServiceKt(
        packageName:String,
        apiName:String,
        remark:String,
        methodIsGet:Boolean
) = """
package $packageName.data.service
    
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult
import $packageName.data.model.params.${apiName.firstToUpperCase()}Params
import $packageName.data.model.responses.${apiName.firstToUpperCase()}Response
import $packageName.data.service.ApiMethods
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    /**
     * 自动生成：by WaTaNaBe on ${Date().timeNow()}.
     * #$apiName#
     * #$remark#
     */
    ${if(methodIsGet) "@POST(ApiMethods.$apiName)" else "@GET(ApiMethods.$apiName)"}
    suspend fun ${apiName}Async(${if(methodIsGet) "@Body params: ${apiName.firstToUpperCase()}Params" else "@QueryMap params: Map<String, String>"}): Response<BasicApiResult<${apiName.firstToUpperCase()}Response>>

}
"""
