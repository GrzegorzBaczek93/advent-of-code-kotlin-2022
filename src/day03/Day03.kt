package day03

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input03_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input03")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input.sumOf { it.findCommon().asNumber() }

private fun part2(input: List<String>) = input.chunked(3).sumOf { it.findCommon().asNumber() }

private fun String.findCommon(): Char {
    val (s1, s2) = chunked(length / 2)
    return s2.first { s1.contains(it) }
}

private fun List<String>.findCommon(): Char {
    val (s1, s2, s3) = this
    val commonElements = mutableSetOf<Char>()

    s2.forEach { if (s1.contains(it)) commonElements.add(it) }
    return s3.first { commonElements.contains(it) }
}

private fun Char.asNumber(): Int {
    return if (this.isLowerCase()) {
        this.code - 96
    } else {
        this.code - 38
    }
}
