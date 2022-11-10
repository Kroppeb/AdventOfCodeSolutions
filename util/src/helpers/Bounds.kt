package helpers

import grid.Clock
import java.io.PrintStream
import java.util.function.Consumer
import kotlin.math.*

/**
 * lower and higher are inclusive
 */
abstract class IBounds : Iterable<Point> {
	abstract val lower: Point
	abstract val higher: Point
	operator fun contains(point: Point): Boolean = point.x in lower.x..higher.x && point.y in lower.y..higher.y

	val isSquare: Boolean get() = (higher.x - lower.x) == (higher.y - lower.y)
	val ne
		get() = when (Clock.nX + Clock.eX) {
			1 -> higher.x
			-1 -> lower.x
			else -> error("")
		} toP when (Clock.nY + Clock.eY) {
			1 -> higher.y
			-1 -> lower.y
			else -> error("")
		}
	val nw
		get() =
			when (Clock.nX - Clock.eX) {
				1 -> higher.x
				-1 -> lower.x
				else -> error("")
			} toP when (Clock.nY - Clock.eY) {
				1 -> higher.y
				-1 -> lower.y
				else -> error("")
			}
	val se get() = higher + lower - nw
	val sw get() = higher + lower - ne
	val upperLeft get() = nw
	val upperRight get() = ne
	val lowerLeft get() = sw
	val lowerRight get() = se

	override operator fun iterator() = (this.lower.x..this.higher.x).flatMap { x ->
		(this.lower.y..this.higher.y).map { y -> x toP y }
	}.iterator()

	val xs get() = this.lower.x..this.higher.x
	val ys get() = this.lower.y..this.higher.y

	val xSize get() = (this.higher.x - this.lower.x + 1)
	val ySize get() = (this.higher.y - this.lower.y + 1)
	val size get() = xSize toP ySize
	val area get() = xSize * ySize
}

data class Bounds(override val lower: Point, override val higher: Point) : IBounds() {
	constructor(xs: IntRange, ys: IntRange) : this(xs.first toP ys.first, xs.last toP ys.last)


	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: Bounds): Bounds = Bounds(
		kotlin.math.max(this.lower.x, other.lower.x) toP kotlin.math.max(this.lower.y, other.lower.y),
		kotlin.math.min(this.higher.x, other.higher.x) toP kotlin.math.min(this.higher.y, other.higher.y)
	)

	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: Bounds): Bounds = Bounds(
		kotlin.math.min(this.lower.x, other.lower.x) toP kotlin.math.min(this.lower.y, other.lower.y),
		kotlin.math.max(this.higher.x, other.higher.x) toP kotlin.math.max(this.higher.y, other.higher.y)
	)

	companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		val INFINITE = (Int.MIN_VALUE toP Int.MIN_VALUE) toB (Int.MAX_VALUE toP Int.MAX_VALUE)
	}
}

class MutableBounds(start: Point) : IBounds(){
	override var lower = start
	override var higher = start

	fun add(point: Point) {
		lower = Point(min(lower.x, point.x), min(lower.y, point.y))
		higher = Point(max(higher.x, point.x), max(higher.y, point.y))
	}

	fun toBounds(): Bounds = Bounds(lower, higher)
}

infix fun Point.toB(other: Point): Bounds = Bounds(
	min(this.x, other.x) toP min(this.y, other.y), max(this.x, other.x) toP max(this.y, other.y)
)

operator fun Point.rangeTo(other: Point) = this toB other
operator fun Point.rem(bounds: IBounds) = (x % bounds.xs) toP (y % bounds.ys)

fun Iterable<Point>.bounds(): Bounds {
	val bounds = MutableBounds(first())
	forEach { bounds.add(it) }
	return bounds.toBounds()
}
