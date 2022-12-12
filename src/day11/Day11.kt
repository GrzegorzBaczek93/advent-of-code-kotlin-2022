package day11

import readInput
import utils.multiply
import utils.split
import utils.withStopwatch

fun main() {
    val testInput = readInput("input11_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput)) }

    val input = readInput("input11")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input)) }
}

private fun part1(input: List<String>) = input.play(20, 3)

private fun part2(input: List<String>) = input.play(10000, 1)

private fun List<String>.play(numberOfGames: Int = 1, worryLevelDivisor: Int = 1): Long {
    val monkeys = this.split { it.isBlank() }.map { Monkey.fromString(it) }

    val normalizationDivisor = monkeys.map { it.getTestDivisor() }.multiply()

    fun List<Pair<Long, Int>>.distributeItems() {
        forEach { (item, monkeyId) ->
            monkeys.first { monkey -> monkey.id == monkeyId }.addItem(item)
        }
    }

    repeat(numberOfGames) {
        monkeys.forEach {
            it.inspect(worryLevelDivisor, normalizationDivisor).distributeItems()
        }
    }

    return monkeys
        .map { it.inspectionCounter }
        .sortedDescending().take(2)
        .multiply()
}
