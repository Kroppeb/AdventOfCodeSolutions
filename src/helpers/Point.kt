package helpers

import kotlin.math.abs
import kotlin.math.sqrt

typealias Point = Pair<Int, Int>

fun Char.toPoint() = when (this) {
	'E' -> 1 to 0
	'R' -> 1 to 0

	'S' -> 0 to 1
	'D' -> 0 to 1

	'W' -> -1 to 0
	'L' -> -1 to 0

	'N' -> 0 to -1
	'U' -> 0 to -1
	else -> error("")
}

fun Point.right() = first + 1 to second
fun Point.down() = first to second + 1
fun Point.left() = first + 1 to second
fun Point.up() = first to second - 1

fun Point.rotateClock() = -second to first
fun Point.rotateAntiClock() = second to -first

fun Point.getQuadNeighbours() = listOf(right(), down(), left(), up())
fun Point.getDiagonalNeighbours() = listOf(right().down(), down().left(), left().up(), up().right())
fun Point.getOctNeighbours() = listOf(right(), right().down(), down(), down().left(), left(), left().up(), up(), up().right())

operator fun Point.unaryMinus(): Point = -first to -second

operator fun Point.minus(other: Point): Point = first - other.first to second - other.second
operator fun Point.plus(other: Point): Point = first + other.first to second + other.second

operator fun Point.minus(other: Char): Point = this - other.toPoint()
operator fun Point.plus(other: Char): Point = this + other.toPoint()


operator fun Point.times(other: Point): Point = first * other.first to second * other.second
operator fun Point.div(other: Point): Point = first / other.first to second / other.second
operator fun Point.rem(other: Point): Point = first % other.first to second % other.second

operator fun Point.times(other: Int): Point = first * other to second * other
operator fun Point.div(other: Int): Point = first / other to second / other
operator fun Point.rem(other: Int): Point = first % other to second % other

fun abs(v: Point): Point = abs(v.first) to abs(v.second)

fun Point.sqrDist(): Int = first * first + second * second
fun Point.dist(): Double = sqrt(sqrDist().toDouble())
fun Point.manDist(): Int = abs(first) + abs(second)

fun Point.sqrDistTo(other: Point): Int = (this - other).sqrDist()
fun Point.distTo(other: Point): Double = (this - other).dist()
fun Point.manDistTo(other: Point): Int = (this - other).manDist()

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

