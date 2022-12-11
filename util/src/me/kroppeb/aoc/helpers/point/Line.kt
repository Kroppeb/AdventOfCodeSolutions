package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.point.*

typealias Line = LineN<Point>
typealias Line3D = LineN<Point3D>
typealias LineL = LineN<PointL>
typealias Line3DL = LineN<Point3DL>

data class LineN<P : PointN<P, *>>(val start: P, val end: P) : Iterable<P> {
	val diff = end - start;

	override fun iterator(): Iterator<P> = object : Iterator<P> {
		var prev:P? = null
		var next = start
		val step = diff.discreteAngle()

		override fun hasNext(): Boolean = this.prev != end
		override fun next(): P {
			val result = this.next
			this.prev = result
			this.next += this.step
			return result
		}
	}
}

infix fun <P : PointN<P, *>> P.toL(other: P) = LineN(this, other)

@JvmName("lineIsHorizontal")
fun Line.isHorizontal() = if (Clock.eX != 0) diff.y == 0 else diff.x == 0

@JvmName("lineIsVertical")
fun Line.isVertical() = if (Clock.nX != 0) diff.y == 0 else diff.x == 0

@JvmName("lineIsAxisAligned")
fun Line.isAxisAligned() = diff.x == 0 || diff.y == 0

@JvmName("3DIsAxisAligned")
fun Line3D.isAxisAligned() = diff.x == 0 || diff.y == 0 || diff.z == 0

@JvmName("lineLIsHorizontal")
fun LineL.isHorizontal() = if (Clock.eX != 0) diff.y == 0L else diff.x == 0L

@JvmName("lineLIsVertical")
fun LineL.isVertical() = if (Clock.nX != 0) diff.y == 0L else diff.x == 0L

@JvmName("lineLIsAxisAligned")
fun LineL.isAxisAligned() = diff.x == 0L || diff.y == 0L

@JvmName("line3DLIsAxisAligned")
fun Line3DL.isAxisAligned() = diff.x == 0L || diff.y == 0L || diff.z == 0L
