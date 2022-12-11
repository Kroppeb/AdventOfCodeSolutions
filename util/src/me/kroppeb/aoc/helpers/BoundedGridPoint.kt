package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toP
import me.kroppeb.aoc.helpers.point.toPoint

data class BoundedGridPoint<out T>(val p: Point, val v: T, val g: SimpleGrid<T>) {
	val b get() = g.bounds

	// TODO: evaluate whether having these as lazy actually helps or hurts
	val right get() = g.getBpOrNull(this.p.right)
	val down get() = g.getBpOrNull(this.p.down)
	val left get() = g.getBpOrNull(this.p.left)
	val up get() = g.getBpOrNull(this.p.up)

	val north get() = up
	val east get() = right
	val south get() = down
	val west get() = left

	private fun Iterable<Point>.fix() = mapNotNull { g.getBpOrNull(it) }

	fun getQuadNeighbours() = listOf(p.right, p.down, p.left, p.up).fix()
	fun getDiagonalNeighbours() = listOf(p.right.down, p.left.down, p.left.up, p.right.up).fix()

	fun getOctNeighbours() =
		listOf(p.right, p.right.down, p.down, p.left.down, p.left, p.left.up, p.up, p.right.up).fix()

	fun getQuadNeighbourHood() = listOf(this).plus(getQuadNeighbours())
	fun getDiagonalNeighbourHood() = listOf(this) + getDiagonalNeighbours()
	fun getOctNeighbourHood() = listOf(this) + getOctNeighbours()

	fun getMooreNeighbours() = getOctNeighbours()
	fun getVonNeumannNeighbours() = getQuadNeighbours()

	operator fun minus(other: Point) = g.getBp(p.x - other.x toP p.y - other.y)
	operator fun plus(other: Point) = g.getBp(p.x + other.x toP p.y + other.y)

	operator fun minus(other: Char) = this - other.toPoint()
	operator fun plus(other: Char) = this + other.toPoint()

	fun sqrDistTo(other: Point): Int = (this.p - other).sqrDist()
	fun distTo(other: Point): Double = (this.p - other).dist()
	fun manDistTo(other: Point): Int = (this.p - other).manDist()

	fun sqrDistTo(other: BoundedGridPoint<*>): Int = (this.p - other.p).sqrDist()
	fun distTo(other: BoundedGridPoint<*>): Double = (this.p - other.p).dist()
	fun manDistTo(other: BoundedGridPoint<*>): Int = (this.p - other.p).manDist()

	fun isLeftOf(other: Point) = Clock.left.dot(this.p) > Clock.left.dot(other)
	fun isRightOf(other: Point) = Clock.right.dot(this.p) > Clock.right.dot(other)
	fun isAbove(other: Point) = Clock.up.dot(this.p) > Clock.up.dot(other)
	fun isBelow(other: Point) = Clock.down.dot(this.p) > Clock.down.dot(other)
	fun sameLeftRight(other: Point) = Clock.left.dot(this.p) == Clock.left.dot(other)
	fun sameUpDown(other: Point) = Clock.up.dot(this.p) == Clock.up.dot(other)

	fun isLeftOf(other: BoundedGridPoint<*>) = isLeftOf(other.p)
	fun isRightOf(other: BoundedGridPoint<*>) = isRightOf(other.p)
	fun isAbove(other: BoundedGridPoint<*>) = isAbove(other.p)
	fun isBelow(other: BoundedGridPoint<*>) = isBelow(other.p)
	fun sameLeftRight(other: BoundedGridPoint<*>) = sameLeftRight(other.p)
	fun sameUpDown(other: BoundedGridPoint<*>) = sameUpDown(other.p)

	fun northsInc() = p.northsInc().takeWhile { it in b }.map{g.getBp(it)}.toList()
	fun southsInc() = p.southsInc().takeWhile { it in b }.map{g.getBp(it)}.toList()
	fun eastsInc() = p.eastsInc().takeWhile { it in b }.map{g.getBp(it)}.toList()
	fun westsInc() = p.westsInc().takeWhile { it in b }.map{g.getBp(it)}.toList()

	fun norths() = p.norths().takeWhile { it in b }.map{g.getBp(it)}.toList()
	fun souths() = p.souths().takeWhile { it in b }.map{g.getBp(it)}.toList()
	fun easts() = p.easts().takeWhile { it in b }.map{g.getBp(it)}.toList()
	fun wests() = p.wests().takeWhile { it in b }.map{g.getBp(it)}.toList()
}
