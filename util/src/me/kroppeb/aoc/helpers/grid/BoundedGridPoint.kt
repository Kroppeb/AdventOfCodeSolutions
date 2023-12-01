package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

data class BoundedGridPoint<T>(val p: Point, val v: T, val g: SimpleGrid<T>) {

	constructor(pi: PointI, v: T, g: SimpleGrid<T>) : this(pi.sint, v, g)
	val b get() = g.bounds
	val pi: PointI get() = p.int

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

	operator fun minus(other: PointI) = g.getBp(p.x - other.x.s toP p.y - other.y.s)
	operator fun plus(other: PointI) = g.getBp(p.x + other.x.s toP p.y + other.y.s)
	operator fun minus(other: Point) = g.getBp(p.x - other.x toP p.y - other.y)
	operator fun plus(other: Point) = g.getBp(p.x + other.x toP p.y + other.y)

	operator fun minus(other: Char) = this - other.toPoint()
	operator fun plus(other: Char) = this + other.toPoint()

	fun sqrDistTo(other: PointI): Int = (this.pi - other).sqrDist()
	fun distTo(other: PointI): Double = (this.pi - other).dist()
	fun manDistTo(other: PointI): Int = (this.pi - other).manDist()

	fun sqrDistTo(other: Point): Sint = (this.p - other).sqrDist()
	fun distTo(other: Point): Double = (this.p - other).dist()
	fun manDistTo(other: Point): Sint = (this.p - other).manDist()

	fun sqrDistTo(other: BoundedGridPoint<*>): Int = (this.pi - other.pi).sqrDist()
	fun distTo(other: BoundedGridPoint<*>): Double = (this.pi - other.pi).dist()
	fun manDistTo(other: BoundedGridPoint<*>): Int = (this.pi - other.pi).manDist()

	fun isLeftOf(other: PointI) = Clock.leftI.dot(this.pi) > Clock.leftI.dot(other)
	fun isRightOf(other: PointI) = Clock.rightI.dot(this.pi) > Clock.rightI.dot(other)
	fun isAbove(other: PointI) = Clock.upI.dot(this.pi) > Clock.upI.dot(other)
	fun isBelow(other: PointI) = Clock.downI.dot(this.pi) > Clock.downI.dot(other)
	fun sameLeftRight(other: PointI) = Clock.leftI.dot(this.pi) == Clock.leftI.dot(other)
	fun sameUpDown(other: PointI) = Clock.upI.dot(this.pi) == Clock.upI.dot(other)

	fun isLeftOf(other: Point) = Clock.leftI.dot(this.p) > Clock.leftI.dot(other)
	fun isRightOf(other: Point) = Clock.rightI.dot(this.p) > Clock.rightI.dot(other)
	fun isAbove(other: Point) = Clock.upI.dot(this.p) > Clock.upI.dot(other)
	fun isBelow(other: Point) = Clock.downI.dot(this.p) > Clock.downI.dot(other)
	fun sameLeftRight(other: Point) = Clock.leftI.dot(this.p) == Clock.leftI.dot(other)
	fun sameUpDown(other: Point) = Clock.upI.dot(this.p) == Clock.upI.dot(other)

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

	override fun toString(): String {
		return "BoundedGridPoint(p=$pi, v=$v)"
	}
}
