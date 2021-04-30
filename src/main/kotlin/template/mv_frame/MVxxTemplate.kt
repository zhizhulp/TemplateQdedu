package template.mv_frame

import com.android.tools.idea.wizard.template.*
import java.io.File

val mvxxTemplate
 get() =template{
     revision = 1
     name = "MVxx架构"
     description = "一键创建 mvxxTemplate 单个页面所需要的全部组件"
     minApi = 9
     minBuildApi = 15
     category = Category.Application
     formFactor = FormFactor.Mobile
     screens = listOf(WizardUiContext.MenuEntry)


     val prefix= stringParameter {
         name = "前缀"
         default = "TestAuto10"
         help = "前缀"
         constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
     }

     val remarkName= stringParameter {
         name = "标题"
         default = "test auto"
         help = "标题"
     }

     val uiType=enumParameter<UIType>{
         name = "界面类型"
         default = UIType.ACTIVITY
         help ="Activity or Fragment"
     }

     val mvType=enumParameter<MVType>{
         name = "MVxx架构模式"
         default = MVType.MVC
         help ="MVxx架构模式"
     }
     val entityName = stringParameter {
         visible = {
             mvType.value== MVType.MVPLIST ||  mvType.value== MVType.MVVMLIST
         }
         name = "列表实体类（不填会自动生成，填写Main则生成Main）"
         default = ""
         help = "列表实体类"
     }
     thumb { File("template_blank_activity.png") }
     widgets(
             TextFieldWidget(prefix),
             TextFieldWidget(remarkName),
             EnumWidget(uiType),
             EnumWidget(mvType),
             TextFieldWidget(entityName)
     )

     recipe = { data: TemplateData ->
         apiRecipe(
                 data as ModuleTemplateData,
                 prefix.value,
                 remarkName.value,
                 uiType.value,
                 mvType.value,
                 entityName.value
         )
     }

 }