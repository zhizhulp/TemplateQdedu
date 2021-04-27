package api.src.app_package.data.service


fun appServiceKt(
) = """
package com.qdedu.homework.data.source

import com.qdedu.baselibcommon.data.source.ApiSource
import com.qdedu.homework.data.service.ApiService

object AppService {

    fun getApiService(mApi: String): ApiService {
        return ApiSource.instance(mApi).getApiRetrofit.create(ApiService::class.java)
    }
}

"""


