package day13

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import readInput
import utils.multiply
import utils.withStopwatch

fun main() {
    val testInput = readInput("input13_test")
    withStopwatch { println(part1(testInput)) }
    withStopwatch { println(part2(testInput + listOf("[[2]]", "[[6]]"))) }

    val input = readInput("input13")
    withStopwatch { println(part1(input)) }
    withStopwatch { println(part2(input + listOf("[[2]]", "[[6]]"))) }
}

private fun part1(input: List<String>) = input.windowed(2, 3)
    .mapIndexed { index, data -> index + 1 to compare(data) }
    .filter { it.second == 1 }
    .sumOf { it.first }

private fun part2(input: List<String>) = input.asSequence().filter { it.isNotBlank() }
    .map { decode(it) }
    .sorted()
    .mapIndexed { index, node -> index + 1 to node }
    .filter {
        it.second == Node.NodeList(listOf(Node.NodeList(listOf(Node.NodeValue(2))))) ||
            it.second == Node.NodeList(listOf(Node.NodeList(listOf(Node.NodeValue(6)))))
    }
    .map { it.first }.toList()
    .multiply()

private fun compare(input: List<String>): Int {
    val (first, second) = input
    return decode(first).compare(decode(second))
}

private fun decode(input: String): Node = parseJson(Json.parseToJsonElement(input))

private fun parseJson(jsonElement: JsonElement): Node {
    return when (jsonElement) {
        is JsonArray -> Node.NodeList(jsonElement.map { parseJson(it) })
        is JsonPrimitive -> Node.NodeValue(jsonElement.int)
        else -> error("Unknown element")
    }
}
