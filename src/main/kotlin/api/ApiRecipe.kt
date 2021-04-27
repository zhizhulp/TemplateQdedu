package api

import api.src.app_package.data.model.params.paramsKt
import api.src.app_package.data.model.responses.responseKt
import api.src.app_package.data.service.apiMethodsKt
import api.src.app_package.data.service.apiServiceKt
import api.src.app_package.data.service.appServiceKt
import api.src.app_package.data.source.dataSourceKt
import api.src.app_package.data.source.local.localDataSourceKt
import api.src.app_package.data.source.remote.remoteDataSourceKt
import api.src.app_package.data.source.repositoryKt
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import util.AttachmentStore
import java.io.File


fun RecipeExecutor.apiRecipe(
        moduleData: ModuleTemplateData,
        packageName: String,
        groupName: String,
        apiName: String,
        apiUrl: String,
        isGetMethod: Boolean,
        remarkName: String
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData
    addOrMergeApiMethod(srcOut,apiName, apiUrl, remarkName,this)


    save(apiServiceKt(packageName,apiName,remarkName,isGetMethod),srcOut.resolve("data/service/ApiService.kt"))
    save(appServiceKt(),srcOut.resolve("data/service/AppService.kt"))
    save(dataSourceKt(remarkName,apiName,packageName,groupName),srcOut.resolve("data/source/${groupName}DataSource.kt"))
    save(localDataSourceKt(groupName),srcOut.resolve("data/source/local/${groupName}LocalDataSource.kt"))
    save(remoteDataSourceKt(groupName),srcOut.resolve("data/source/remote/${groupName}RemoteDataSource.kt"))
    save(paramsKt(remarkName,groupName),srcOut.resolve("data/model/params/${groupName}Params.kt"))
    save(responseKt(groupName),srcOut.resolve("data/model/responses/${groupName}Response.kt"))
    save(repositoryKt(groupName),srcOut.resolve("data/source/${groupName}Repository.kt"))
}

fun addOrMergeApiMethod(srcOut: File, apiName: String, apiUrl: String, remarkName: String, recipeExecutor: RecipeExecutor) {
    val outFileApiMethods = srcOut.resolve("data/service/ApiMethods.kt")
    val newDataString = apiMethodsKt(apiName, apiUrl, remarkName)
    if (outFileApiMethods.exists()) {
        val originString = AttachmentStore.loadAsString(outFileApiMethods.absolutePath)
        if (originString.contains(apiName) && originString.contains(apiUrl) && originString.contains(remarkName)) {//此方法会多次执行
            return
        }
        val finalString = transformData(originString, newDataString)
        AttachmentStore.save(outFileApiMethods.absolutePath, finalString)
    } else {
        recipeExecutor.save(newDataString, outFileApiMethods)
    }
}

fun transformData(origin: String, append: String): String {
    val indexOf = append.indexOf("{")
    val lastIndexOf = append.lastIndexOf("}")
    val appendString = append.substring(indexOf + 1, lastIndexOf + 1)
    val lastIndexOf1 = origin.lastIndexOf("}")
    val originFormatString = origin.substring(0, lastIndexOf1)
    return originFormatString.plus(appendString)
}

