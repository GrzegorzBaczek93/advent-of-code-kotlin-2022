package day01

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input01_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input01")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>): Int {
    var biggestSum = 0
    var currentSum = 0

    fun check() {
        if (currentSum > biggestSum) biggestSum = currentSum
        currentSum = 0
    }

    input.forEach {
        when {
            it.isBlank() -> check()
            else -> currentSum += it.toInt()
        }
    }
    check()

    return biggestSum
}

private fun part2(input: List<String>): Int {
    val biggestSums = mutableListOf(0, 0, 0)
    var currentSum = 0

    fun check() {
        biggestSums.add(currentSum)
        biggestSums.remove(biggestSums.min())
        currentSum = 0
    }

    input.forEach {
        when {
            it.isBlank() -> check()
            else -> currentSum += it.toInt()
        }
    }
    check()

    return biggestSums.sum()
}