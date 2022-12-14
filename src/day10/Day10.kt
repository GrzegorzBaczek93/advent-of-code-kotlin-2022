package day10

import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input10_test")
//    withStopwatch { part1(testInput) }
//    withStopwatch { part2(testInput) }

    val input = readInput("input10")
//    withStopwatch { part1(input) }
    withStopwatch { part2(input) }
}

private fun part1(input: List<String>): Int {
    val cyclesHistory = mutableMapOf(
        20 to 0,
        60 to 0,
        100 to 0,
        140 to 0,
        180 to 0,
        220 to 0,
    )
    var cycle = 0
    var x = 1

    input.forEach { line ->
        Command.from(line).execute(
            onEachCycle = {
                cycle++
                if (cyclesHistory.containsKey(cycle)) {
                    cyclesHistory[cycle] = cycle * x
                }
            },
            onCyclesEnd = { cmd ->
                if (cmd is Command.Addx) x += cmd.x
            }
        )
    }

    return cyclesHistory.values.sum()
}

private fun part2(input: List<String>) {
    var cycle = 0
    var x = 1

    input.forEach { line ->
        Command.from(line).execute(
            onEachCycle = {
                cycle += 1
                print(x = x, cycle = cycle)
            },
            onCyclesEnd = { cmd ->
                if (cmd is Command.Addx) x += cmd.x
            }
        )
    }
}

private fun print(x: Int, cycle: Int) {
    val char = if ((cycle - 1).mod(40) + 1 in (x..x + 2)) '#' else '.'
    if (cycle.mod(40) == 0) println(char) else print(char)
}

private sealed class Command {
    abstract val cycles: Int

    data class Addx(val x: Int = 0) : Command() {
        override val cycles: Int = 2
    }

    object Noop : Command() {
        override val cycles: Int = 1
    }

    companion object {
        fun from(line: String): Command {
            return when {
                line.startsWith("addx") -> Addx(line.split(" ").last().toInt())
                line.startsWith("noop") -> Noop
                else -> error("Unsupported operation")
            }
        }
    }
}

private fun Command.execute(onEachCycle: () -> Unit, onCyclesEnd: (Command) -> Unit) {
    repeat(cycles) {
        onEachCycle()
    }
    onCyclesEnd(this)
}
