package helpers

import grid.Clock
import kotlin.math.sign
import kotlin.math.sqrt

/**
 * righthanded rotations for now
 */
data class Point3D(val x: Int, val y: Int, val z: Int) : PointNI<Point3D> {
	val right by lazy { (x toP y) + Clock.right toP z }
	val left by lazy { (x toP y) + Clock.left toP z }
	val down by lazy { (x toP y) + Clock.down toP z }
	val up by lazy { (x toP y) + Clock.up toP z }
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

	override operator fun times(other: Int): Point3D = x * other toP y * other toP z * other
	override operator fun div(other: Int): Point3D = x / other toP y / other toP z / other
	override operator fun rem(other: Int): Point3D = x % other toP y % other toP z % other

	override fun abs(): Point3D = kotlin.math.abs(x) toP kotlin.math.abs(y) toP kotlin.math.abs(z)

	override fun sqrDist(): Int = x * x + y * y + z * z
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Int = kotlin.math.abs(x) + kotlin.math.abs(y) + kotlin.math.abs(z)
	override fun chebyshevDist(): Int = maxOf(kotlin.math.abs(x), kotlin.math.abs(y), kotlin.math.abs(z))

	override fun gcd(): Int = gcd(kotlin.math.abs(x), gcd(kotlin.math.abs(y), kotlin.math.abs(z)))

	override fun min(other: Point3D): Point3D = min(this.x, other.x) toP min(this.y, other.y) toP min(this.z, other.z)
	override fun max(other: Point3D): Point3D = max(this.x, other.x) toP max(this.y, other.y) toP max(this.z, other.z)

	override fun dot(other: Point3D) = this.x * other.x + this.y * other.y + this.z * other.z

	override fun sign() = x.sign toP y.sign toP z.sign
}