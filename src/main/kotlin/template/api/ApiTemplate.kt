package template.api

import com.android.tools.idea.wizard.template.*
import java.io.File

val apiTemplate
 get() =template{
     revision = 1
     name = "ApiTemplate"
     description = "一键创建 ApiTemplate 单个页面所需要的全部组件"
     minApi = 9
     minBuildApi = 15
     category = Category.Other
     formFactor = FormFactor.Mobile
     screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)


     val groupName= stringParameter {
         name = "组名"
         default = ""
         help = "组名"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val apiName= stringParameter {
         name = "api链接名字，用于生成其他类的前缀"
         default = ""
         help = "api链接名字，用于生成其他类的前缀"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val apiUrl= stringParameter {
         name = "请求链接 例如/template/api/user/student"
         default = ""
         help = "请求链接"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val isGetMethod= booleanParameter {
         name = "是get请求还是post请求"
         default = false
         help = "是get请求还是post请求"
     }

     val remarkName= stringParameter {
         name = "描述内容"
         default = "description"
         help = "描述内容"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }
     thumb { File("template_blank_activity.png") }
     widgets(
             TextFieldWidget(groupName),
             TextFieldWidget(apiName),
             TextFieldWidget(apiUrl),
             CheckBoxWidget(isGetMethod),
             TextFieldWidget(remarkName),
     )

     recipe = { data: TemplateData ->
         apiRecipe(
                 data as ModuleTemplateData,
                 groupName.value,
                 apiName.value,
                 apiUrl.value,
                 isGetMethod.value,
                 remarkName.value
         )
     }

 }