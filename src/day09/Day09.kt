package day09

import readInput
import utils.withStopwatch
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val testInput = readInput("input09_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input09")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input.execute(2)

private fun part2(input: List<String>) = input.execute(10)

private fun List<String>.execute(ropeSize: Int = 1): Int {
    val tailVisitedPlaces = mutableSetOf(0 to 0)
    val rope = MutableList(ropeSize) { 0 to 0 }

    map { it.split(" ") }.forEach { (direction, length) ->
        repeat(length.toInt()) {
            (0 until ropeSize).forEach {index ->
                when (index) {
                    0 -> rope[index] = rope[index].resolvePosition(direction)
                    else -> rope[index] = rope[index].resolvePosition(rope[index - 1])
                }
            }
            tailVisitedPlaces.add(rope[ropeSize - 1])
        }
    }

    return tailVisitedPlaces.size
}

private fun Pair<Int, Int>.resolvePosition(direction: String): Pair<Int, Int> {
    val (x, y) = this
    return when (direction) {
        "R" -> x + 1 to y
        "L" -> x - 1 to y
        "D" -> x to y - 1
        "U" -> x to y + 1
        else -> error("Unsupported direction")
    }
}

private fun Pair<Int, Int>.resolvePosition(knot: Pair<Int, Int>): Pair<Int, Int> {
    val (tx, ty) = this
    val (kx, ky) = knot

    if (!shouldMoveTail(knot, this)) return this

    val diffX = tx - kx
    val newX = when {
        diffX > 0 -> tx - 1
        diffX < 0 -> tx + 1
        else -> tx
    }

    val diffY = ty - ky
    val newY = when {
        diffY > 0 -> ty - 1
        diffY < 0 -> ty + 1
        else -> ty
    }

    return newX to newY
}

private fun shouldMoveTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Boolean {
    val (hx, hy) = head
    val (tx, ty) = tail
    return sqrt((hx - tx.toDouble()).pow(2) + (hy - ty.toDouble()).pow(2)) >= 2
}
