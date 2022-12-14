package day05

import utils.removeLast

class Supplies private constructor() {
    private val supplies = mutableMapOf<Int, MutableList<Char>>()

    private fun init(input: List<String>) {
        input.forEach { line ->
            line.forEachIndexed { index, c ->
                if (c.isLetter()) {
                    supplies.getOrPut(calculateStackIndex(index)) { mutableListOf() }.add(0, c)
                }
            }
        }
    }

    fun compute(input: List<String>, keepOrder: Boolean): String {
        input.forEach { line ->
            val (amount, from, to) = parseAction(line)
            supplies[from]?.removeLast(amount)?.let { supplies[to]?.addAll(if (keepOrder) it.reversed() else it) }
        }
        return parseResult()
    }

    private fun calculateStackIndex(index: Int) = (index - 1) / 4 + 1

    private fun parseAction(input: String): List<Int> =
        input.filter { !it.isLetter() }.removePrefix(" ").split("  ").map { it.toInt() }

    private fun parseResult(): String {
        var result = ""
        supplies.toSortedMap().forEach { (_, u) -> result += if (u.isEmpty()) "" else u.last() }
        return result
    }

    companion object {
        fun from(input: List<String>): Supplies = Supplies().also { it.init(input) }
    }
}
