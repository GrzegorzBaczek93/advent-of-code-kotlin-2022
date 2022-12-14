package day14

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input14_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input14")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input

private fun part2(input: List<String>) = input
