package api.src.app_package.data.service


fun appServiceKt(
        packageName:String
) = """
package $packageName.data.source

import com.qdedu.baselibcommon.data.source.ApiSource
import $packageName.data.service.ApiService

object AppService {

    fun getApiService(mApi: String): ApiService {
        return ApiSource.instance(mApi).getApiRetrofit.create(ApiService::class.java)
    }
}

"""


