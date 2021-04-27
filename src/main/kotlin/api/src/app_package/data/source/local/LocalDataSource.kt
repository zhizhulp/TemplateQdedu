package api.src.app_package.data.source.local


import java.text.DateFormat
import java.util.*

fun localDataSourceKt(
        sourceName: String,
) = """
package com.qdedu.homework.data.source.local

import com.kangraoo.basektlib.data.DataResult
import com.kangraoo.basektlib.data.source.local.BaseLocalDataSource
import com.qdedu.homework.data.source.HomeWorkDataSource
import com.qdedu.homework.data.model.params.*
import com.qdedu.homework.data.model.responses.*
import com.qdedu.baselibcommon.data.model.responses.BasicApiResult
import com.qdedu.homework.data.model.entity.BatchSearchModel
import com.qdedu.homework.data.model.entity.FeedBackQuestionEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 自动生成：by WaTaNaBe on 2021-03-11 09:29.
 * HomeWorkLocalDataSource
 */
public class ${sourceName}LocalDataSource internal constructor(
     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseLocalDataSource(), HomeWorkDataSource {


    /**
     * 自动生成：by WaTaNaBe on 2021-03-11 09:29.
     * #studentWorkTaskList#
     * #作业模块通用接口，通过moduleTpye指定类型：7闪测评  10作业#
     */
    override suspend fun studentWorkTaskList(param: StudentWorkTaskListParams): DataResult<BasicApiResult<StudentWorkTaskListResponse>> {
        TODO("Not yet implemented")
    }

//#06#
}

"""
