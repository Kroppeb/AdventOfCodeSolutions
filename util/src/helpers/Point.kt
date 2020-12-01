package helpers

import grid.Clock
import grid.Clock.down
import grid.Clock.left
import grid.Clock.right
import grid.Clock.up
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt

infix fun Int.toP(y: Int): Point = Point(this, y)
infix fun Point.toP(z: Int): Point3D = Point3D(x, y, z)


interface PointN<T : PointN<T>> {

	fun getVonNeumannNeighbours(): List<T>
	fun getMooreNeighbours(): List<T>

	operator fun unaryMinus(): T

	operator fun minus(other: T): T
	operator fun plus(other: T): T

	operator fun times(other: T): T
	operator fun div(other: T): T
	operator fun rem(other: T): T

	operator fun times(other: Int): T
	operator fun div(other: Int): T
	operator fun rem(other: Int): T

	fun abs(): T

	fun sqrDist(): Int
	fun dist(): Double
	fun manDist(): Int

	fun sqrDistTo(other: T): Int = (this - other).sqrDist()
	fun distTo(other: T): Double = (this - other).dist()
	fun manDistTo(other: T): Int = (this - other).manDist()

	fun discreteAngle(): T
	fun gcd(): Int
	fun min(other: T): T
	fun max(other: T): T
}

data class Point(val x: Int, val y: Int) : PointN<Point> {
	val right by lazy { x + Clock.eX toP y + Clock.eY }
	val down by lazy { x - Clock.nX toP y - Clock.nY }
	val left by lazy { x - Clock.eX toP y - Clock.eY }
	val up by lazy { x + Clock.nX toP y + Clock.nY }


	fun rotateClock() = (-y) toP x
	fun rotateAntiClock() = y toP (-x)

	fun getQuadNeighbours() = listOf(right, down, left, up)
	fun getDiagonalNeighbours() = listOf(right.down, left.down, left.up, right.up)
	fun getOctNeighbours() = listOf(right, right.down, down, left.down, left, left.up, up, right.up)

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

	override fun abs() = abs(x) toP abs(y)

	override fun sqrDist(): Int = x * x + y * y
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Int = abs(x) + abs(y)

	override fun sqrDistTo(other: Point): Int = (this - other).sqrDist()
	override fun distTo(other: Point): Double = (this - other).dist()
	override fun manDistTo(other: Point): Int = (this - other).manDist()

	fun angle(): Double {
		return atan2(y.toDouble(), x.toDouble())
	}

	override fun gcd(): Int = gcd(abs(x), abs(y))

	override fun discreteAngle(): Point {
		val g = gcd()
		return this / g
	}

	override fun min(other: Point): Point = min(this.x, other.x) toP min(this.y, other.y)
	override fun max(other: Point): Point = max(this.x, other.x) toP max(this.y, other.y)
}

/**
 * righthanded rotations for now
 */
data class Point3D(val x: Int, val y: Int, val z: Int) : PointN<Point3D> {
	val right by lazy { x + Clock.eX toP y + Clock.eY toP z }
	val left by lazy { x - Clock.eX toP y - Clock.eY toP z }
	val down by lazy { x - Clock.nX toP y - Clock.nY toP z }
	val up by lazy { x + Clock.nX toP y + Clock.nY toP z }
	val front by lazy { x toP y toP z + 1 }
	val back by lazy { x toP y toP z - 1 }

	fun rotateClockX() = x toP -z toP y
	fun rotateAntiClockX() = x toP z toP -y
	fun rotateClockY() = z toP y toP -x
	fun rotateAntiClockY() = -z toP y toP x
	fun rotateClockZ() = -y toP x toP z
	fun rotateAntiClockZ() = y toP -x toP z

	fun getHexNeighbours() = listOf(right, left, up, down, front, back)

	// Ordered by x:y:z
	fun get2DDiagonalNeighbours() = listOf(
			left.down, left.back, left.front, left.up,
			down.back, down.front, up.back, up.front,
			right.down, right.back, right.front, right.up)

	// Ordered by x:y:z
	fun get3DDiagonalNeighbours() = listOf(
			left.down.back, left.down.front, left.up.back, left.up.front,
			right.down.back, right.down.front, right.up.back, right.up.front)

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
			right.up.back, right.up, right.up.front)


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

	override fun abs(): Point3D = abs(x) toP abs(y) toP abs(z)

	override fun sqrDist(): Int = x * x + y * y + z * z
	override fun dist(): Double = sqrt(sqrDist().toDouble())
	override fun manDist(): Int = abs(x) + abs(y) + abs(z)

	override fun sqrDistTo(other: Point3D): Int = (this - other).sqrDist()
	override fun distTo(other: Point3D): Double = (this - other).dist()
	override fun manDistTo(other: Point3D): Int = (this - other).manDist()

	override fun gcd(): Int = gcd(abs(x), gcd(abs(y), abs(z)))

	override fun discreteAngle(): Point3D {
		val g = gcd()
		return this / g
	}

	override fun min(other: Point3D): Point3D = min(this.x, other.x) toP min(this.y, other.y) toP min(this.z, other.z)
	override fun max(other: Point3D): Point3D = max(this.x, other.x) toP max(this.y, other.y) toP max(this.z, other.z)
}


