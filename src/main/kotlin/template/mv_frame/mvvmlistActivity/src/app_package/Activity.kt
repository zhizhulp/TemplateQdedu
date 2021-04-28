package template.mv_frame.mvvmlistActivity.src.app_package

fun mvvmListActivityKt(
        packageName: String,
        viewModelClass: String,
        layoutName: String,
        adapterClass: String,
        now: String,
        remarks: String,
        activityClass: String,
        entity:String
) ="""
package $packageName.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ktx.immersionBar
import com.kangraoo.basektlib.ui.BActivity
import com.kangraoo.basektlib.widget.toolsbar.LibToolBarOptions
import com.kangraoo.basektlib.widget.toolsbar.OnLibToolBarListener
import $packageName.R
import kotlinx.android.synthetic.main.$layoutName.*
import $packageName.ui.viewmodel.$viewModelClass
import com.kangraoo.basektlib.ui.mvvm.BVMActivity
import $packageName.ui.adapter.$adapterClass
import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarListener
import com.qdedu.baselibcommon.widget.toolsbar.CommonToolBarOptions
import com.kangraoo.basektlib.widget.emptypage.AbsEmptyPage
import com.kangraoo.basektlib.widget.emptypage.DefaultEmptyPage
import com.kangraoo.basektlib.widget.emptypage.EmptyPageLayout
import com.kangraoo.basektlib.widget.emptypage.EmptyType
import com.kangraoo.basektlib.ui.impl.PageStateActionEvent
import android.app.Activity
import android.graphics.Color
import android.util.TypedValue
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.gyf.immersionbar.ktx.immersionBar
import com.kangraoo.basektlib.tools.launcher.LibActivityLauncher
import $packageName.data.model.responses.$entity
/**
 * 自动生成：by WaTaNaBe on $now
 * #$remarks#
 */
class $activityClass : BVMActivity<$viewModelClass>() {
    companion object{

        fun startFrom(activity: Activity) {
            LibActivityLauncher.instance
                .launch(activity, $activityClass::class.java)
        }

    }

    override fun getLayoutId() = R.layout.$layoutName


    override fun onViewCreated(savedInstanceState: Bundle?) {
        immersionBar {
            statusBarDarkFont(true)
            statusBarColor(R.color.color_white)
        }
        val libToolBarOptions = CommonToolBarOptions()
        libToolBarOptions.titleString = "$remarks"
        libToolBarOptions.setNeedNavigate(true)
        setToolBar(R.id.toolbar, libToolBarOptions, object : CommonToolBarListener(){})
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
		_vm.pageActionEvent.observe(this, Observer {
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
                        adapter!!.setNewInstance(data.toMutableList())
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