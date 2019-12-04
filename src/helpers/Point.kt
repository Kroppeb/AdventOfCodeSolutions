package helpers

import kotlin.math.abs
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
}

data class Point(val x: Int, val y: Int) : PointN<Point> {
	val right by lazy { x + 1 toP y }
	val down by lazy { x toP y + 1 }
	val left by lazy { x - 1 toP y }
	val up by lazy { x toP y - 1 }


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
}

/**
 * righthanded
 */
data class Point3D(val x: Int, val y: Int, val z: Int) : PointN<Point3D>{
	val right by lazy { x + 1 toP y toP z }
	val left by lazy { x - 1 toP y toP z }
	val down by lazy { x toP y + 1 toP z }
	val up by lazy { x toP y - 1 toP z }
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
}


fun Char.toPoint(): Point = when (this) {
	'E' -> 1 toP 0
	'R' -> 1 toP 0

	'S' -> 0 toP 1
	'D' -> 0 toP 1

	'W' -> -1 toP 0
	'L' -> -1 toP 0

	'N' -> 0 toP -1
	'U' -> 0 toP -1
	else -> error("")
}


fun abs(v: Point) = v.abs()


fun Iterable<Point>.getClosest(): Point? = this.minBy(Point::sqrDist)
fun Iterable<Point>.getClosestMan(): Point? = this.minBy(Point::manDist)

fun Iterable<Point>.getClosestTo(other: Point): Point? = this.minBy { it.sqrDistTo(other) }
fun Iterable<Point>.getClosestManTo(other: Point): Point? = this.minBy { it.manDistTo(other) }

fun Iterable<Point>.getFurthest(): Point? = this.maxBy(Point::sqrDist)
fun Iterable<Point>.getFurthestMan(): Point? = this.maxBy(Point::manDist)

fun Iterable<Point>.getFurthestTo(other: Point): Point? = this.maxBy { it.sqrDistTo(other) }
fun Iterable<Point>.getFurthestManTo(other: Point): Point? = this.maxBy { it.manDistTo(other) }

fun Iterable<Point>.getClosestSqrDist(): Int? = this.map(Point::sqrDist).min()
fun Iterable<Point>.getClosestDist(): Double? = getClosestSqrDist()?.let { sqrt(it.toDouble()) }
fun Iterable<Point>.getClosestManDist(): Int? = this.map(Point::manDist).min()

fun Iterable<Point>.getClosestSqrDistTo(other: Point): Int? = this.map { it.sqrDistTo(other) }.min()
fun Iterable<Point>.getClosestDistTo(other: Point): Double? = getClosestSqrDistTo(other)?.let { sqrt(it.toDouble()) }
fun Iterable<Point>.getClosestManDistTo(other: Point): Int? = this.map { it.manDistTo(other) }.min()

fun Iterable<Point>.getFurthestSqrDist(): Int? = this.map(Point::sqrDist).max()
fun Iterable<Point>.getFurthestDist(): Double? = getFurthestSqrDist()?.let { sqrt(it.toDouble()) }
fun Iterable<Point>.getFurthestManDist(): Int? = this.map(Point::manDist).max()

fun Iterable<Point>.getFurthestSqrDistTo(other: Point): Int? = this.map { it.sqrDistTo(other) }.max()
fun Iterable<Point>.getFurthestDistTo(other: Point): Double? = getFurthestSqrDistTo(other)?.let { sqrt(it.toDouble()) }
fun Iterable<Point>.getFurthestManDistTo(other: Point): Int? = this.map { it.manDistTo(other) }.max()

