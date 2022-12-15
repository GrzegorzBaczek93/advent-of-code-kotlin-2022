package day14

sealed class Segment {
    abstract val symbol: Char

    object Air : Segment() {
        override val symbol: Char = '.'
    }

    object Rock : Segment() {
        override val symbol: Char = '#'
    }

    object Sand: Segment() {
        override val symbol: Char = 'o'
    }
}
