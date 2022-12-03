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
    val firstSlice = take(length / 2).toSet()

    takeLast(length / 2).forEach {
        if (firstSlice.contains(it)) {
            return it
        }
    }
    error("No common element for input")
}

private fun List<String>.findCommon(): Char {
    val firstSlice = get(0).toSet()
    val commonElements = mutableSetOf<Char>()

    get(1).forEach {
        if (firstSlice.contains(it)) {
            commonElements.add(it)
        }
    }
    get(2).forEach {
        if (commonElements.contains(it)) {
            return it
        }
    }
    error("No common element for input")
}

private fun Char.asNumber(): Int {
    return if (this.isLowerCase()) {
        this.code - 96
    } else {
        this.code - 38
    }
}
