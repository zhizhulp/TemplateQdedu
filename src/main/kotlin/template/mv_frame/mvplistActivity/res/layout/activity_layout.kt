package template.mv_frame.mvplistActivity.res.layout

fun mvpActivityListAXml(
        packageName:String,
        activityClass:String
) ="""
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
	xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="$packageName.ui.activity.$activityClass">

    <include
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        layout="@layout/lib_include_titlebar"
        />

    <com.scwang.smart.refresh.layout.SmartRefreshLayout
        android:id="@+id/refreshLayout"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        >

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recycler"
            style="@style/public_common_basic_recyclerView" />

    </com.scwang.smart.refresh.layout.SmartRefreshLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()