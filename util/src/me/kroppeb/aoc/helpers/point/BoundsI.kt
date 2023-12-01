package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.Sint

/**
 * lower and higher are inclusive
 */
abstract class IBoundsI : Iterable<PointI> {
	abstract val lower: PointI
	abstract val higher: PointI
	operator fun contains(point: PointI): Boolean = point.x in xs && point.y in ys

	val isSquare: Boolean get() = (higher.x - lower.x) == (higher.y - lower.y)
	val ne
		get() = when (Clock.nX + Clock.eX) {
			1 -> higher.x
			-1 -> lower.x
			else -> error("")
		} toPI when (Clock.nY + Clock.eY) {
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
			} toPI when (Clock.nY - Clock.eY) {
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

	override operator fun iterator() = xs.flatMap { x -> ys.map { y -> x toPI y } }.iterator()

	val xs get() = this.lower.x..this.higher.x
	val ys get() = this.lower.y..this.higher.y

	open val xSize get() = (this.higher.x - this.lower.x + 1)
	open val ySize get() = (this.higher.y - this.lower.y + 1)
	val size get() = xSize toPI ySize
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

data class BoundsI(override val lower: PointI, override val higher: PointI) : IBoundsI() {
	constructor(xs: IntRange, ys: IntRange) : this(xs.first toPI ys.first, xs.last toPI ys.last)


	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: BoundsI): BoundsI = BoundsI(
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
	fun merge(other: BoundsI): BoundsI = BoundsI(
		this.lower.min(other.lower),
		this.higher.max(other.higher)
	)

	companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		val INFINITE: BoundsI = (Int.MIN_VALUE toPI Int.MIN_VALUE) toBI (Int.MAX_VALUE toPI Int.MAX_VALUE)
		val EMPTY: BoundsI = BoundsI(0 toPI 0, -1 toPI -1)
	}
}

class MutableBoundsI : IBoundsI {
	override var lower: PointI
	override var higher: PointI

	constructor(start: PointI) : super() {
		this.lower = start
		this.higher = start
	}

	constructor() : super() {
		this.lower = Int.MAX_VALUE toPI Int.MAX_VALUE
		this.higher = Int.MIN_VALUE toPI Int.MIN_VALUE
	}

	fun add(point: PointI) {
		lower = PointI(min(lower.x, point.x), min(lower.y, point.y))
		higher = PointI(max(higher.x, point.x), max(higher.y, point.y))
	}

	fun toBounds(): BoundsI {
		if (lower.x > higher.x || lower.y > higher.y) return BoundsI.EMPTY
		return BoundsI(lower, higher)
	}

	override val xSize: Int
		get() = super.xSize.coerceAtLeast(0)
	override val ySize: Int
		get() = super.ySize.coerceAtLeast(0)
}

infix fun PointI.toBI(other: PointI): BoundsI = BoundsI(
	min(this.x, other.x) toPI min(this.y, other.y), max(this.x, other.x) toPI max(this.y, other.y)
)

operator fun PointI.rangeTo(other: PointI) = this toBI other
operator fun PointI.rem(bounds: IBoundsI) = x % bounds.xs  toPI y % bounds.ys

fun Iterable<PointI>.bounds(): BoundsI {
	val bounds = MutableBoundsI()
	forEach { bounds.add(it) }
	return bounds.toBounds()
}
