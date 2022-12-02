package day02

enum class Shape(val points: Int) {
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

fun Char.toShape(x: Shape = Shape.Rock, y: Shape = Shape.Paper, z: Shape = Shape.Scissors) = when (this) {
    'A' -> Shape.Rock
    'B' -> Shape.Paper
    'C' -> Shape.Scissors
    'X' -> x
    'Y' -> y
    'Z' -> z
    else -> throw IllegalArgumentException()
}
