package me.kroppeb.aoc.helpers.point


/**
 * lower and higher are inclusive
 */
abstract class IBoundsG<P: PointN<P, *>> {
	abstract val lower: P
	abstract val higher: P
	operator fun contains(point: P): Boolean = point.min(lower) == lower && point.max(higher) == higher
}

data class BoundsG<P: PointN<P, *>>(override val lower: P, override val higher: P) : IBoundsG<P>() {
	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: BoundsG<P>): BoundsG<P> = BoundsG(
		lower.max(other.lower),
		higher.min(other.higher)
	)

	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: BoundsG<P>): BoundsG<P> = BoundsG(
		lower.min(other.lower),
		higher.max(other.higher)
	)
}

class MutableBoundsG<P: PointN<P, *>>(start: P) : IBoundsG<P>(){
	override var lower = start
	override var higher = start

	fun add(point: P) {
		lower = lower.min(point)
		higher = higher.max(point)
	}

	fun toBoundsG(): BoundsG<P> = BoundsG<P>(lower, higher)
}

infix fun <P: PointN<P, *>> P.toBG(other: P): BoundsG<P> = BoundsG(
	this.min(other),
	this.max(other)
)

fun <P: PointN<P, *>> Iterable<P>.boundsG(): BoundsG<P> {
	val bounds = MutableBoundsG(first())
	forEach { bounds.add(it) }
	return bounds.toBoundsG()
}
