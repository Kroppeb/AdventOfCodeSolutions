package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import kotlin.math.sign
import kotlin.math.sqrt

data class Point3DL(val x: Long, val y: Long, val z: Long) : PointNL<Point3DL> {
	val right by lazy { x + Clock.eX toPL y + Clock.eY toPL z }
	val left by lazy { x - Clock.eX toPL y - Clock.eY toPL z }
	val down by lazy { x - Clock.nX toPL y - Clock.nY toPL z }
	val up by lazy { x + Clock.nX toPL y + Clock.nY toPL z }
	val front by lazy { x toPL y toPL z + 1 }
	val back by lazy { x toPL y toPL z - 1 }

	fun rotateClockX() = x toPL -z toPL y
	fun rotateAntiClockX() = x toPL z toPL -y
	fun rotateClockY() = z toPL y toPL -x
	fun rotateAntiClockY() = -z toPL y toPL x
	fun rotateClockZ() = -y toPL x toPL z
	fun rotateAntiClockZ() = y toPL -x toPL z

	fun getHexNeighbours() = listOf(right, left, up, down, front, back)

	// Ordered by x:y:z
	fun get2DDiagonalNeighbours() = listOf(
		left.down, left.back, left.front, left.up,
		down.back, down.front, up.back, up.front,
		right.down, right.back, right.front, right.up
	)

	// Ordered by x:y:z
	fun get3DDiagonalNeighbours() = listOf(
		left.down.back, left.down.front, left.up.back, left.up.front,
		right.down.back, right.down.front, right.up.back, right.up.front
	)

	// Ordered by x:y:z
	fun getIcosiHeptaNeighbours() = listOf(
		left.down.back, left.down, left.down.front,
		left.back, left, left.front,
		left.up.back, left.up, left.up.front,

		down.back, down, down.front,
		back, /*this,*/ front,
		up.back, up, up.front,

		right.down.back, right.down, right.down.front,
		right.back, right, right.front,
		right.up.back, right.up, right.up.front
	)


	override fun getMooreNeighbours() = getIcosiHeptaNeighbours()
	override fun getVonNeumannNeighbours() = getHexNeighbours()


	override operator fun unaryMinus(): Point3DL = -x toPL -y toPL -z

	override operator fun minus(other: Point3DL): Point3DL = x - other.x toPL y - other.y toPL z - other.z
	override operator fun plus(other: Point3DL): Point3DL = x + other.x toPL y + other.y toPL z + other.z

	override operator fun times(other: Point3DL): Point3DL = x * other.x toPL y * other.y toPL z * other.z
	override operator fun div(other: Point3DL): Point3DL = x / other.x toPL y / other.y toPL z / other.z
	override operator fun rem(other: Point3DL): Point3DL = x % other.x toPL y % other.y toPL z % other.z

	override operator fun times(other: Long): Point3DL = x * other toPL y * other toPL z * other
	override operator fun div(other: Long): Point3DL = x / other toPL y / other toPL z / other
	override operator fun rem(other: Long): Point3DL = x % other toPL y % other toPL z % other

	override fun abs(): Point3DL = kotlin.math.abs(x) toPL kotlin.math.abs(y) toPL kotlin.math.abs(z)

	override fun sqrDist(): Long = TODO()
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Long = kotlin.math.abs(x) + kotlin.math.abs(y) + kotlin.math.abs(z)
	override fun chebyshevDist(): Long = kotlin.math.max(kotlin.math.max(kotlin.math.abs(x), kotlin.math.abs(y)), kotlin.math.abs(z))

	override fun gcd(): Long = me.kroppeb.aoc.helpers.gcd(kotlin.math.abs(x), me.kroppeb.aoc.helpers.gcd(kotlin.math.abs(y), kotlin.math.abs(z)))

	override fun min(other: Point3DL): Point3DL = kotlin.math.min(this.x, other.x) toPL kotlin.math.min(
		this.y,
		other.y
	) toPL kotlin.math.min(this.z, other.z)
	override fun max(other: Point3DL): Point3DL = kotlin.math.max(this.x, other.x) toPL kotlin.math.max(
		this.y,
		other.y
	) toPL kotlin.math.max(this.z, other.z)

	override fun dot(other: Point3DL) = (this.x * other.x + this.y * other.y + this.z * other.z)

	override fun sign() = x.sign.toLong() toPL y.sign.toLong() toPL z.sign.toLong()
}