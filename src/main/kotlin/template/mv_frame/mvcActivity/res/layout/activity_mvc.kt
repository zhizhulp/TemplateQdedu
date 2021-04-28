package template.mv_frame.mvcActivity.res.layout


fun mvcActivityXml(
        activityName: String,
        packageName: String,
) = """
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout 
    	xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="$packageName.ui.activity.$activityName">

        <include
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            layout="@layout/lib_include_titlebar"
            />

    </androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()