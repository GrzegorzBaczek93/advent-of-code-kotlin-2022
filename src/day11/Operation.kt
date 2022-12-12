package day11

enum class Operation {
    Addition {
        override fun execute(n1: Long, n2: Long): Long = n1 + n2
    },
    Multiplication {
        override fun execute(n1: Long, n2: Long): Long = n1 * n2
    };

    abstract fun execute(n1: Long, n2: Long): Long

    companion object {
        fun from(input: String) =
            when {
                input.contains('+') -> Addition
                input.contains('*') -> Multiplication
                else -> error("Unsupported operation")
            }
    }
}
