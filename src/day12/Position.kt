package day12

data class Position(
    val x: Int,
    val y: Int,
) {
    fun getTop() = Position(x - 1, y)
    fun getBottom() = Position(x + 1, y)
    fun getLeft() = Position(x, y - 1)
    fun getRight() = Position(x, y + 1)
}
