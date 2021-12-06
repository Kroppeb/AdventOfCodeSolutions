package helpers

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

infix fun <P : PointN<P>> P.lineInc(other: P) = LineN(this, other)