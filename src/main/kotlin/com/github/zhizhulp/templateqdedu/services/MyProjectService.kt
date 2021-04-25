package com.github.zhizhulp.templateqdedu.services

import com.github.zhizhulp.templateqdedu.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
