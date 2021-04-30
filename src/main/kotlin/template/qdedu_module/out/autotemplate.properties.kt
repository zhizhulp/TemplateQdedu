package template.qdedu_module.out

fun mvConfig()="""
    #HomeWork=[\
    #    {"title":"studentWorkTaskList","url":"work/composite/list-student-work-task","type":"GET","remarks":"作业模块通用接口，通过moduleTpye指定类型：7闪测评  10作业"},\
    #    {"title":"subjectList","url":"wisdom/usersubject/list4subject","type":"GET","remarks":"用户科目通用接口"}\
    #]
""".trimIndent()