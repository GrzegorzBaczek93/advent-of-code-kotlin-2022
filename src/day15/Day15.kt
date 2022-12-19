package day15

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min
import readInput
import utils.withStopwatch

fun main() {
    val testInput = readInput("input15_test")
    withStopwatch { println(part1(testInput, 10)) }
    withStopwatch { println(part2(testInput, 0..20)) }

    val input = readInput("input15")
    withStopwatch { println(part1(input, 2000000)) }
    withStopwatch { println(part2(input, 0..4000000)) }
}

private fun part1(input: List<String>, yLine: Int): Int {
    val map = mutableMapOf<Position, Field>()
    parse(input).forEach { (sensor, beacon) ->
        val radius = calculateDistance(sensor, beacon)

        val absDistance = (sensor.y - yLine).absoluteValue
        val xRange = sensor.x - (radius - absDistance)..sensor.x + (radius - absDistance)
        for (x in xRange) {
            val position = Position(x, yLine)
            when {
                position == sensor -> map[position] = Field.Sensor
                position == beacon -> map[position] = Field.Beacon
                map.containsKey(position) && map[position] != Field.Coverage -> {}
                else -> map[position] = Field.Coverage
            }
        }
    }
    return map.count { (_, field) -> field == Field.Coverage }
}

private fun part2(input: List<String>, range: IntRange): Long {
    val parsed = parse(input)

    for (x in range) {
        val ranges = mutableSetOf<IntRange>()
        parsed.forEach { (sensor, beacon) ->
            val radius = calculateDistance(sensor, beacon)

            val positiveXEdge = min(sensor.x + radius, range.last)
            val negativeXEdge = max(sensor.x - radius, range.first)
            if (x !in negativeXEdge..positiveXEdge) return@forEach

            val negativeYEdge = max(sensor.y - (radius - (x - sensor.x).absoluteValue), range.first)
            val positiveYEdge = min(sensor.y + (radius - (x - sensor.x).absoluteValue), range.last)

            ranges.add(negativeYEdge..positiveYEdge)
        }
        val sorted = ranges.sortedBy { it.first }
        sorted.reduce { acc, intRange ->
            when {
                acc.last >= intRange.first - 1 && acc.last >= intRange.last -> acc
                acc.last >= intRange.first - 1 && acc.last < intRange.last -> acc.first..intRange.last
                else -> return x.toLong() * 4000000 + (acc.last + 1)
            }
        }
    }
    return -1
}

private fun parse(input: List<String>) =
    input.map { line ->
        line.split(':')
            .map { halfLine ->
                halfLine.split(',').map { chunk -> chunk.filter { it.isDigit() || it == '-' }.toInt() }
            }
            .map { (x, y) -> Position(x, y) }
    }.map { (sensor, beacon) -> sensor to beacon }

private fun calculateDistance(sensor: Position, beacon: Position): Int =
    (sensor.x - beacon.x).absoluteValue + (sensor.y - beacon.y).absoluteValue

private data class Position(
    val x: Int,
    val y: Int,
)

private sealed class Field {
    object Sensor : Field() {
        override fun toString(): String = "S"
    }

    object Beacon : Field() {
        override fun toString(): String = "B"
    }

    object Coverage : Field() {
        override fun toString(): String = "#"
    }
}