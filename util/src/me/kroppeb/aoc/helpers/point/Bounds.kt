package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.*

/**
 * lower and higher are inclusive
 */
abstract class IBounds : Collection<Point> {
	abstract val lower: Point
	abstract val higher: Point
	override operator fun contains(point: Point): Boolean = point.x in xs && point.y in ys
	operator fun contains(point: PointI): Boolean = point.sint in this

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
		get() = when (Clock.nX - Clock.eX) {
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
	val sizes get() = xSize toP ySize
	val area get() = xSize * ySize

	@Deprecated("use area", ReplaceWith("area"))
	override val size get() = area.i

	override fun isEmpty() = xs.isEmpty() || ys.isEmpty()

	override fun containsAll(elements: Collection<Point>): Boolean = elements.all { it in this }

	fun exactCenter() = (lower + higher).also {
		require(it.x divBy 2)
		require(it.y divBy 2)
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
	constructor(lower: PointI, higher: PointI) : this(lower.sint, higher.sint)
	constructor(xs: IntRange, ys: IntRange) : this(xs.first.s toP ys.first.s, xs.last.s toP ys.last.s)
	constructor(xs: SintRange, ys: SintRange) : this(xs.first toP ys.first, xs.last toP ys.last)


	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: IBounds): Bounds = Bounds(this.lower.max(other.lower), this.higher.min(other.higher))

	fun intersect(other: IBoundsI): Bounds = Bounds(this.lower.max(other.lower.sint), this.higher.min(other.higher.sint))


	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: IBounds): Bounds = Bounds(this.lower.min(other.lower), this.higher.max(other.higher))

	fun merge(other: IBoundsI): Bounds = Bounds(this.lower.min(other.lower.sint), this.higher.max(other.higher.sint))

	fun weight(): Sint = super.area

	fun fracture(other: Bounds): List<Bounds> = cartesianProductOf(
		xs.fracture(other.xs), ys.fracture(other.ys)) { x, y -> Bounds(x, y) }


	fun expand(x: Sint, y: Sint): Bounds {
		val offset = x toP y
		return Bounds(this.lower - offset, this.higher + offset)
	}

	fun expand(i: Sint): Bounds = expand(i, i)
	fun expand(x: Int, y: Int): Bounds = expand(x.s, y.s)
	fun expand(i: Int): Bounds = expand(i.s)
	fun retract(x: Sint, y: Sint): Bounds = expand(-x, -y)
	fun retract(i: Sint): Bounds = expand(-i)
	fun retract(x: Int, y: Int): Bounds = expand(-x, -y)
	fun retract(i: Int): Bounds = expand(-i)

	fun frac(i: Int): List<Bounds> = xs.frac(i).cartesianProduct(ys.frac(i)).map { (a, b) -> Bounds(a, b) }


	companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		val INFINITE: Bounds = (Long.MIN_VALUE toP Long.MIN_VALUE) toB (Int.MAX_VALUE toPI Int.MAX_VALUE)
		val EMPTY: Bounds = Bounds(0 toPI 0, -1 toPI -1)
		val LARGE: Bounds = (Sint.NEG_MEGA toP Sint.NEG_MEGA) toB (Sint.POS_MEGA toP Sint.POS_MEGA)
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
		this.lower = Sint.MAX_VALUE toP Sint.MAX_VALUE
		this.higher = Sint.MIN_VALUE toP Sint.MIN_VALUE
	}

	fun add(point: Point) {
		lower = Point(min(lower.x, point.x), min(lower.y, point.y))
		higher = Point(max(higher.x, point.x), max(higher.y, point.y))
	}

	fun toBounds(): Bounds {
		if (lower.x > higher.x || lower.y > higher.y) return Bounds.EMPTY
		return Bounds(lower, higher)
	}

	override val xSize: Sint
		get() = super.xSize.coerceAtLeast(0)
	override val ySize: Sint
		get() = super.ySize.coerceAtLeast(0)
}

infix fun Point.toB(other: Point): Bounds = Bounds(min(this.x, other.x) toP min(this.y, other.y), max(this.x, other.x) toP max(this.y, other.y))

infix fun Point.toB(other: PointI): Bounds = this toB other.sint
infix fun PointI.toB(other: Point): Bounds = this.sint toB other
infix fun PointI.toB(other: PointI): Bounds = this.sint toB other.sint
infix fun Point.toB(other: PointL): Bounds = this toB other.sint
infix fun PointL.toB(other: Point): Bounds = this.sint toB other
infix fun PointL.toB(other: PointL): Bounds = this.sint toB other.sint


operator fun Point.rangeTo(other: Point) = this toB other
operator fun Point.rem(bounds: IBounds) = x % bounds.xs toP y % bounds.ys

fun Iterable<Point>.bounds(): Bounds {
	val bounds = MutableBounds()
	forEach { bounds.add(it) }
	return bounds.toBounds()
}


@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("printString")
inline fun IBounds.print(transform: (Point) -> String) {
	if (Clock.nY != 0) {
		val yys = if (Clock.nY == -1) ys else ys.reversed()
		val xxs = if (Clock.eX == 1) xs else xs.reversed()
		for (y in yys) {
			for (x in xxs) {
				print(transform(x toP y))
			}
			println()
		}
	} else {
		val xxs = if (Clock.nX == -1) xs else xs.reversed()
		val yys = if (Clock.eY == 1) ys else ys.reversed()
		for (x in xxs) {
			for (y in yys) {
				print(transform(x toP y))
			}
			println()
		}
	}
}

@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
inline fun IBounds.print(transform: (Point) -> Char) {
	print { "" + transform(it) }
}


val BoundsI.sint: Bounds
	get() = Bounds(lower.sint, higher.sint)


val Bounds.upper: Point get() = higher