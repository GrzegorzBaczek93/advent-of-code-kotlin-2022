package day12

import utils.walkIndexed

class Graph(
    input: List<String>,
) {
    private val nodes = mutableSetOf<Node>()

    init {
        createNodes(input)
        initializeEdges()
    }

    fun getStartNode() = nodes.first { it.isStart }

    fun getEndNode() = nodes.first { it.isEnd }

    fun getNodes(height: Int) = nodes.filter { it.height == height }

    fun clearNodes() = nodes.forEach { it.parent = null }

    private fun createNodes(input: List<String>) {
        input.walkIndexed { x, y, char ->
            nodes.add(Node.create(x, y, char))
        }
    }

    private fun initializeEdges() {
        fun Node.getNode(position: Position): Node? {
            return nodes.firstOrNull { it.position == position }?.let { possibleNode ->
                if ((possibleNode.height - height) <= 1) {
                    possibleNode
                } else {
                    null
                }
            }
        }

        nodes.forEach { node ->
            node.updateEdges(
                listOfNotNull(
                    node.getNode(node.position.getTop()),
                    node.getNode(node.position.getBottom()),
                    node.getNode(node.position.getLeft()),
                    node.getNode(node.position.getRight()),
                )
            )
        }
    }

    override fun toString(): String {
        return nodes.map { "$it\n" }.toString()
    }
}