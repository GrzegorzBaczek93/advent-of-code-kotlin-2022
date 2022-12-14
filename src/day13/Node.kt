package day13

sealed class Node : Comparable<Node> {
    data class NodeList(val list: List<Node>) : Node() {
        override fun compareTo(other: Node): Int {
            return this.compare(other)
        }
    }

    data class NodeValue(val value: Int) : Node() {
        fun toNodeList(): NodeList {
            return NodeList(listOf(this))
        }

        override fun compareTo(other: Node): Int {
            return this.compare(other)
        }
    }
}