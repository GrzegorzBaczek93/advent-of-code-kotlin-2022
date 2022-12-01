package utils

import java.text.SimpleDateFormat
import java.util.*

fun withStopwatch(action: () -> Unit) {
    val startTime = System.currentTimeMillis()
    action()
    println("Action took: ${formatMilliseconds(System.currentTimeMillis() - startTime)}")
}

private fun formatMilliseconds(millis: Long): String {
    val dateFormat = SimpleDateFormat("ss:SSSS")
    return dateFormat.format(Date(millis))
}