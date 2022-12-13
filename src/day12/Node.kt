package day12

import utils.asNumber

data class Node private constructor(
    val position: Position,
    val height: Int,
    val isStart: Boolean = false,
    val isEnd: Boolean = false,
) {
    var parent: Node? = null
    val edges: MutableList<Node> = mutableListOf()

    fun updateEdges(edges: List<Node>) {
        this.edges.clear()
        this.edges.addAll(edges)
    }

    override fun toString(): String {
        return "Node(position=$position, height=$height, edges=${edges.map { it.position to it.height }}"
    }

    companion object {
        fun create(x: Int, y: Int, char: Char): Node {
            fun getHeight(char: Char) = when {
                char.isLowerCase() -> char.asNumber()
                char == 'S' -> 'a'.asNumber()
                char == 'E' -> 'z'.asNumber()
                else -> error("Unsupported character")
            }

            fun isStart(char: Char) = char == 'S'

            fun isEnd(char: Char) = char == 'E'

            return Node(
                position = Position(x, y),
                height = getHeight(char),
                isStart = isStart(char),
                isEnd = isEnd(char)
            )
        }
    }
}
