package day08

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input08_test")
    check(part1(testInput) == 1)

    val input = readInput("input08")
    println(part1(input))
    println(part2(input))
}
