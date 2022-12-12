package day11

import utils.component6

data class Monkey private constructor(
    val id: Int,
    private val items: MutableList<Long>,
    private val operation: Operation,
    private val operationFirstParam: OperationParam,
    private val operationSecondParam: OperationParam,
    private val testDivisor: Long,
    private val idForPositiveTest: Int,
    private val idForNegativeTest: Int,
) {

    var inspectionCounter: Long = 0
        private set

    fun getTestDivisor() = testDivisor

    fun inspect(worryLevelDivisor: Int, normalizationDivisor: Long): List<Pair<Long, Int>> {
        if (items.isEmpty()) return emptyList()

        val result = mutableListOf<Pair<Long, Int>>()

        items.forEach {
            inspectionCounter++
            val newWorryLevel = operation(it).mod(normalizationDivisor).div(worryLevelDivisor)
            val newId = test(newWorryLevel)
            result.add(newWorryLevel to newId)
        }
        items.clear()

        return result
    }

    fun addItem(item: Long) {
        items.add(item)
    }

    private fun test(worryLevel: Long): Int = if (worryLevel.mod(testDivisor) == 0L) {
        idForPositiveTest
    } else {
        idForNegativeTest
    }

    private fun operation(param: Long): Long {
        val first = resolveParam(param, operationFirstParam)
        val second = resolveParam(param, operationSecondParam)
        return operation.execute(first, second)
    }

    private fun resolveParam(param: Long, operationParam: OperationParam) =
        when (operationParam) {
            is OperationParam.Const -> operationParam.number
            OperationParam.Old -> param
        }

    companion object {
        fun fromString(input: List<String>): Monkey {
            val (
                name,
                startingItems,
                operation,
                test,
                positive,
                negative,
            ) = input

            return Monkey(
                id = getNumber(name).toInt(),
                items = getNumberList(startingItems).toMutableList(),
                operation = parseOperation(operation),
                operationFirstParam = OperationParam.Old,
                operationSecondParam = parseOperationParam(operation),
                testDivisor = getNumber(test).toLong(),
                idForPositiveTest = getNumber(positive).toInt(),
                idForNegativeTest = getNumber(negative).toInt(),
            )
        }

        private fun getNumber(name: String) = name.filter { it.isDigit() }

        private fun getNumberList(startingItems: String) =
            startingItems.split(',').map { dirty -> dirty.filter { it.isDigit() }.toLong() }

        private fun parseOperation(input: String): Operation = Operation.from(input)

        private fun parseOperationParam(input: String): OperationParam =
            input.split(" ").last().let { OperationParam.from(it) }
    }
}
