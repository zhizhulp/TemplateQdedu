package template.mv_frame.mvvmlistActivity.src.app_package

fun mvvmListActivityViewModelKt(
        packageName:String,
        now:String,
        remarks:String,
        viewModelClass:String,
        entity:String
) ="""
package $packageName.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kangraoo.basektlib.data.DataResult
import com.kangraoo.basektlib.ui.action.BASE_PAGE_NAME
import com.kangraoo.basektlib.ui.action.BasePageAction
import com.kangraoo.basektlib.ui.impl.IBasePageView
import com.kangraoo.basektlib.ui.impl.PageStateActionEvent
import com.kangraoo.basektlib.ui.mvvm.BViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import $packageName.data.model.responses.$entity
/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $viewModelClass: BViewModel(), IBasePageView<$entity> {

var basePageAction: BasePageAction<$entity> = BasePageAction()

    init {
        addAction(BASE_PAGE_NAME,basePageAction)
        basePageAction.dataListener = object : BasePageAction.DataListener<$entity>{
            override fun request(
                basePageAction: BasePageAction<$entity>,
                currentPage: Int,
                pageSize : Int
            ) {
                viewModelScope.launch {
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
    val pageActionEvent = MutableLiveData<PageStateActionEvent>()

    override fun refreshCompleted() {
        pageActionEvent.value = (PageStateActionEvent.RefreshCompletedState)
    }

    override fun loadMoreCompleted() {
        pageActionEvent.value = (PageStateActionEvent.LoadMoreCompletedState)
    }

    override fun emptyPage() {
        pageActionEvent.value = (PageStateActionEvent.EmptyPageState)
    }

    override fun setData(data: List<$entity>, isRefreshLast: Boolean) {
        pageActionEvent.value = (PageStateActionEvent.DataState(data,isRefreshLast))
    }

    override fun lastData() {
        pageActionEvent.value = (PageStateActionEvent.LastDataState)
    }

    override fun moreLoadFail(e: Exception) {
        pageActionEvent.value = (PageStateActionEvent.MoreLoadFailState(e))
    }

    override fun onLoadFail(e: Exception) {
        pageActionEvent.value = (PageStateActionEvent.LoadFailState(e))
    }

}
""".trimIndent()