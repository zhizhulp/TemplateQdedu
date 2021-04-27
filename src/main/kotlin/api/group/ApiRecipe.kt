package api.group

import api.*
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import util.AttachmentStore


fun RecipeExecutor.apiRecipe(
        moduleData: ModuleTemplateData,
        packageName: String,
        groupName: String,
        apiName: String,
        apiUrl: String,
        isGetMethod: Boolean,
        remarkName: String
){
    val (projectData, srcOut, resOut,manifestOut) = moduleData
    val outFileApiMethods = srcOut.resolve("data/service/ApiMethods.kt")
    //val outFileApiMethodsTemp = srcOut.resolve("data/service/temp/ApiMethods.kt")
    if(outFileApiMethods.exists()){
        //save(apiMethodsKt(apiName,apiUrl,remarkName),outFileApiMethodsTemp)
        //copy and append temp/ApiMethods.kt to origin ApiMethods.kt
        val originString = AttachmentStore.loadAsString(outFileApiMethods.absolutePath)
        println("originString:$originString")
        //val appendString = AttachmentStore.loadAsString(outFileApiMethodsTemp.absolutePath)
        val appendString=apiMethodsKt(apiName,apiUrl,remarkName)
        println("appendString:$appendString")
        val finalString = transformData(originString, appendString)
        println("finalString:$finalString")
        val save = AttachmentStore.save(finalString, outFileApiMethods.absolutePath)
        println("save:$save")
    }else{
        save(apiMethodsKt(apiName,apiUrl,remarkName),outFileApiMethods)
    }

//    save(apiServiceKt(packageName,apiName,remarkName,isGetMethod),srcOut.resolve("data/service/ApiService.kt"))
//    save(appServiceKt(),srcOut.resolve("data/service/AppService.kt"))
//    save(dataSourceKt(remarkName,apiName,packageName,groupName),srcOut.resolve("data/source/${groupName}DataSource.kt"))
//    save(localDataSourceKt(groupName),srcOut.resolve("data/source/local/${groupName}LocalDataSource.kt"))
//    save(remoteDataSourceKt(groupName),srcOut.resolve("data/source/remote/${groupName}RemoteDataSource.kt"))
//    save(paramsKt(remarkName,groupName),srcOut.resolve("data/model/params/${groupName}Params.kt"))
//    save(responseKt(groupName),srcOut.resolve("data/model/responses/${groupName}Response.kt"))
//    save(repositoryKt(groupName),srcOut.resolve("data/source/${groupName}Repository.kt"))
}

fun transformData(origin:String,append:String):String{
    val indexOf = append.indexOf("{")
    val lastIndexOf = append.lastIndexOf("}")
    val appendString = append.substring(indexOf + 1, lastIndexOf + 1)

    val lastIndexOf1 = origin.lastIndexOf("}")
    val originFormatString = origin.substring(0, lastIndexOf1)
    val finalString = originFormatString.plus(appendString)
    return finalString
}

