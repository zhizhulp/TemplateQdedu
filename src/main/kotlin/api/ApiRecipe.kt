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
import util.firstToUpperCase
import java.io.File


fun RecipeExecutor.apiRecipe(
        moduleData: ModuleTemplateData,
        groupName: String,
        apiName: String,
        apiUrl: String,
        isGetMethod: Boolean,
        remarkName: String
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData
    val packageName = moduleData.packageName
    addOrMergeApiMethod(srcOut,packageName, apiName, apiUrl, remarkName, this)
    save(paramsKt(packageName, apiName, remarkName), srcOut.resolve("data/model/params/${apiName.firstToUpperCase()}Params.kt"))
    save(responseKt(packageName, apiName, remarkName), srcOut.resolve("data/model/responses/${apiName.firstToUpperCase()}Response.kt"))
    addOrMergeApiService(srcOut,packageName, apiName, remarkName, isGetMethod,this)
    save(appServiceKt(packageName), srcOut.resolve("data/source/AppService.kt"))

    addOrMergeDataSource(srcOut,remarkName, apiName, packageName, groupName,this)
    addOrMergeDataSourceLocal(srcOut,remarkName, apiName, packageName, groupName,this)
    addOrMergeDataSourceRemote(srcOut,remarkName, apiName, packageName, groupName,this)
    addOrMergeRepository(srcOut,remarkName, apiName, packageName, groupName,this)

}

fun addOrMergeRepository(srcOut: File, remarkName: String, apiName: String, packageName: String, groupName: String, recipeExecutor: RecipeExecutor) {
    val outFile = srcOut.resolve("data/source/${groupName}Repository.kt")
    val newDataString = repositoryKt(remarkName, apiName, packageName, groupName,outFile.exists())
    if (outFile.exists()) {
        val originString = AttachmentStore.loadAsString(outFile.absolutePath)
        if (originString.contains(apiName) && originString.contains(remarkName)) {//此方法会多次执行
            return
        }
        val finalString = transformData(originString, newDataString)
        AttachmentStore.save(outFile.absolutePath, finalString)
    } else {
        recipeExecutor.save(newDataString, outFile)
    }
}

fun addOrMergeDataSourceRemote(srcOut: File, remarkName: String, apiName: String, packageName: String, groupName: String, recipeExecutor: RecipeExecutor) {
    val outFile = srcOut.resolve("data/source/remote/${groupName}RemoteDataSource.kt")
    val newDataString = remoteDataSourceKt(remarkName, apiName, packageName, groupName)
    if (outFile.exists()) {
        val originString = AttachmentStore.loadAsString(outFile.absolutePath)
        if (originString.contains(apiName) && originString.contains(remarkName)) {//此方法会多次执行
            return
        }
        val finalString = transformData(originString, newDataString)
        AttachmentStore.save(outFile.absolutePath, finalString)
    } else {
        recipeExecutor.save(newDataString, outFile)
    }
}

fun addOrMergeDataSourceLocal(srcOut: File, remarkName: String, apiName: String, packageName: String, groupName: String, recipeExecutor: RecipeExecutor) {
    val outFile = srcOut.resolve("data/source/local/${groupName}LocalDataSource.kt")
    val newDataString = localDataSourceKt(remarkName, apiName, packageName, groupName)
    if (outFile.exists()) {
        val originString = AttachmentStore.loadAsString(outFile.absolutePath)
        if (originString.contains(apiName) && originString.contains(remarkName)) {//此方法会多次执行
            return
        }
        val finalString = transformData(originString, newDataString)
        AttachmentStore.save(outFile.absolutePath, finalString)
    } else {
        recipeExecutor.save(newDataString, outFile)
    }
}

fun addOrMergeDataSource(srcOut: File, remarkName: String, apiName: String, packageName: String, groupName: String, recipeExecutor: RecipeExecutor) {
    val outFile = srcOut.resolve("data/source/${groupName}DataSource.kt")
    val newDataString = dataSourceKt(remarkName, apiName, packageName, groupName)
    if (outFile.exists()) {
        val originString = AttachmentStore.loadAsString(outFile.absolutePath)
        if (originString.contains(apiName) && originString.contains(remarkName)) {//此方法会多次执行
            return
        }
        val finalString = transformData(originString, newDataString)
        AttachmentStore.save(outFile.absolutePath, finalString)
    } else {
        recipeExecutor.save(newDataString, outFile)
    }
}

fun addOrMergeApiService(srcOut: File, packageName: String, apiName: String, remarkName: String, isGetMethod: Boolean, recipeExecutor: RecipeExecutor) {
    val outFile = srcOut.resolve("data/service/ApiService.kt")
    val newDataString = apiServiceKt(packageName, apiName, remarkName, isGetMethod)
    if (outFile.exists()) {
        val originString = AttachmentStore.loadAsString(outFile.absolutePath)
        if (originString.contains(apiName) && originString.contains(remarkName)) {//此方法会多次执行
            return
        }
        val finalString = transformData(originString, newDataString)
        AttachmentStore.save(outFile.absolutePath, finalString)
    } else {
        recipeExecutor.save(newDataString, outFile)
    }
}

fun addOrMergeApiMethod(srcOut: File, packageName: String, apiName: String, apiUrl: String, remarkName: String, recipeExecutor: RecipeExecutor) {
    val outFileApiMethods = srcOut.resolve("data/service/ApiMethods.kt")
    val newDataString = apiMethodsKt(packageName, apiName, apiUrl, remarkName)
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

