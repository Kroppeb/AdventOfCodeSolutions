package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.gcd
import me.kroppeb.aoc.helpers.max
import me.kroppeb.aoc.helpers.min
import me.kroppeb.aoc.helpers.sint.*
import kotlin.math.sqrt

/**
 * righthanded rotations for now
 */
data class Point3D(val x: Sint, val y: Sint, val z: Sint) : PointNS<Point3D> {
	val right by lazy { (x toP y) + Clock.rightI toP z }
	val left by lazy { (x toP y) + Clock.leftI toP z }
	val down by lazy { (x toP y) + Clock.downI toP z }
	val up by lazy { (x toP y) + Clock.upI toP z }
	val front by lazy { x toP y toP z + 1 }
	val back by lazy { x toP y toP z - 1 }

	@Deprecated("Is always right handed")
	fun rotateClockX() = x toP -z toP y

	@Deprecated("Is always right handed")
	fun rotateAntiClockX() = x toP z toP -y

	@Deprecated("Is always right handed")
	fun rotateClockY() = z toP y toP -x

	@Deprecated("Is always right handed")
	fun rotateAntiClockY() = -z toP y toP x

	@Deprecated("Is always right handed")
	fun rotateClockZ() = -y toP x toP z

	@Deprecated("Is always right handed")
	fun rotateAntiClockZ() = y toP -x toP z

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


	override operator fun unaryMinus(): Point3D = -x toP -y toP -z

	override operator fun minus(other: Point3D): Point3D = x - other.x toP y - other.y toP z - other.z
	override operator fun plus(other: Point3D): Point3D = x + other.x toP y + other.y toP z + other.z

	override operator fun times(other: Point3D): Point3D = x * other.x toP y * other.y toP z * other.z
	override operator fun div(other: Point3D): Point3D = x / other.x toP y / other.y toP z / other.z
	override operator fun rem(other: Point3D): Point3D = x % other.x toP y % other.y toP z % other.z

	override operator fun times(other: Sint): Point3D = x * other toP y * other toP z * other
	override operator fun div(other: Sint): Point3D = x / other toP y / other toP z / other
	override operator fun rem(other: Sint): Point3D = x % other toP y % other toP z % other

	override fun abs(): Point3D = abs(x) toP abs(y) toP abs(z)

	override fun sqrDist(): Sint = x * x + y * y + z * z
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Sint = abs(x) + abs(y) + abs(z)
	override fun chebyshevDist(): Sint = maxOf(abs(x), abs(y), abs(z))

	override fun gcd(): Sint = gcd(abs(x), gcd(abs(y), abs(z)))

	override fun min(other: Point3D): Point3D =
		min(this.x, other.x) toP min(this.y, other.y) toP min(this.z, other.z)

	override fun max(other: Point3D): Point3D =
		max(this.x, other.x) toP max(this.y, other.y) toP max(this.z, other.z)

	override fun dot(other: Point3D) = this.x * other.x + this.y * other.y + this.z * other.z

	override fun sign() = x.sign() toP y.sign() toP z.sign()


	companion object {
		val ZERO = 0 toP 0 toP 0
		val DIRS get() = ZERO.getHexNeighbours()
	}
}