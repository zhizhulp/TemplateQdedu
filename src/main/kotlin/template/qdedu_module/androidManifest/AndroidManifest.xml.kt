package template.qdedu_module.androidManifest


fun libManifest(
        packageName:String,
        activityName:String
)="""
    <?xml version="1.0" encoding="UTF-8"?>

    <manifest xmlns:android="http://schemas.android.com/apk/res/android" package="$packageName">  
        <application>
            <activity android:name="debug.ui.$activityName"/>
        </application>
    </manifest>

""".trimIndent()