package day07

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input07_test")
    withStopwatch { println(part1(testInput)) }
//    withStopwatch { println(part2(testInput)) }

    val input = readInput("input07")
    withStopwatch { println(part1(input)) }
//    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input.parse()

private fun part2(input: List<String>) = input.parse()

fun List<String>.parse() {
    val root = Node.Directory(parent = null, name = "/")
    var currentNode: Node.Directory = root

    fun parseCommand(line: String) {
        line.split(" ").let {
            when (it[1]) {
                "cd" -> {
                    currentNode = if (it[2] == "..") {
                        currentNode.parent!!
                    } else {
                        currentNode.nodes.first { node -> node.name == it[2] } as Node.Directory
                    }
                }

                "ls" -> {}
                else -> error("Unsupported operation!")
            }
        }
    }

    fun parseDirectory(line: String) {
        line.split(" ").let { (_, name) -> currentNode.nodes.add(Node.Directory(currentNode, name)) }
    }

    fun parseFile(line: String) {
        line.split(" ").let { (size, name) -> currentNode.nodes.add(Node.File(currentNode, name, size.toInt())) }
    }

    fun printTree(node: Node.Directory) {
        println(node)
        node.nodes.forEach {
            when (it) {
                is Node.Directory -> {
                    printTree(it)
                }

                is Node.File -> {}
            }
        }
    }

    fun calculateDirSize(node: Node.Directory): Int {
        var size = 0
        node.nodes.forEach {
            size += when (it) {
                is Node.Directory -> calculateDirSize(it)
                is Node.File -> it.size
            }
        }
        return size.also { node.size = size }
    }

    fun calcResultPart1(node: Node.Directory): Int {
        var size = 0
        if (node.size <= 100000) {
            size += node.size
        }
        node.nodes.forEach {
            if (it is Node.Directory) {
                size += calcResultPart1(it)
            }
        }
        return size
    }

    fun calcResultPart2(node: Node.Directory): Int {
        val requiredSize = 6975962
        var smallest = Int.MAX_VALUE
        var smallestSize = Int.MAX_VALUE

        node.nodes.forEach {
            if (it is Node.Directory) {
                val result = calcResultPart2(it)
                if (result - requiredSize in 1 until smallest) {
                    smallest = result - requiredSize
                    smallestSize = result
                }
            }
        }
        if (node.size - requiredSize in 1 until smallest) {
            smallest = node.size - requiredSize
            smallestSize = node.size
        }

        return smallestSize
    }

    drop(1).forEach { line ->
        when {
            line.startsWith("$") -> parseCommand(line)
            line.startsWith("dir") -> parseDirectory(line)
            else -> parseFile(line)
        }
    }

    println(calculateDirSize(root))
//    printTree(root)
    println(calcResultPart2(root))
}

sealed class Node(
    open val parent: Directory?,
    open val name: String,
) {
    data class Directory(
        override val parent: Directory?,
        override val name: String,
        val nodes: MutableList<Node> = mutableListOf(),
        var size: Int = 0,
    ) : Node(parent, name) {
        override fun toString(): String {
            return "Directory(parent=${parent?.name}, name=$name, size=$size, nodes=$nodes"
        }
    }

    data class File(override val parent: Directory?, override val name: String, val size: Int) : Node(parent, name) {
        override fun toString(): String {
            return "File(parent=${parent?.name}, name=$name, size=$size)"
        }
    }
}

/*
- / (dir)
  - a (dir)
    - e (dir)
      - i (file, size=584)
    - f (file, size=29116)
    - g (file, size=2557)
    - h.lst (file, size=62596)
  - b.txt (file, size=14848514)
  - c.dat (file, size=8504156)
  - d (dir)
    - j (file, size=4060174)
    - d.log (file, size=8033020)
    - d.ext (file, size=5626152)
    - k (file, size=7214296)
 */
