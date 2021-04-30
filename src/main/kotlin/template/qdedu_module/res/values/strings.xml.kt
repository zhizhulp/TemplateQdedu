package template.qdedu_module.res.values

fun stringXml(
        appResName:String,
        appName:String
)="""
    <resources >
        <string name="$appResName">$appName</string>
    </resources>
""".trimIndent()
