package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.gcd
import kotlin.math.atan2
import kotlin.math.sign
import kotlin.math.sqrt

data class PointI(val x: Int, val y: Int) : PointNI<PointI> {
	// TODO: evaluate whether having these as lazy actually helps or hurts
	val right by lazy { x + Clock.eX toPI y + Clock.eY }
	val down by lazy { x - Clock.nX toPI y - Clock.nY }
	val left by lazy { x - Clock.eX toPI y - Clock.eY }
	val up by lazy { x + Clock.nX toPI y + Clock.nY }

	val north get() = up
	val east get() = right
	val south get() = down
	val west get() = left

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


	fun rotateClock() = Clock.rightI * Clock.upI.dot(this) + Clock.upI * Clock.leftI.dot(this)
	fun rotateAntiClock() = -rotateClock()

	fun getQuadNeighbours() = listOf(right, down, left, up)
	fun getDiagonalNeighbours() = listOf(right.down, left.down, left.up, right.up)
	fun getOctNeighbours() = listOf(right, right.down, down, left.down, left, left.up, up, right.up)

	fun getQuadNeighbourHood() = listOf(this, right, down, left, up)
	fun getDiagonalNeighbourHood() = listOf(this, right.down, left.down, left.up, right.up)
	fun getOctNeighbourHood() = listOf(this, right, right.down, down, left.down, left, left.up, up, right.up)

	override fun getMooreNeighbours() = getOctNeighbours()
	override fun getVonNeumannNeighbours() = getQuadNeighbours()

	override operator fun unaryMinus(): PointI = -x toPI -y

	override operator fun minus(other: PointI): PointI = x - other.x toPI y - other.y
	override operator fun plus(other: PointI): PointI = x + other.x toPI y + other.y

	operator fun minus(other: Char): PointI = this - other.toPointI()
	operator fun plus(other: Char): PointI = this + other.toPointI()


	override operator fun times(other: PointI): PointI = x * other.x toPI y * other.y
	override operator fun div(other: PointI): PointI = x / other.x toPI y / other.y
	override operator fun rem(other: PointI): PointI = x % other.x toPI y % other.y

	override operator fun times(other: Int): PointI = x * other toPI y * other
	override operator fun div(other: Int): PointI = x / other toPI y / other
	override operator fun rem(other: Int): PointI = x % other toPI y % other

	override fun abs() = kotlin.math.abs(x) toPI kotlin.math.abs(y)

	override fun sqrDist(): Int = x * x + y * y
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Int = kotlin.math.abs(x) + kotlin.math.abs(y)
	override fun chebyshevDist(): Int = kotlin.math.max(kotlin.math.abs(x), kotlin.math.abs(y))

	fun angle(): Double {
		return atan2(y.toDouble(), x.toDouble())
	}

	override fun gcd(): Int = gcd(kotlin.math.abs(x), kotlin.math.abs(y))

	override fun min(other: PointI): PointI = kotlin.math.min(this.x, other.x) toPI kotlin.math.min(this.y, other.y)
	override fun max(other: PointI): PointI = kotlin.math.max(this.x, other.x) toPI kotlin.math.max(this.y, other.y)

	override fun dot(other: PointI) = this.x * other.x + this.y * other.y

	fun isLeftOf(other: PointI) = Clock.leftI.dot(this) > Clock.leftI.dot(other)
	fun isRightOf(other: PointI) = Clock.rightI.dot(this) > Clock.rightI.dot(other)
	fun isAbove(other: PointI) = Clock.upI.dot(this) > Clock.upI.dot(other)
	fun isBelow(other: PointI) = Clock.downI.dot(this) > Clock.downI.dot(other)
	fun sameLeftRight(other: PointI) = Clock.leftI.dot(this) == Clock.leftI.dot(other)
	fun sameUpDown(other: PointI) = Clock.upI.dot(this) == Clock.upI.dot(other)

	companion object {
		val ZERO = 0 toPI 0
		val DIRS get() = ZERO.getQuadNeighbours()
	}

	fun northsInc() = this.sequence(Clock.upI)
	fun southsInc() = this.sequence(Clock.downI)
	fun eastsInc() = this.sequence(Clock.rightI)
	fun westsInc() = this.sequence(Clock.leftI)

	fun norths() = this.northsInc().drop(1)
	fun souths() = this.southsInc().drop(1)
	fun easts() = this.eastsInc().drop(1)
	fun wests() = this.westsInc().drop(1)

	override fun sign(): PointI = x.sign toPI y.sign

	fun toPair() = x to y
}