package day01

import readInput
import utils.split
import utils.withStopwatch

fun main() {
    val testInput = readInput("input01_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input01")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>): Int = listOfSums(input).maxOf { it }

private fun part2(input: List<String>): Int = listOfSums(input).sorted().takeLast(3).sum()

private fun listOfSums(input: List<String>): List<Int> = input.split { it.isNotBlank() }.map { it.sumOf { it.toInt() } }