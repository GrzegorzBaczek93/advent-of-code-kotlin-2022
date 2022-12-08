package day08

import readInput
import utils.withStopwatch
import kotlin.math.max

fun main() {
    val testInput = readInput("input08_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input08")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>): Int {
    val height = input.size
    val width = input.first().length

    val edgesSize = (height * 2) + (width * 2) - 4 // 4 is extra corners
    var midSize = 0

    calculate(input) { top, bottom, start, end ->
        if (top is Result.NotFound || bottom is Result.NotFound || start is Result.NotFound || end is Result.NotFound) {
            midSize++
        }
    }

    return edgesSize + midSize
}

private fun part2(input: List<String>): Int {
    var maxSize = 0

    calculate(input) { top, bottom, start, end ->
        (top.distance * bottom.distance * start.distance * end.distance).let {
            maxSize = max(it, maxSize)
        }
    }

    return maxSize
}

private fun calculate(input: List<String>, onEach: (Result, Result, Result, Result) -> Unit) {
    val height = input.size
    val width = input.first().length

    fun getDistanceFromTop(i: Int, j: Int): Result {
        var distance = 0
        (i - 1 downTo 0).forEach {
            distance++
            if (input[it][j] >= input[i][j]) {
                return Result.Found(distance)
            }
        }
        return Result.NotFound(distance)
    }

    fun getDistanceFromBottom(i: Int, j: Int): Result {
        var distance = 0
        (i + 1 until height).forEach {
            distance++
            if (input[it][j] >= input[i][j]) {
                return Result.Found(distance)
            }
        }
        return Result.NotFound(distance)
    }

    fun getDistanceFromStart(i: Int, j: Int): Result {
        var distance = 0
        (j - 1 downTo 0).forEach {
            distance++
            if (input[i][it] >= input[i][j]) {
                return Result.Found(distance)
            }
        }
        return Result.NotFound(distance)
    }

    fun getDistanceFromEnd(i: Int, j: Int): Result {
        var distance = 0
        (j + 1 until width).forEach {
            distance++
            if (input[i][it] >= input[i][j]) {
                return Result.Found(distance)
            }
        }
        return Result.NotFound(distance)
    }

    for (i in 1 until height - 1) {
        for (j in 1 until width - 1) {
            onEach(
                getDistanceFromTop(i, j),
                getDistanceFromBottom(i, j),
                getDistanceFromStart(i, j),
                getDistanceFromEnd(i, j),
            )
        }
    }
}

private sealed class Result(
    open val distance: Int,
) {
    data class Found(override val distance: Int) : Result(distance)
    data class NotFound(override val distance: Int) : Result(distance)
}
