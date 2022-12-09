package helpers

import grid.Clock
import kotlin.math.atan2
import kotlin.math.sign
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) : PointNI<Point> {
	// TODO: evaluate whether having these as lazy actually helps or hurts
	val right by lazy { x + Clock.eX toP y + Clock.eY }
	val down by lazy { x - Clock.nX toP y - Clock.nY }
	val left by lazy { x - Clock.eX toP y - Clock.eY }
	val up by lazy { x + Clock.nX toP y + Clock.nY }

	val north get() = up
	val east get() = right
	val south get() = down
	val west get() = left


	fun rotateClock() = Clock.right * Clock.up.dot(this) + Clock.up * Clock.left.dot(this)
	fun rotateAntiClock() = -rotateClock()

	fun getQuadNeighbours() = listOf(right, down, left, up)
	fun getDiagonalNeighbours() = listOf(right.down, left.down, left.up, right.up)
	fun getOctNeighbours() = listOf(right, right.down, down, left.down, left, left.up, up, right.up)

	fun getQuadNeighbourHood() = listOf(this, right, down, left, up)
	fun getDiagonalNeighbourHood() = listOf(this, right.down, left.down, left.up, right.up)
	fun getOctNeighbourHood() = listOf(this, right, right.down, down, left.down, left, left.up, up, right.up)

	override fun getMooreNeighbours() = getOctNeighbours()
	override fun getVonNeumannNeighbours() = getQuadNeighbours()

	override operator fun unaryMinus(): Point = -x toP -y

	override operator fun minus(other: Point): Point = x - other.x toP y - other.y
	override operator fun plus(other: Point): Point = x + other.x toP y + other.y

	operator fun minus(other: Char): Point = this - other.toPoint()
	operator fun plus(other: Char): Point = this + other.toPoint()


	override operator fun times(other: Point): Point = x * other.x toP y * other.y
	override operator fun div(other: Point): Point = x / other.x toP y / other.y
	override operator fun rem(other: Point): Point = x % other.x toP y % other.y

	override operator fun times(other: Int): Point = x * other toP y * other
	override operator fun div(other: Int): Point = x / other toP y / other
	override operator fun rem(other: Int): Point = x % other toP y % other

	override fun abs() = kotlin.math.abs(x) toP kotlin.math.abs(y)

	override fun sqrDist(): Int = x * x + y * y
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Int = kotlin.math.abs(x) + kotlin.math.abs(y)
	override fun chebyshevDist(): Int = kotlin.math.max(kotlin.math.abs(x), kotlin.math.abs(y))

	fun angle(): Double {
		return atan2(y.toDouble(), x.toDouble())
	}

	override fun gcd(): Int = gcd(kotlin.math.abs(x), kotlin.math.abs(y))

	override fun min(other: Point): Point = min(this.x, other.x) toP min(this.y, other.y)
	override fun max(other: Point): Point = max(this.x, other.x) toP max(this.y, other.y)

	override fun dot(other: Point) = this.x * other.x + this.y * other.y

	fun isLeftOf(other: Point) = Clock.left.dot(this) > Clock.left.dot(other)
	fun isRightOf(other: Point) = Clock.right.dot(this) > Clock.right.dot(other)
	fun isAbove(other: Point) = Clock.up.dot(this) > Clock.up.dot(other)
	fun isBelow(other: Point) = Clock.down.dot(this) > Clock.down.dot(other)
	fun sameLeftRight(other: Point) = Clock.left.dot(this) == Clock.left.dot(other)
	fun sameUpDown(other: Point) = Clock.up.dot(this) == Clock.up.dot(other)

	companion object {
		val ZERO = 0 toP 0
		val DIRS = ZERO.getQuadNeighbours()
	}

	fun northsInc() = this.sequence(Clock.up)
	fun southsInc() = this.sequence(Clock.down)
	fun eastsInc() = this.sequence(Clock.right)
	fun westsInc() = this.sequence(Clock.left)

	fun norths() = this.northsInc().drop(1)
	fun souths() = this.southsInc().drop(1)
	fun easts() = this.eastsInc().drop(1)
	fun wests() = this.westsInc().drop(1)

	override fun sign(): Point = x.sign toP y.sign
}