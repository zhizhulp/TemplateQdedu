package util

import java.text.SimpleDateFormat
import java.util.*


fun Date.timeNow() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())