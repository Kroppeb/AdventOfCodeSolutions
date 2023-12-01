package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.gcd
import kotlin.math.sign
import kotlin.math.sqrt

/**
 * righthanded rotations for now
 */
data class Point3DI(val x: Int, val y: Int, val z: Int) : PointNI<Point3DI> {
	val right by lazy { (x toPI y) + Clock.rightI toPI z }
	val left by lazy { (x toPI y) + Clock.leftI toPI z }
	val down by lazy { (x toPI y) + Clock.downI toPI z }
	val up by lazy { (x toPI y) + Clock.upI toPI z }
	val front by lazy { x toPI y toPI z + 1 }
	val back by lazy { x toPI y toPI z - 1 }

	@Deprecated("Is always right handed")
	fun rotateClockX() = x toPI -z toPI y
	@Deprecated("Is always right handed")
	fun rotateAntiClockX() = x toPI z toPI -y
	@Deprecated("Is always right handed")
	fun rotateClockY() = z toPI y toPI -x
	@Deprecated("Is always right handed")
	fun rotateAntiClockY() = -z toPI y toPI x
	@Deprecated("Is always right handed")
	fun rotateClockZ() = -y toPI x toPI z
	@Deprecated("Is always right handed")
	fun rotateAntiClockZ() = y toPI -x toPI z

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


	override operator fun unaryMinus(): Point3DI = -x toPI -y toPI -z

	override operator fun minus(other: Point3DI): Point3DI = x - other.x toPI y - other.y toPI z - other.z
	override operator fun plus(other: Point3DI): Point3DI = x + other.x toPI y + other.y toPI z + other.z

	override operator fun times(other: Point3DI): Point3DI = x * other.x toPI y * other.y toPI z * other.z
	override operator fun div(other: Point3DI): Point3DI = x / other.x toPI y / other.y toPI z / other.z
	override operator fun rem(other: Point3DI): Point3DI = x % other.x toPI y % other.y toPI z % other.z

	override operator fun times(other: Int): Point3DI = x * other toPI y * other toPI z * other
	override operator fun div(other: Int): Point3DI = x / other toPI y / other toPI z / other
	override operator fun rem(other: Int): Point3DI = x % other toPI y % other toPI z % other

	override fun abs(): Point3DI = kotlin.math.abs(x) toPI kotlin.math.abs(y) toPI kotlin.math.abs(z)

	override fun sqrDist(): Int = x * x + y * y + z * z
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Int = kotlin.math.abs(x) + kotlin.math.abs(y) + kotlin.math.abs(z)
	override fun chebyshevDist(): Int = maxOf(kotlin.math.abs(x), kotlin.math.abs(y), kotlin.math.abs(z))

	override fun gcd(): Int = me.kroppeb.aoc.helpers.gcd(kotlin.math.abs(x), gcd(kotlin.math.abs(y), kotlin.math.abs(z)))

	override fun min(other: Point3DI): Point3DI = kotlin.math.min(this.x, other.x) toPI kotlin.math.min(
		this.y,
		other.y
	) toPI kotlin.math.min(this.z, other.z)
	override fun max(other: Point3DI): Point3DI = kotlin.math.max(this.x, other.x) toPI kotlin.math.max(
		this.y,
		other.y
	) toPI kotlin.math.max(this.z, other.z)

	override fun dot(other: Point3DI) = this.x * other.x + this.y * other.y + this.z * other.z

	override fun sign() = x.sign toPI y.sign toPI z.sign
}