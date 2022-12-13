package day12

import readInput
import utils.withStopwatch
import java.util.LinkedList
import kotlin.math.absoluteValue

fun main() {
    val testInput = readInput("input12_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input12")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = solve1(input)

private fun part2(input: List<String>) = solve2(input)

private fun solve1(input: List<String>): Int {
    val graph = Graph(input)
    val startNode = graph.getStartNode()
    val endNode = graph.getEndNode()

    return calculateBFS(startNode, endNode)?.size ?: -1
}

private fun solve2(input: List<String>): Int {
    val graph = Graph(input)
    val startNodes = graph.getNodes(1)
    val endNode = graph.getEndNode()

    return startNodes.mapNotNull {
        calculateBFS(it, endNode).also { graph.clearNodes() }?.size
    }.min()
}

private fun calculateBFS(startNode: Node, endNode: Node): List<Node>? {
    val queue = LinkedList<Node>()
    val visitedNodes = mutableSetOf<Node>()

    visitedNodes.add(startNode)
    queue.add(startNode)

    while (queue.isNotEmpty()) {
        val currentNode = queue.poll()

        if (currentNode == endNode) {
            val path = mutableListOf<Node>()
            var current: Node? = currentNode
            while (current != null) {
                path.add(current)
                current = current.parent
            }
            return path.reversed()
        }

        currentNode.edges.forEach { edge ->
            if (!visitedNodes.contains(edge)) {
                visitedNodes.add(edge)
                queue.add(edge)
                edge.parent = currentNode
            }
        }
    }
    return null
}
