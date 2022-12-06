package day06

import readInput
import utils.withStopwatch
import utils.containsDuplicates

fun main() {
    val testInput = readInput("input06_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input06")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input.first().solve(4)

private fun part2(input: List<String>) = input.first().solve(14)

private fun String.solve(packageSize: Int): Int {
    var startIndex = 0
    while (startIndex + packageSize <= length) {
        val chunk = substring(startIndex, startIndex + packageSize)
        if (chunk.containsDuplicates().not()) {
            return startIndex + packageSize
        }
        startIndex++
    }

    return -1
}
