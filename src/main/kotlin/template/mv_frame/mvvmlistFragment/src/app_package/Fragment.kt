package template.mv_frame.mvvmlistFragment.src.app_package

fun mvvmListFragmentKt(
        packageName:String,
        layoutName:String,
        viewModelClass:String,
        now:String,
        remarks:String,
        activityClass:String,
        adapterClass:String,
        entity:String?
) ="""
package $packageName.ui.fragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ktx.immersionBar
import com.kangraoo.basektlib.ui.BActivity
import com.kangraoo.basektlib.widget.toolsbar.LibToolBarOptions
import com.kangraoo.basektlib.widget.toolsbar.OnLibToolBarListener
import $packageName.R
import com.kangraoo.basektlib.ui.mvvm.BVMFragment
import $packageName.ui.viewmodel.$viewModelClass
import kotlinx.android.synthetic.main.$layoutName.*
import com.kangraoo.basektlib.widget.emptypage.AbsEmptyPage
import com.kangraoo.basektlib.widget.emptypage.DefaultEmptyPage
import com.kangraoo.basektlib.widget.emptypage.EmptyPageLayout
import com.kangraoo.basektlib.widget.emptypage.EmptyType
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.kangraoo.basektlib.ui.impl.PageStateActionEvent
import $packageName.ui.adapter.$adapterClass
import android.graphics.Color
import android.util.TypedValue
import $packageName.data.model.responses.$entity

/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $activityClass : BVMFragment<$viewModelClass>(){

    companion object{

        @JvmStatic
        fun newInstance() = $activityClass()
        
    }

    override fun getLayoutId() = R.layout.$layoutName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = $adapterClass()

        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setOnRefreshListener {
            adapter!!.loadMoreModule.isEnableLoadMore = false
            _vm.basePageAction!!.refreshData()
        }

        recycler.layoutManager = LinearLayoutManager(visitActivity())
        visitActivity().dividerBuilder()
            .color(Color.WHITE)
            .size(0, TypedValue.COMPLEX_UNIT_DIP)
            .build()
            .addTo(recycler)


        adapter!!.loadMoreModule.setOnLoadMoreListener(OnLoadMoreListener {_vm.basePageAction.loadMore() })

        adapter!!.loadMoreModule.isAutoLoadMore = true

        recycler.adapter = adapter


        adapter!!.loadMoreModule.isEnableLoadMore = false

        showProgressingDialog()
        _vm.basePageAction.refreshData()

        emptyView = EmptyPageLayout(visitActivity(), DefaultEmptyPage())
        emptyView!!.setEmptyType(EmptyType.EmptyPageType())

        var emptyPage = DefaultEmptyPage()
        netErrorView = EmptyPageLayout(visitActivity(),emptyPage)
        netErrorView!!.setEmptyType(EmptyType.NetWorkErrorType())
        netErrorView!!.setButtonClickListener(object :AbsEmptyPage.OnRefreshDelegate{
            override fun onRefresh() {
                showProgressingDialog()
                _vm.basePageAction.refreshData()
            }
        })
        _vm.pageActionEvent.observe(requireActivity(), Observer {
            when(it){
                is PageStateActionEvent.EmptyPageState -> adapter!!.setEmptyView(emptyView!!)
                is PageStateActionEvent.LoadFailState -> adapter!!.setEmptyView(netErrorView!!)
                is PageStateActionEvent.LastDataState -> {
                    //如果不够一页,显示没有更多数据布局
                    adapter!!.loadMoreModule.loadMoreEnd()
                }
                is PageStateActionEvent.LoadMoreCompletedState -> {
                    adapter!!.loadMoreModule.loadMoreComplete()

                }
                is PageStateActionEvent.RefreshCompletedState -> {
                    refreshLayout.finishRefresh()
                }
                is PageStateActionEvent.DataState<*> -> {
                    adapter!!.loadMoreModule.isEnableLoadMore = true
                    if(it.isRefreshLast){
                        val entitys: MutableList<$entity> = ArrayList()
                        entitys.addAll(it.data as List<$entity>)
                        adapter!!.setNewInstance(entitys)
                    }else{
                        adapter!!.addData(it.data as List<$entity>)
                    }
                }
                is PageStateActionEvent.MoreLoadFailState -> {
                    adapter!!.loadMoreModule.isEnableLoadMore = true

                    adapter!!.loadMoreModule.loadMoreFail()
                }
            }
        })

    }

    var emptyView:EmptyPageLayout? = null
    var netErrorView:EmptyPageLayout? = null

    override fun createVMInstance() = $viewModelClass()

    var adapter: $adapterClass? = null

}
""".trimIndent()