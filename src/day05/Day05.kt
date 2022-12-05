package day05

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input05_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input05")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>): String {
    val (schema, actions) = input.parse()
    return Supplies.from(schema).compute(actions, keepOrder = true)
}

private fun part2(input: List<String>): String {
    val (schema, actions) = input.parse()
    return Supplies.from(schema).compute(actions, keepOrder = false)
}

private fun List<String>.parse(): Pair<List<String>, List<String>> {
    val firstActionIndex = indexOfFirst { it.startsWith("move") }
    return subList(0, firstActionIndex - 2) to subList(firstActionIndex, size)
}
