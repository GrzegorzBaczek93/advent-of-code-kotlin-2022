package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
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

    val testInput = readInput("input01_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("input01")
    println(part1(input))
    println(part2(input))
}
