package day14

class Playground {
    private val map = mutableMapOf<Position, Segment>()
    private val sandStartPosition = Position(500, 0)
    private var floor = 0

    fun addFromPath(path: String, type: Segment) {
        parseToPositions(path).windowed(2).forEach { (start, end) -> add(start, end, type) }
        floor = getFloor()
    }

    fun getMap() = map

    fun getRanges(): Pair<Position, Position> {
        val minX = map.minBy { it.key.x }.key.x
        val maxX = map.maxBy { it.key.x }.key.x
        val minY = map.minBy { it.key.y }.key.y
        val maxY = map.maxBy { it.key.y }.key.y

        return Position(minX, minY) to Position(maxX, maxY)
    }

    fun generateSand(maxDepth: Int = floor, endYIndex: Int = -1): Boolean {
        var currentPosition = sandStartPosition

        while (currentPosition.y < maxDepth) {
            val nextPosition = getNextPosition(currentPosition)
            when {
                nextPosition.y == endYIndex -> return true
                nextPosition == currentPosition -> { if (nextPosition == sandStartPosition) return true else break }
                else -> { currentPosition = nextPosition }
            }
        }

        map[currentPosition] = Segment.Sand

        return false
    }

    private fun getNextPosition(current: Position): Position {
        if (isSegmentEmpty(current.getBottom())) return current.getBottom()
        if (isSegmentEmpty(current.getBottomLeft())) return current.getBottomLeft()
        if (isSegmentEmpty(current.getBottomRight())) return current.getBottomRight()
        return current
    }

    private fun getFloor() = map.maxBy { it.key.y }.key.y + 1

    private fun isSegmentEmpty(position: Position): Boolean {
        return map.contains(position).not()
    }

    private fun parseToPositions(path: String): List<Position> = path.split(" -> ")
        .map { pos -> pos.split(',').let { (x, y) -> Position(x.toInt(), y.toInt()) } }

    private fun add(start: Position, end: Position, type: Segment) {
        val xs = listOf(start.x, end.x).sorted()
        val ys = listOf(start.y, end.y).sorted()
        (xs.first()..xs.last()).forEach { x ->
            (ys.first()..ys.last()).forEach { y ->
                map[Position(x, y)] = type
            }
        }
    }
}