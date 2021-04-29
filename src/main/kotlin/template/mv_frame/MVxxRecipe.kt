package template.mv_frame

import com.android.tools.idea.wizard.template.*
import template.mv_frame.mvcActivity.res.layout.mvcActivityXml
import template.mv_frame.mvcActivity.src.app_package.mvcActivityKt
import template.mv_frame.mvcFragment.res.layout.mvcFragmentXml
import template.mv_frame.mvcFragment.src.app_package.mvcFragmentKt
import template.mv_frame.mvpActivity.res.layout.activity_layoutKt
import template.mv_frame.mvpActivity.src.app_package.mvpActivityKt
import template.mv_frame.mvpActivity.src.app_package.mvpPresenterKt
import template.mv_frame.mvpActivity.src.app_package.mvpViewKt
import template.mv_frame.mvpFragment.res.layout.fragment_layoutKt
import template.mv_frame.mvpFragment.src.app_package.mvpFragmentKt
import template.mv_frame.mvpFragment.src.app_package.presenterKt
import template.mv_frame.mvpFragment.src.app_package.viewKt
import template.mv_frame.mvplistActivity.res.layout.mvpActivityListAXml
import template.mv_frame.mvplistActivity.res.layout.mvpListActivityAdapterXml
import template.mv_frame.mvplistActivity.src.app_package.*
import template.mv_frame.mvplistFragment.res.layout.mvpFragmentListAXml
import template.mv_frame.mvplistFragment.res.layout.mvpListFragmentAdapterXml
import template.mv_frame.mvplistFragment.src.app_package.mvpListFragmentAdapterKt
import template.mv_frame.mvplistFragment.src.app_package.mvpListFragmentKt
import template.mv_frame.mvplistFragment.src.app_package.mvpListFragmentPresenter
import template.mv_frame.mvplistFragment.src.app_package.mvpListFragmentViewKt
import template.mv_frame.mvvmActivity.res.layout.mvvmXml
import template.mv_frame.mvvmActivity.src.app_package.mvvmActivityKt
import template.mv_frame.mvvmActivity.src.app_package.viewModelKt
import template.mv_frame.mvvmFragment.res.layout.mvvmFragmentXml
import template.mv_frame.mvvmFragment.src.app_package.mvvmFragmentKt
import template.mv_frame.mvvmFragment.src.app_package.mvvmFragmentViewModel
import template.mv_frame.mvvmlistActivity.res.layout.mvvmListActivityAXml
import template.mv_frame.mvvmlistActivity.res.layout.mvvmListActivityAdapterXml
import template.mv_frame.mvvmlistActivity.src.app_package.mvvmListActivityAdapterKt
import template.mv_frame.mvvmlistActivity.src.app_package.mvvmListActivityKt
import template.mv_frame.mvvmlistActivity.src.app_package.mvvmListActivityViewModelKt
import template.mv_frame.mvvmlistFragment.res.layout.mvvmListFragmentAXml
import template.mv_frame.mvvmlistFragment.res.layout.mvvmListFragmentAdapterXml
import template.mv_frame.mvvmlistFragment.src.app_package.mvvmListFragmentKt
import template.mv_frame.mvvmlistFragment.src.app_package.mvvmListFragmentViewModelKt
import template.mvvmListFragmentAdapterKt
import util.timeNow
import java.util.*

