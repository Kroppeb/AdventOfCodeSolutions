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
	operator fun contains(point: Point): Boolean = point.x in xs && point.y in ys

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

	override operator fun iterator() = xs.flatMap { x -> ys.map { y -> x toP y } }.iterator()

	val xs get() = this.lower.x..this.higher.x
	val ys get() = this.lower.y..this.higher.y

	open val xSize get() = (this.higher.x - this.lower.x + 1)
	open val ySize get() = (this.higher.y - this.lower.y + 1)
	val size get() = xSize toP ySize
	val area get() = xSize * ySize

	fun exactCenter() = (lower + higher).also {
		require(it.x % 2 == 0)
		require(it.y % 2 == 0)
	} / 2

	fun leftEdge() = this.upperLeft toL this.lowerLeft
	fun rightEdge() = this.upperRight toL this.lowerRight
	fun topEdge() = this.upperLeft toL this.upperRight
	fun bottomEdge() = this.lowerLeft toL this.lowerRight

	fun northEdge() = topEdge()
	fun southEdge() = bottomEdge()
	fun eastEdge() = rightEdge()
	fun westEdge() = leftEdge()
}

data class Bounds(override val lower: Point, override val higher: Point) : IBounds() {
	constructor(xs: IntRange, ys: IntRange) : this(xs.first toP ys.first, xs.last toP ys.last)


	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: Bounds): Bounds = Bounds(
		this.lower.max(other.lower),
		this.higher.min(other.higher)
	)

	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: Bounds): Bounds = Bounds(
		this.lower.min(other.lower),
		this.higher.max(other.higher)
	)

	companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		val INFINITE = (Int.MIN_VALUE toP Int.MIN_VALUE) toB (Int.MAX_VALUE toP Int.MAX_VALUE)
		val EMPTY = Bounds(0 toP 0, -1 toP -1)
	}
}

class MutableBounds : IBounds {
	override var lower: Point
	override var higher: Point

	constructor(start: Point) : super() {
		this.lower = start
		this.higher = start
	}

	constructor() : super() {
		this.lower = Int.MAX_VALUE toP Int.MAX_VALUE
		this.higher = Int.MIN_VALUE toP Int.MIN_VALUE
	}

	fun add(point: Point) {
		lower = Point(min(lower.x, point.x), min(lower.y, point.y))
		higher = Point(max(higher.x, point.x), max(higher.y, point.y))
	}

	fun toBounds(): Bounds {
		if (lower.x > higher.x || lower.y > higher.y) return Bounds.EMPTY
		return Bounds(lower, higher)
	}

	override val xSize: Int
		get() = super.xSize.coerceAtLeast(0)
	override val ySize: Int
		get() = super.ySize.coerceAtLeast(0)
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