fun Char.toPoint(): Point = when (this) {
	'E' -> right
	'R' -> right

	'S' -> down
	'D' -> down

	'W' -> left
	'L' -> left

	'N' -> up
	'U' -> up
	else -> error("")
}


fun <T : PointN<T>> abs(v: T) = v.abs()


fun <T : PointN<T>> Iterable<T>.getClosest(): T? = this.minBy(PointN<T>::sqrDist)
fun <T : PointN<T>> Iterable<T>.getClosestMan(): T? = this.minBy(PointN<T>::manDist)

fun <T : PointN<T>> Iterable<T>.getClosestTo(other: T): T? = this.minBy { it.sqrDistTo(other) }
fun <T : PointN<T>> Iterable<T>.getClosestManTo(other: T): T? = this.minBy { it.manDistTo(other) }

fun <T : PointN<T>> Iterable<T>.getFurthest(): T? = this.maxBy(PointN<T>::sqrDist)
fun <T : PointN<T>> Iterable<T>.getFurthestMan(): T? = this.maxBy(PointN<T>::manDist)

fun <T : PointN<T>> Iterable<T>.getFurthestTo(other: T): T? = this.maxBy { it.sqrDistTo(other) }
fun <T : PointN<T>> Iterable<T>.getFurthestManTo(other: T): T? = this.maxBy { it.manDistTo(other) }

fun <T : PointN<T>> Iterable<T>.getClosestSqrDist(): Int? = this.map(PointN<T>::sqrDist).min()
fun <T : PointN<T>> Iterable<T>.getClosestDist(): Double? = getClosestSqrDist()?.let { sqrt(it.toDouble()) }
fun <T : PointN<T>> Iterable<T>.getClosestManDist(): Int? = this.map(PointN<T>::manDist).min()

fun <T : PointN<T>> Iterable<T>.getClosestSqrDistTo(other: T): Int? = this.map { it.sqrDistTo(other) }.min()
fun <T : PointN<T>> Iterable<T>.getClosestDistTo(other: T): Double? = getClosestSqrDistTo(other)?.let { sqrt(it.toDouble()) }
fun <T : PointN<T>> Iterable<T>.getClosestManDistTo(other: T): Int? = this.map { it.manDistTo(other) }.min()

fun <T : PointN<T>> Iterable<T>.getFurthestSqrDist(): Int? = this.map(PointN<T>::sqrDist).max()
fun <T : PointN<T>> Iterable<T>.getFurthestDist(): Double? = getFurthestSqrDist()?.let { sqrt(it.toDouble()) }
fun <T : PointN<T>> Iterable<T>.getFurthestManDist(): Int? = this.map(PointN<T>::manDist).max()

fun <T : PointN<T>> Iterable<T>.getFurthestSqrDistTo(other: T): Int? = this.map { it.sqrDistTo(other) }.max()
fun <T : PointN<T>> Iterable<T>.getFurthestDistTo(other: T): Double? = getFurthestSqrDistTo(other)?.let { sqrt(it.toDouble()) }
fun <T : PointN<T>> Iterable<T>.getFurthestManDistTo(other: T): Int? = this.map { it.manDistTo(other) }.max()


fun <T : PointN<T>> Iterable<T>.sortByClosestTo(other: T) = sortedBy { it.sqrDistTo(other) }
fun <T : PointN<T>> Iterable<T>.sortByClosestManTo(other: T) = sortedBy { it.sqrDistTo(other) }
fun <T : PointN<T>> Iterable<T>.sortByFurthestTo(other: T) = sortedByDescending { it.manDistTo(other) }
fun <T : PointN<T>> Iterable<T>.sortByFurthestManTo(other: T) = sortedByDescending { it.manDistTo(other) }

fun <T : PointN<T>> Iterable<T>.sortByClosest() = sortedBy(PointN<T>::sqrDist)
fun <T : PointN<T>> Iterable<T>.sortByClosestMan() = sortedBy(PointN<T>::manDist)
fun <T : PointN<T>> Iterable<T>.sortByFurthest() = sortedByDescending(PointN<T>::sqrDist)
fun <T : PointN<T>> Iterable<T>.sortByFurthestMan() = sortedByDescending(PointN<T>::manDist)

object PointOrdering {
	object XMayor : Comparator<Point> {
		override fun compare(o1: Point, o2: Point): Int {
			if (o1.x == o2.x)
				return o1.y.compareTo(o2.y)
			return o1.x.compareTo(o2.x)
		}
	}

	object YMayor : Comparator<Point> {
		override fun compare(o1: Point, o2: Point): Int {
			if (o1.y == o2.y)
				return o1.x.compareTo(o2.x)
			return o1.y.compareTo(o2.y)
		}
	}
}
