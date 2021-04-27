package api

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

     val packageName = stringParameter {
         name = "Root Package Name"
         default = "com.mycompany.myapp"
         constraints = listOf(Constraint.PACKAGE)
         help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:me.jessyan.arms"
     }
     val groupName= stringParameter {
         name = "Group Name"
         default = "Homework"
         help = "Group Name"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val apiName= stringParameter {
         name = "Api Name"
         default = "Main"
         help = "Api Name"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val apiUrl= stringParameter {
         name = "Api Url"
         default = "/api/user/student"
         help = "Api Url"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val isGetMethod= booleanParameter {
         name = "isGetMethod"
         default = false
         help = "isGetMethod"
     }

     val remarkName= stringParameter {
         name = "remarkName Name"
         default = "description"
         help = "remarkName Name"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }
     thumb { File("template_blank_activity.png") }
     widgets(
             TextFieldWidget(packageName),
             TextFieldWidget(groupName),
             TextFieldWidget(apiName),
             TextFieldWidget(apiUrl),
             CheckBoxWidget(isGetMethod),
             TextFieldWidget(remarkName),
     )

     recipe = { data: TemplateData ->
         apiRecipe(
                 data as ModuleTemplateData,
                 packageName.value,
                 groupName.value,
                 apiName.value,
                 apiUrl.value,
                 isGetMethod.value,
                 remarkName.value
         )
     }

 }