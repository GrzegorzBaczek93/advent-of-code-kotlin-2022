package day11

sealed class OperationParam {
    object Old : OperationParam()
    data class Const(val number: Long) : OperationParam()

    companion object {
        fun from(input: String): OperationParam = when {
            input.contains(Regex("\\d")) -> Const(input.toLong())
            else -> Old
        }
    }
}
