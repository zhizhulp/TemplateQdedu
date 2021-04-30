package template.qdedu_module

import com.android.tools.idea.wizard.template.*
import java.io.File

val qdModuleTemplate
 get() =template{
     revision = 1
     name = "QdModule"
     description = "一键创建清大module组件"
     minApi = 9
     minBuildApi = 15
     category = Category.Application
     formFactor = FormFactor.Mobile
     screens = listOf( WizardUiContext.MenuEntry)

     thumb { File("template_blank_activity.png") }

     val appName= stringParameter {
         name = "app名字"
         default ="xx模块"
         constraints = listOf(Constraint.NONEMPTY)
     }

     widgets= listOf(
             TextFieldWidget(appName)
     )

     recipe = { data: TemplateData ->
         apiRecipe(
                 data as ModuleTemplateData,
                 appName.value
         )
     }

 }