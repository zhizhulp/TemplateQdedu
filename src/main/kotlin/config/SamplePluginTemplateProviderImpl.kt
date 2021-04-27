package config
import api.apiTemplate
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

/**
 * description:
 * author: liping
 * date: 2021/4/26 9:53
 */
class SamplePluginTemplateProviderImpl : WizardTemplateProvider(){
    override fun getTemplates(): List<Template> = listOf(
            // activity的模板
            apiTemplate
    )
}
