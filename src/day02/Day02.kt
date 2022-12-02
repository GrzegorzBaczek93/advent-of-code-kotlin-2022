package day02

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input02_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input02")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input.sumOf { it.resolvePointsPart1() }

private fun part2(input: List<String>) = input.sumOf { it.resolvePointsPart2() }

private fun String.resolvePointsPart1() = when (this) {
    "A X" -> 4 // Rock - Rock = draw
    "A Y" -> 8 // Rock - Paper = win
    "A Z" -> 3 // Rock - Scissors = lose
    "B X" -> 1 // Paper - Rock = lose
    "B Y" -> 5 // Paper - Paper = draw
    "B Z" -> 9 // Paper - Scissors = win
    "C X" -> 7 // Scissors - Rock = win
    "C Y" -> 2 // Scissors - Paper = lose
    "C Z" -> 6 // Scissors - Scissors = draw
    else -> throw UnsupportedOperationException()
}

private fun String.resolvePointsPart2() = when (this) {
    "A X" -> 3 // Rock - Scissors = lose
    "A Y" -> 4 // Rock - Rock = draw
    "A Z" -> 8 // Rock - Paper = win
    "B X" -> 1 // Paper - Rock = lose
    "B Y" -> 5 // Paper - Paper = draw
    "B Z" -> 9 // Paper - Scissors = win
    "C X" -> 2 // Scissors - Paper = lose
    "C Y" -> 6 // Scissors - Scissors = draw
    "C Z" -> 7 // Scissors - Rock = win
    else -> throw UnsupportedOperationException()
}
