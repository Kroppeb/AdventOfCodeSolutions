package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import kotlin.math.atan2
import kotlin.math.sign
import kotlin.math.sqrt

data class PointL(val x: Long, val y: Long) : PointNL<PointL> {
	// TODO: evaluate whether having these as lazy actually helps or hurts
	val right by lazy { x + Clock.eX toPL y + Clock.eY }
	val down by lazy { x - Clock.nX toPL y - Clock.nY }
	val left by lazy { x - Clock.eX toPL y - Clock.eY }
	val up by lazy { x + Clock.nX toPL y + Clock.nY }

	val north get() = up
	val east get() = right
	val south get() = down
	val west get() = left


	fun rotateClock() = Clock.rightI.toPointL() * Clock.upI.dot(this) + Clock.upI.toPointL() * Clock.leftI.dot(this)
	fun rotateAntiClock() = -rotateClock()

	fun getQuadNeighbours() = listOf(right, down, left, up)
	fun getDiagonalNeighbours() = listOf(right.down, left.down, left.up, right.up)
	fun getOctNeighbours() = listOf(right, right.down, down, left.down, left, left.up, up, right.up)

	fun getQuadNeighbourHood() = listOf(this, right, down, left, up)
	fun getDiagonalNeighbourHood() = listOf(this, right.down, left.down, left.up, right.up)
	fun getOctNeighbourHood() = listOf(this, right, right.down, down, left.down, left, left.up, up, right.up)

	override fun getMooreNeighbours() = getOctNeighbours()
	override fun getVonNeumannNeighbours() = getQuadNeighbours()

	override operator fun unaryMinus(): PointL = -x toPL -y

	override operator fun minus(other: PointL): PointL = x - other.x toPL y - other.y
	override operator fun plus(other: PointL): PointL = x + other.x toPL y + other.y

	operator fun minus(other: Char): PointL = this - other.toPointI()
	operator fun plus(other: Char): PointL = this + other.toPointI()


	override operator fun times(other: PointL): PointL = x * other.x toPL y * other.y
	override operator fun div(other: PointL): PointL = x / other.x toPL y / other.y
	override operator fun rem(other: PointL): PointL = x % other.x toPL y % other.y

	override operator fun times(other: Long): PointL = x * other toPL y * other
	override operator fun div(other: Long): PointL = x / other toPL y / other
	override operator fun rem(other: Long): PointL = x % other toPL y % other

	override fun abs() = kotlin.math.abs(x) toPL kotlin.math.abs(y)

	override fun sqrDist(): Long = x * x + y * y
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Long = kotlin.math.abs(x) + kotlin.math.abs(y)
	override fun chebyshevDist(): Long = kotlin.math.max(kotlin.math.abs(x), kotlin.math.abs(y))

	fun angle(): Double {
		return atan2(y.toDouble(), x.toDouble())
	}

	override fun gcd(): Long = me.kroppeb.aoc.helpers.gcd(kotlin.math.abs(x), kotlin.math.abs(y))

	override fun discreteAngle(): PointL {
		val g = gcd()
		return this / g
	}

	override fun min(other: PointL): PointL = kotlin.math.min(this.x, other.x) toPL kotlin.math.min(this.y, other.y)
	override fun max(other: PointL): PointL = kotlin.math.max(this.x, other.x) toPL kotlin.math.max(this.y, other.y)

	override fun dot(other: PointL) = this.x * other.x + this.y * other.y

	fun isLeftOf(other: PointL) = Clock.leftI.dot(this) > Clock.leftI.dot(other)
	fun isRightOf(other: PointL) = Clock.rightI.dot(this) > Clock.rightI.dot(other)
	fun isAbove(other: PointL) = Clock.upI.dot(this) > Clock.upI.dot(other)
	fun isBelow(other: PointL) = Clock.downI.dot(this) > Clock.downI.dot(other)
	fun sameLeftRight(other: PointL) = Clock.leftI.dot(this) == Clock.leftI.dot(other)
	fun sameUpDown(other: PointL) = Clock.upI.dot(this) == Clock.upI.dot(other)

	override fun sign() = x.sign.toLong() toPL y.sign.toLong()

	fun toPair() = x to y
}