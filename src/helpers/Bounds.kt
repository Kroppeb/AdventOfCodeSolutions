package helpers

import kotlin.math.*

/**
 * lower and higher are inclusive
 */
data class Bounds(val lower: Point, val higher: Point) {
	operator fun contains(point: Point): Boolean = point.x in lower.x..higher.x && point.y in lower.y..higher.y

	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: Bounds): Bounds = Bounds(
			max(this.lower.x, other.lower.x) toP max(this.lower.y, other.lower.y),
			min(this.higher.x, other.higher.x) toP min(this.higher.y, other.higher.y))

	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: Bounds): Bounds = Bounds(
			min(this.lower.x, other.lower.x) toP min(this.lower.y, other.lower.y),
			max(this.higher.x, other.higher.x) toP max(this.higher.y, other.higher.y))

	companion object{
		val INFINITE = (Int.MIN_VALUE toP Int.MIN_VALUE) toB (Int.MAX_VALUE toP Int.MAX_VALUE)
	}
}

infix fun Point.toB(other: Point): Bounds = Bounds(
		min(this.x, other.x) toP min(this.y, other.y),
		max(this.x, other.x) toP max(this.y, other.y)
)
