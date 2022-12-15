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

private fun part1(input: List<String>) = solve1(input)

private fun part2(input: List<String>) = solve2(input)

private fun solve1(input: List<String>) {
    val playground = Playground()
    input.forEach { playground.addFromPath(it, Segment.Rock) }

    var finished = false
    var i = 0
    while (!finished) {
        finished = playground.generateSand(endYIndex = playground.getRanges().second.y)
        i++
    }
    println("Number of sand: ${i - 1}")
}

private fun solve2(input: List<String>) {
    val playground = Playground()
    input.forEach { playground.addFromPath(it, Segment.Rock) }

    var finished = false
    var i = 0
    while (!finished) {
        finished = playground.generateSand()
        i++
    }
    println("Number of sand: $i")
}

private fun print(playground: Playground) {
    val (min, max) = playground.getRanges()
    val map = playground.getMap()

    (0..max.y + 2).forEach { y ->
        (min.x - 2..max.x + 2).forEach { x ->
            val current = Position(x, y)
            when {
                current == Position(500, 0) -> print(" + ")
                map.containsKey(current) -> print(" ${map[current]!!.symbol} ")
                else -> print(" . ")
            }
        }
        println()
    }
}
