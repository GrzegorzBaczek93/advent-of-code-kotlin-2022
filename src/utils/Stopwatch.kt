package utils

import java.text.SimpleDateFormat
import java.util.*

fun withStopwatch(action: () -> Unit) {
    val startTime = System.currentTimeMillis()
    action()
    printTime(System.currentTimeMillis() - startTime)
}

private fun printTime(millis: Long) = println("Action took: ${formatMilliseconds(millis)}")

private fun formatMilliseconds(millis: Long): String {
    val dateFormat = SimpleDateFormat("ss:SSSS")
    return dateFormat.format(Date(millis))
}
