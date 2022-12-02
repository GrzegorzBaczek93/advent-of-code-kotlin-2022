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

private fun part1(input: List<String>) = input.sumOf { game ->
    val shapeA = game.first().toShape()
    val shapeB = game.last().toShape()
    calculateResult(shapeA, shapeB)
}

private fun part2(input: List<String>) = input.sumOf { game ->
    val shapeA = game.first().toShape()
    val shapeB = game.last().toShape(shapeA.winningShape, shapeA.drawingShape, shapeA.losingShape)
    calculateResult(shapeA, shapeB)
}

private fun calculateResult(shapeA: Shape, shapeB: Shape): Int =
    when (shapeB) {
        shapeA.winningShape -> MatchResult.Lose.points + shapeB.points
        shapeA.drawingShape -> MatchResult.Draw.points + shapeB.points
        shapeA.losingShape -> MatchResult.Win.points + shapeB.points
        else -> throw UnsupportedOperationException()
    }