fun RecipeExecutor.apiRecipe(
        moduleData: ModuleTemplateData,
        prefix: String,
        remarkName: String,
        uiType: UIType,
        mvType: MVType,
        entity: String?
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData
    val packageName = moduleData.packageName
    val now = Date().timeNow()
    val activityClass = "${prefix}Activity"
    val fragmentClass = "${prefix}Fragment"
    val viewModelClass = "${prefix}ViewModel"
    val viewClass = "${prefix}View"
    val presenterClass = "${prefix}Presenter"
    val adapterClass = "${prefix}Adapter"
    val entityName = if (mvType == MVType.MVPLIST || mvType == MVType.MVVMLIST) {
        if (entity.isNullOrEmpty()) {
            "${prefix}Entity"
        } else {
            entity
        }
    } else "${prefix}Entity"
    val activityLayoutName = activityToLayout(activityClass)
    val fragmentLayoutName = fragmentToLayout(fragmentClass)
    val itemLayoutName = "item_${camelCaseToUnderlines(prefix)}"
    if (uiType == UIType.ACTIVITY) {
        when (mvType) {
            MVType.MVC -> {
                save(mvcActivityKt(activityClass, packageName, activityLayoutName, remarkName), srcOut.resolve("ui/activity/$activityClass.kt"))
                save(mvcActivityXml(activityClass, packageName), resOut.resolve("layout/$activityLayoutName.xml"))
            }
            MVType.MVP -> {
                save(activity_layoutKt(packageName, activityClass), resOut.resolve("layout/$activityLayoutName.xml"))
                save(mvpActivityKt(activityClass, presenterClass, viewClass, packageName, activityLayoutName, remarkName, now), srcOut.resolve("ui/activity/$activityClass.kt"))
                save(mvpPresenterKt(packageName, viewClass, now, remarkName, presenterClass), srcOut.resolve("ui/presenter/$presenterClass.kt"))
                save(mvpViewKt(packageName, viewClass, now, remarkName), srcOut.resolve("ui/view/$viewClass.kt"))
            }
            MVType.MVVM -> {
                save(mvvmXml(packageName, activityClass), resOut.resolve("layout/$activityLayoutName.xml"))
                save(mvvmActivityKt(packageName, viewModelClass, activityLayoutName, now, remarkName, activityClass), srcOut.resolve("ui/activity/$activityClass.kt"))
                save(viewModelKt(packageName, now, remarkName, viewModelClass), srcOut.resolve("ui/viewmodel/$viewModelClass.kt"))
            }
            MVType.MVPLIST -> {
                save(mvpActivityListAXml(packageName, activityClass), resOut.resolve("layout/$activityLayoutName.xml"))
                save(mvpListActivityAdapterXml(), resOut.resolve("layout/$itemLayoutName.xml"))
                save(mvpListActivityKt(packageName, viewClass, presenterClass, activityLayoutName, adapterClass, now, remarkName, activityClass, entityName), srcOut.resolve("ui/activity/$activityClass.kt"))
                save(mvpListActivityAdapterKt(packageName, itemLayoutName, now, remarkName, adapterClass, entityName), srcOut.resolve("ui/adapter/$adapterClass.kt"))
                save(mvpListActivityPresenter(packageName, viewClass, now, remarkName, presenterClass, entityName), srcOut.resolve("ui/presenter/$presenterClass.kt"))
                save(mvpListActivityViewKt(packageName, viewClass, now, remarkName, entityName), srcOut.resolve("ui/view/$viewClass.kt"))
                save(entityKt(entityName, packageName, remarkName, now), srcOut.resolve("data/model/responses/$entityName.kt"))
            }
            MVType.MVVMLIST -> {
                save(mvvmListActivityAXml(packageName, activityClass), resOut.resolve("layout/$activityLayoutName.xml"))
                save(mvvmListActivityAdapterXml(), resOut.resolve("layout/$itemLayoutName.xml"))
                save(mvvmListActivityKt(packageName, viewModelClass, activityLayoutName, adapterClass, now, remarkName, activityClass, entityName), srcOut.resolve("ui/activity/$activityClass.kt"))
                save(mvvmListActivityAdapterKt(packageName, itemLayoutName, now, remarkName, adapterClass, entityName), srcOut.resolve("ui/adapter/$adapterClass.kt"))
                save(mvvmListActivityViewModelKt(packageName, now, remarkName, viewModelClass, entityName), srcOut.resolve("ui/viewmodel/$viewModelClass.kt"))
                save(entityKt(entityName, packageName, remarkName, now), srcOut.resolve("data/model/responses/$entityName.kt"))
            }
        }
    } else if (uiType == UIType.FRAGMENT) {
        when (mvType) {
            MVType.MVC -> {
                save(mvcFragmentKt(fragmentClass, packageName, fragmentLayoutName, remarkName,now), srcOut.resolve("ui/fragment/$fragmentClass.kt"))
                save(mvcFragmentXml(fragmentClass, packageName), resOut.resolve("layout/$fragmentLayoutName.xml"))
            }
            MVType.MVP -> {
                save(fragment_layoutKt(packageName, fragmentClass), resOut.resolve("layout/$fragmentLayoutName.xml"))
                save(mvpFragmentKt(packageName, viewClass, presenterClass, fragmentLayoutName, now, remarkName, fragmentClass), srcOut.resolve("ui/fragment/$fragmentClass.kt"))
                save(presenterKt(packageName, viewClass, now, remarkName, presenterClass), srcOut.resolve("ui/presenter/$presenterClass.kt"))
                save(viewKt(packageName, viewClass, now, remarkName), srcOut.resolve("ui/view/$viewClass.kt"))
            }
            MVType.MVVM -> {
                save(mvvmFragmentXml(packageName, fragmentClass), resOut.resolve("layout/$fragmentLayoutName.xml"))
                save(mvvmFragmentKt(packageName,fragmentLayoutName,viewModelClass,now,remarkName,fragmentClass), srcOut.resolve("ui/fragment/$fragmentClass.kt"))
                save(mvvmFragmentViewModel(packageName, now, remarkName, viewModelClass), srcOut.resolve("ui/viewmodel/$viewModelClass.kt"))
            }
            MVType.MVPLIST -> {
                save(mvpFragmentListAXml(packageName, fragmentClass), resOut.resolve("layout/$fragmentLayoutName.xml"))
                save(mvpListFragmentAdapterXml(), resOut.resolve("layout/$itemLayoutName.xml"))
                save(mvpListFragmentKt(packageName, viewClass, presenterClass,adapterClass,fragmentLayoutName,now,remarkName,fragmentClass,entityName), srcOut.resolve("ui/fragment/$fragmentClass.kt"))
                save(mvpListFragmentAdapterKt(packageName, itemLayoutName, now, adapterClass, remarkName, entityName), srcOut.resolve("ui/adapter/$adapterClass.kt"))
                save(mvpListFragmentPresenter(packageName, viewClass, now, remarkName, presenterClass, entityName), srcOut.resolve("ui/presenter/$presenterClass.kt"))
                save(mvpListFragmentViewKt(packageName, viewClass, now, remarkName, entityName), srcOut.resolve("ui/view/$viewClass.kt"))
                save(entityKt(entityName, packageName, remarkName, now), srcOut.resolve("data/model/responses/$entityName.kt"))
            }
            MVType.MVVMLIST -> {
                save(mvvmListFragmentAXml(packageName, fragmentClass), resOut.resolve("layout/$fragmentLayoutName.xml"))
                save(mvvmListFragmentAdapterXml(), resOut.resolve("layout/$itemLayoutName.xml"))
                save(mvvmListFragmentKt(packageName,fragmentLayoutName,viewModelClass,now,remarkName,fragmentClass,adapterClass,entityName), srcOut.resolve("ui/fragment/$fragmentClass.kt"))
                save(mvvmListFragmentAdapterKt(packageName, itemLayoutName, now, remarkName, adapterClass, entityName), srcOut.resolve("ui/adapter/$adapterClass.kt"))
                save(mvvmListFragmentViewModelKt(packageName, now, remarkName, viewModelClass, entityName), srcOut.resolve("ui/viewmodel/$viewModelClass.kt"))
                save(entityKt(entityName, packageName, remarkName, now), srcOut.resolve("data/model/responses/$entityName.kt"))
            }
        }
    }

}

