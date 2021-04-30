package template.qdedu_module.module.androidManifest


fun moduleManifest(
        packageName:String,
        baseAppName:String,
        mainActivityName:String,
        appResName:String
)="""
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        package="$packageName">

        <application
            android:name="debug.${baseAppName}"
            android:allowBackup="true"
            android:icon="@drawable/ic_pages_lib_icon_100dp"
            android:label="@string/$appResName"
            android:supportsRtl="true"
            android:theme="@style/Lib.AppTheme"
            tools:replace="android:label">
            <activity android:name="debug.ui.$mainActivityName">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />

                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>

        </application>

    </manifest>
""".trimIndent()