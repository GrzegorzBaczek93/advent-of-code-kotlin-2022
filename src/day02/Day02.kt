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
    calculateResult(game.first().toShape(), game.last().toShape())
}

private fun part2(input: List<String>) = input.sumOf { game ->
    val shapeA = game.first().toShape()
    calculateResult(shapeA, game.last().toShape(shapeA.winningShape, shapeA.drawingShape, shapeA.losingShape))
}

private fun Char.toShape(x: Shape = Shape.Rock, y: Shape = Shape.Paper, z: Shape = Shape.Scissors) = when (this) {
    'A' -> Shape.Rock
    'B' -> Shape.Paper
    'C' -> Shape.Scissors
    'X' -> x
    'Y' -> y
    'Z' -> z
    else -> throw IllegalArgumentException()
}

private enum class Shape(val points: Int) {
    Rock(1) {
        override val winningShape: Shape
            get() = Scissors

        override val losingShape: Shape
            get() = Paper
    },
    Paper(2) {
        override val winningShape: Shape
            get() = Rock

        override val losingShape: Shape
            get() = Scissors
    },
    Scissors(3) {
        override val winningShape: Shape
            get() = Paper

        override val losingShape: Shape
            get() = Rock
    };

    abstract val winningShape: Shape
    abstract val losingShape: Shape
    val drawingShape: Shape
        get() = this
}

private enum class MatchResult(val points: Int) {
    Win(6), Draw(3), Lose(0);
}

private fun calculateResult(shapeA: Shape, shapeB: Shape): Int =
    when (shapeB) {
        shapeA.winningShape -> MatchResult.Lose.points + shapeB.points
        shapeA.drawingShape -> MatchResult.Draw.points + shapeB.points
        shapeA.losingShape -> MatchResult.Win.points + shapeB.points
        else -> throw UnsupportedOperationException()
    }
