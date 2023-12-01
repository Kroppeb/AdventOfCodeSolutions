package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.math.atan2
import kotlin.math.sqrt

data class Point(val x: Sint, val y: Sint) : PointNS<Point> {
	// TODO: evaluate whether having these as lazy actually helps or hurts
	val right by lazy { this + Clock.right }
	val down by lazy { this + Clock.down }
	val left by lazy { this + Clock.left }
	val up by lazy { this + Clock.up }

	val north get() = up
	val east get() = right
	val south get() = down
	val west get() = left

	val downRight get() = down.right
	val downLeft get() = down.left
	val upRight get() = up.right
	val upLeft get() = up.left
	val rightDown get() = right.down
	val rightUp get() = right.up
	val leftDown get() = left.down
	val leftUp get() = left.up

	val northEast get() = north.east
	val southEast get() = south.east
	val southWest get() = south.west
	val northWest get() = north.west

	// useful for hex grids
	val downDown get() = down.down
	val rightRight get() = right.right
	val upUp get() = up.up
	val leftLeft get() = left.left

	val northNorth get() = north.north
	val eastEast get() = east.east
	val southSouth get() = south.south
	val westWest get() = west.west


	val r get() = right
	val d get() = down
	val l get() = left
	val u get() = up

	val n get() = north
	val e get() = east
	val s get() = south
	val w get() = west

	val dr get() = down.right
	val dl get() = down.left
	val ur get() = up.right
	val ul get() = up.left
	val rd get() = right.down
	val ru get() = right.up
	val ld get() = left.down
	val lu get() = left.up

	val ne get() = north.east
	val se get() = south.east
	val sw get() = south.west
	val nw get() = north.west

	// useful for hex grids
	val dd get() = down.down
	val rr get() = right.right
	val uu get() = up.up
	val ll get() = left.left

	val nn get() = north.north
	val ee get() = east.east
	val ss get() = south.south
	val ww get() = west.west




	fun rotateClock(): Point = Clock.right * Clock.up.dot(this) + Clock.up * Clock.left.dot(this)
	fun rotateAntiClock(): Point = -rotateClock()

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

	override operator fun times(other: Sint): Point = x * other toP y * other
	override operator fun div(other: Sint): Point = x / other toP y / other
	override operator fun rem(other: Sint): Point = x % other toP y % other

	override fun abs() = abs(x) toP abs(y)

	override fun sqrDist(): Sint = x * x + y * y
	override fun dist(): Double = sqrt(sqrDist().l.toDouble())
	override fun manDist(): Sint = abs(x) + abs(y)
	override fun chebyshevDist(): Sint = max(abs(x), abs(y))

	fun angle(): Double {
		return atan2(y.toDouble(), x.toDouble())
	}

	override fun gcd(): Sint = gcd(abs(x), abs(y))

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
		val DIRS get() = ZERO.getQuadNeighbours()
	}

	fun northsInc() = this.sequence(Clock.up)
	fun southsInc() = this.sequence(Clock.down)
	fun eastsInc() = this.sequence(Clock.right)
	fun westsInc() = this.sequence(Clock.left)

	fun norths() = this.northsInc().drop(1)
	fun souths() = this.southsInc().drop(1)
	fun easts() = this.eastsInc().drop(1)
	fun wests() = this.westsInc().drop(1)

	override fun sign(): Point = x.sign() toP y.sign()

	fun toPair() = x to y

	val int get() = x.i toPI y.i
	val long get() = x.l toPL y.l
}
