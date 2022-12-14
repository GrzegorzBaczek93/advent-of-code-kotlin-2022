package day13

fun Node.compare(other: Node): Int {
    return when {
        this is Node.NodeList && other is Node.NodeList -> this.compare(other)
        this is Node.NodeList && other is Node.NodeValue -> this.compare(other)
        this is Node.NodeValue && other is Node.NodeList -> this.compare(other)
        this is Node.NodeValue && other is Node.NodeValue -> this.compare(other)
        else -> error("If this will ever happen, there is no god on this planet.")
    }
}

private fun Node.NodeList.compare(other: Node.NodeList): Int {
    var index = 0

    while (index < this.list.size && index < other.list.size) {
        when (this.list[index].compare(other.list[index])) {
            -1 -> return -1
            1 -> return 1
            0 -> {}
        }
        index++
    }

    return when {
        this.list.size < other.list.size -> -1
        this.list.size == other.list.size -> 0
        else -> 1
    }
}

private fun Node.NodeList.compare(other: Node.NodeValue): Int {
    return this.compare(other.toNodeList())
}

private fun Node.NodeValue.compare(other: Node.NodeList): Int {
    return this.toNodeList().compare(other)
}

private fun Node.NodeValue.compare(other: Node.NodeValue): Int {
    return when {
        this.value < other.value -> -1
        this.value > other.value -> 1
        else -> 0
    }
}
