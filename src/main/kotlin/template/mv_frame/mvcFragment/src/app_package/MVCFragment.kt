package template.mv_frame.mvcFragment.src.app_package

fun mvcFragmentKt(
        className: String,
        packageName: String,
        layoutName: String,
        remark:String
) = """
    package $packageName.ui.fragment

    import android.content.Context
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import com.gyf.immersionbar.ktx.immersionBar
    import com.kangraoo.basektlib.ui.BActivity
    import com.kangraoo.basektlib.widget.toolsbar.LibToolBarOptions
    import com.kangraoo.basektlib.widget.toolsbar.OnLibToolBarListener
    import $packageName.R;
    import com.kangraoo.basektlib.ui.BFragment
    import kotlinx.android.synthetic.main.$layoutName.*

    /**
     * 自动生成：by WaTaNaBe on {{now}}
     * #{{remarks}}#
     */
    class $className : BFragment(){

        companion object{

            @JvmStatic
            fun newInstance() = $className()
            
        }

        override fun getLayoutId() = R.layout.$layoutName


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
           super.onViewCreated(view, savedInstanceState)
        }

    }
""".trimIndent()