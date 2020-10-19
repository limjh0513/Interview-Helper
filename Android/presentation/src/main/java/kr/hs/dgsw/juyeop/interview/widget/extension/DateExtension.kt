package kr.hs.dgsw.juyeop.interview.widget.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.atFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format.format(Date())
}