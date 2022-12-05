package utils

inline fun <T> List<T>.ifNotEmpty(action: (List<T>) -> Unit) {
    if (isNotEmpty()) action(this)
}

inline fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> {
    val resultList = mutableListOf<List<T>>()
    val buffer = mutableListOf<T>()

    for (element in this) {
        if (predicate(element)) {
            buffer.add(element)
        } else {
            buffer.ifNotEmpty { resultList.add(buffer.toList()) }
            buffer.clear()
        }
    }
    buffer.ifNotEmpty { resultList.add(buffer.toList()) }

    return resultList.toList()
}

/**
 * Removes n last entries from list and return it.
 */
inline fun <T> MutableList<T>.removeLast(n: Int): List<T> {
    if (isEmpty()) {
        return emptyList()
    }
    if (n >= size) {
        val removedItems = this.toList()
        clear()
        return removedItems
    }
    val removedItems = this.takeLast(n)
    repeat(n) { removeLast() }
    return removedItems
}
