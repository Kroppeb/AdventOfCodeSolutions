package helpers

import grid.Clock

typealias Line = LineN<Point>
typealias Line3D = LineN<Point3D>

data class LineN<P : PointN<P>>(val start: P, val end: P) : Iterable<P> {
    val diff = end - start;

    override fun iterator(): Iterator<P> = object : Iterator<P> {
        var current = start
        val step = diff.discreteAngle()

        override fun hasNext(): Boolean = this.current != end
        override fun next(): P {
            val result = this.current
            this.current += this.step
            return result
        }
    }

}

infix fun <P : PointN<P>> P.toL(other: P) = LineN(this, other)

fun Line.isHorizontal() = if (Clock.eX != 0) diff.y == 0 else diff.x == 0
fun Line.isVertical() = if (Clock.nX != 0) diff.y == 0 else diff.x == 0
fun Line.isAxisAligned() = diff.x == 0 || diff.y == 0
@JvmName("isAxisAligned3D")
fun Line3D.isAxisAligned() = diff.x == 0 || diff.y == 0 || diff.z == 0

