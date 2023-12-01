package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import java.math.BigInteger

typealias Line = LineN<Point>
typealias Line3D = LineN<Point3D>
typealias LineI = LineN<PointI>
typealias Line3DI = LineN<Point3DI>
typealias LineL = LineN<PointL>
typealias Line3DL = LineN<Point3DL>

data class LineN<P : PointN<P, *>>(val start: P, val end: P) : Collection<P> {
	val diff = end - start;
	val step = diff.discreteAngle()
	val length = diff.manDist().intDivBy(step.manDist())
	override val size = length + 1

	override fun toString(): String {
		return "LineN(start=$start, end=$end, diff=$diff, step=$step, length=$length)"
	}

	override operator fun contains(element: P): Boolean = when (element) {
		start -> true
		end -> true
		// if it's on the extension of the line, but not on the line itself
		//  then one of these 2 discrete angles will be the opposite of the step
		else -> (element - start).discreteAngle() == step &&
				(end - element).discreteAngle() == step
	}

	override fun containsAll(elements: Collection<P>): Boolean = elements.all { it in this }

	override fun isEmpty(): Boolean = false

	override fun iterator(): Iterator<P> = object : Iterator<P> {
		var prev:P? = null
		var next = start

		override fun hasNext(): Boolean = this.prev != end
		override fun next(): P {
			val result = this.next
			this.prev = result
			this.next += step
			return result
		}
	}
}

infix fun <P : PointN<P, *>> P.toL(other: P) = LineN(this, other)


@JvmName("lineIsHorizontal")
fun Line.isHorizontal() = Clock.up.dot(start) == Clock.up.dot(end)

@JvmName("lineIsVertical")
fun Line.isVertical() = Clock.right.dot(start) == Clock.right.dot(end)

@JvmName("lineIsAxisAligned")
fun Line.isAxisAligned() = diff.x == 0.s || diff.y == 0.s

@JvmName("line3DIsAxisAligned")
fun Line3D.isAxisAligned() = diff.x == 0.s || diff.y == 0.s || diff.z == 0.s

@JvmName("lineIIsHorizontal")
fun LineI.isHorizontal() = if (Clock.eX != 0) diff.y == 0 else diff.x == 0

@JvmName("lineIIsVertical")
fun LineI.isVertical() = if (Clock.nX != 0) diff.y == 0 else diff.x == 0

@JvmName("lineIIsAxisAligned")
fun LineI.isAxisAligned() = diff.x == 0 || diff.y == 0

@JvmName("lineI3DIsAxisAligned")
fun Line3DI.isAxisAligned() = diff.x == 0 || diff.y == 0 || diff.z == 0

@JvmName("lineLIsHorizontal")
fun LineL.isHorizontal() = if (Clock.eX != 0) diff.y == 0L else diff.x == 0L

@JvmName("lineLIsVertical")
fun LineL.isVertical() = if (Clock.nX != 0) diff.y == 0L else diff.x == 0L

@JvmName("lineLIsAxisAligned")
fun LineL.isAxisAligned() = diff.x == 0L || diff.y == 0L

@JvmName("line3DLIsAxisAligned")
fun Line3DL.isAxisAligned() = diff.x == 0L || diff.y == 0L || diff.z == 0L


private fun Comparable<*>.intDivBy(other: Comparable<*>) = when (this) {
	is Int -> (this / other as Int)
	is Long -> (this / other as Long).s.i
	is Double -> (this / other as Double).toInt()
	is Float -> (this / other as Float).toInt()
	is BigInteger -> (this / other as BigInteger).intValueExact()
	is Sint -> (this / other as Sint).i
	else -> error("Unknown type")
}
