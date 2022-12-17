package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.context.IterableOpps.isNotEmpty
import me.kroppeb.aoc.helpers.sint.*

/**
 * lower and higher are inclusive
 */
abstract class IBounds3D : Iterable<Point3D> {
	abstract val lower: Point3D
	abstract val higher: Point3D
	operator fun contains(point: Point3D): Boolean = point.x in xs && point.y in ys && point.z in zs
	operator fun contains(point: Point3DI): Boolean = point.sint in this

	val isSquare: Boolean get() = (higher.x - lower.x) == (higher.y - lower.y) && (higher.y - lower.y) == (higher.z - lower.z)

	override operator fun iterator() = xs.flatMap { x -> ys.flatMap { y -> zs.map { z -> x toP y toP z } } }.iterator()

	val xs get() = this.lower.x..this.higher.x
	val ys get() = this.lower.y..this.higher.y
	val zs get() = this.lower.z..this.higher.z

	open val xSize get() = (this.higher.x - this.lower.x + 1)
	open val ySize get() = (this.higher.y - this.lower.y + 1)
	open val zSize get() = (this.higher.z - this.lower.z + 1)
	val size get() = xSize toP ySize toP zSize
	val volume get() = xSize * ySize * zSize
	fun isEmpty() = xs.isEmpty() || ys.isEmpty() || zs.isEmpty()

	fun exactCenter() = (lower + higher).also {
		require(it.x divBy 2)
		require(it.y divBy 2)
		require(it.z divBy 2)
	} / 2
}

data class Bounds3D(override val lower: Point3D, override val higher: Point3D) : IBounds3D(),
	BoundsN<Bounds3D, Point3D, Sint> {
	constructor(lower: Point3DI, higher: Point3DI) : this(lower.sint, higher.sint)
	constructor(xs: IntRange, ys: IntRange, zs: IntRange) : this(
		xs.first.s toP ys.first.s toP zs.first.s,
		xs.last.s toP ys.last.s toP zs.last.s
	)

	constructor(xs: SintRange, ys: SintRange, zs: SintRange) : this(
		xs.first toP ys.first toP zs.first,
		xs.last toP ys.last toP zs.last
	)


	/**
	 * ∀x:x in this && x in other <-> x in this.intersect(other)
	 */
	fun intersect(other: IBounds3D): Bounds3D = Bounds3D(
		this.lower.max(other.lower),
		this.higher.min(other.higher)
	)

	override fun intersect(other: Bounds3D): Bounds3D = intersect(other as IBounds3D)

	/**
	 * ∀x:x in this || x in other -> x in this.merge(other)
	 * while keeping the amount of x:
	 * 		x in this.merge(other) && x in! this && x in! other
	 * 	as small as possible
	 *
	 */
	fun merge(other: IBounds3D): Bounds3D = Bounds3D(
		this.lower.min(other.lower),
		this.higher.max(other.higher)
	)

	override fun merge(other: Bounds3D): Bounds3D = this.merge(other as IBounds3D)

	override fun size(): Point3D = super.size

	override fun weight(): Sint = super.volume

	override fun fracture(other: Bounds3D): Collection<Bounds3D> {
		val xr = this.xs.fracture(other.xs)
		val yr = this.ys.fracture(other.ys)
		val zr = this.zs.fracture(other.zs)
		return xr.flatMap { x -> yr.flatMap { y -> zr.map { z -> Bounds3D(x, y, z) } } }
	}

	companion object {
		/**
		∀x Point: x in INFINITE
		∀x Bound: x.intersect(INFINITE) == x
		∀x Bound: x.merge(INFINITE) == INFINITE
		 */
		val INFINITE =
			(Sint.MIN_VALUE toP Sint.MIN_VALUE toP Sint.MIN_VALUE) toB (Sint.MAX_VALUE toP Sint.MAX_VALUE toP Sint.MAX_VALUE)
		val EMPTY = Bounds3D(0 toP 0 toP 0, -1 toP -1 toP -1)
	}
}

class MutableBounds3D : IBounds3D {
	override var lower: Point3D
	override var higher: Point3D

	constructor(start: Point3D) : super() {
		this.lower = start
		this.higher = start
	}

	constructor() : super() {
		this.lower = Sint.MAX_VALUE toP Sint.MAX_VALUE toP Sint.MAX_VALUE
		this.higher = Sint.MIN_VALUE toP Sint.MIN_VALUE toP Sint.MIN_VALUE
	}

	fun add(point: Point3D) {
		lower = lower.min(point)
		higher = higher.max(point)
	}

	fun toBounds(): Bounds3D {
		if (lower.x > higher.x || lower.y > higher.y) return Bounds3D.EMPTY
		return Bounds3D(lower, higher)
	}

	override val xSize: Sint
		get() = super.xSize.coerceAtLeast(0)
	override val ySize: Sint
		get() = super.ySize.coerceAtLeast(0)

	override val zSize: Sint
		get() = super.zSize.coerceAtLeast(0)
}

infix fun Point3D.toB(other: Point3D): Bounds3D = Bounds3D(
	min(this.x, other.x) toP min(this.y, other.y) toP min(this.z, other.z),
	max(this.x, other.x) toP max(this.y, other.y) toP max(this.z, other.z),
)

operator fun Point3D.rangeTo(other: Point3D) = this toB other
operator fun Point3D.rem(bounds: IBounds3D) = x % bounds.xs toP y % bounds.ys

fun Iterable<Point3D>.bounds(): Bounds3D {
	val bounds = MutableBounds3D()
	forEach { bounds.add(it) }
	return bounds.toBounds()
}

