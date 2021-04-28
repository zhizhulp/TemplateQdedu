package template.mv_frame.mvplistActivity.src.app_package

fun mvpListActivityPresenter(
        packageName:String,
        viewClass:String,
        now:String,
        remarks:String,
        presenterClass:String,
        entity:String
) ="""
package $packageName.ui.presenter

import $packageName.ui.view.$viewClass
import $packageName.data.model.responses.$entity
import com.kangraoo.basektlib.ui.mvp.BasePresenter
import com.kangraoo.basektlib.ui.action.BASE_PAGE_NAME
import com.kangraoo.basektlib.ui.action.BasePageAction
import com.kangraoo.basektlib.data.DataResult
import kotlinx.coroutines.launch

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $presenterClass : BasePresenter<$viewClass>(){
	var basePageAction:BasePageAction<$entity> = BasePageAction()
    init {
        addAction(BASE_PAGE_NAME,basePageAction)
        basePageAction.dataListener = object : BasePageAction.DataListener<$entity>{
            override fun request(
                basePageAction: BasePageAction<$entity>,
                currentPage: Int,
                pageSize : Int
            ) {
                launch {
                    val entity = $entity()
                    entity.currentPage = currentPage
                    entity.pageSize = pageSize
                    val dataResult = EntityRepository.instance
                    if(dataResult is DataResult.Success){
                        basePageAction.dataSucces(dataResult.list,dataResult.totalCount)
                    }else if(dataResult is DataResult.Error){
                        basePageAction.dataError(dataResult)
                    }
                }
            }
        }
    }
}
""".trimIndent()