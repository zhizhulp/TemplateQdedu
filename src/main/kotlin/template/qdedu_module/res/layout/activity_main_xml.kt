package template.qdedu_module.res.layout

fun mainActivityLayout(
        mainActivityName:String
)="""
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context="debug.ui.$mainActivityName">
        
        <include layout="@layout/lib_include_titlebar"/>
        <com.kangraoo.basektlib.widget.debug.LibDebugView
            android:id="@+id/debug"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
            
        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recycle"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

    </LinearLayout>
""".trimIndent()