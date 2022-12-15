package day14

data class Position(
    val x: Int,
    val y: Int,
) {
    fun getTop() = Position(x - 1, y)
    fun getBottom() = Position(x, y + 1)
    fun getLeft() = Position(x, y - 1)
    fun getRight() = Position(x, y + 1)

    fun getBottomLeft() = Position(x - 1, y + 1)
    fun getBottomRight() = Position(x + 1, y + 1)
}
