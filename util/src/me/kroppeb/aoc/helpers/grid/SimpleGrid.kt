package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.graph.CGraph
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

class SimpleGrid<T>(val items: List<List<T>>) : StrictGrid<T>, Iterable<BoundedGridPoint<T>> {
	override val boundsI: BoundsI
	val bounds: Bounds

	init {
		if (Clock.nX != 0) {
			// x is first index
			boundsI = (0 toPI 0) toBI (items.lastIndex toPI items[0].lastIndex)
		} else {
			// y is first index
			boundsI = (0 toPI 0) toBI (items[0].lastIndex toPI items.lastIndex)
		}
		bounds = boundsI.sint
	}

	override fun get(index: PointI): T {
		return when {
			Clock.nX < 0 -> items[index.x][index.y]
			Clock.nX > 0 -> items[items.size - 1 - index.x][index.y]
			Clock.nY < 0 -> items[index.y][index.x]
			else -> items[items.size - 1 - index.y][index.x]
		}
	}

	fun get(index: Point): T {
		return when {
			Clock.nX < 0 -> items[index.x.i][index.y.i]
			Clock.nX > 0 -> items[items.size - 1 - index.x.i][index.y.i]
			Clock.nY < 0 -> items[index.y.i][index.x.i]
			else -> items[items.size - 1 - index.y.i][index.x.i]
		}
	}

	fun rows(): Iterable<List<T>> = items
	fun cols(): Iterable<List<T>> = items.transpose()
	fun rowsCols(): Iterable<List<T>> = items + items.transpose()
	fun diag1(): List<T> {
		assert(boundsI.isSquare)
		return items.mapIndexed { i, row -> row[i] }
	}

	fun diag2(): List<T> {
		assert(boundsI.isSquare)
		return items.mapIndexed { i, row -> row[items.size - 1 - i] }
	}

	fun diagonals(): Iterable<List<T>> = listOf(diag1(), diag2())
	fun rowsColsDiagonals(): Iterable<List<T>> = rowsCols() + diagonals()

	fun allItems(): Iterable<T> = items.flatten()

	override fun toString(): String {
		return items.joinToString("\n") { it.joinToString(" ") }
	}

	override fun equals(other: Any?): Boolean = other is SimpleGrid<*> && this.items == other.items
	override fun hashCode(): Int = this.items.hashCode()
	override fun iterator(): Iterator<BoundedGridPoint<T>> = boundsI.map { this.getBp(it) }.iterator()

	fun getBpOrNull(point: PointI): BoundedGridPoint<T>? =
		if (point in boundsI) BoundedGridPoint(point, this[point], this) else null

	fun getBpOrNull(point: Point): BoundedGridPoint<T>? =
		if (point in bounds) BoundedGridPoint(point, this.get(point), this) else null

	fun getBp(point: PointI) = getBpOrNull(point) ?: throw IndexOutOfBoundsException(point.toString())

	fun getBp(point: Point) = getBpOrNull(point) ?: throw IndexOutOfBoundsException(point.toString())

	fun asQuadGraph(): CGraph<BoundedGridPoint<T>> = asQuadGraphWeight { _, _ -> 1.s }

	fun asQuadGraphWeight(weight: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Sint?): CGraph<BoundedGridPoint<T>> = CGraph(
		this.associateWith { a -> a.getQuadNeighbours().associateWithNotNull { b -> weight(a, b) }.filterNotNullValues() }
	)

	fun asQuadGraph(connected: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Boolean): CGraph<BoundedGridPoint<T>> =
		asQuadGraphWeight { a, b -> if (connected(a, b)) 1.s else null }

	fun asOctGraph(): CGraph<BoundedGridPoint<T>> = asOctGraphWeight { _, _ -> 1.s }

	fun asOctGraphWeight(weight: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Sint?): CGraph<BoundedGridPoint<T>> = CGraph(
		this.associateWith { a -> a.getOctNeighbours().associateWithNotNull { b -> weight(a, b) }.filterNotNullValues() }
	)

	fun asOctGraph(connected: (BoundedGridPoint<T>, BoundedGridPoint<T>) -> Boolean): CGraph<BoundedGridPoint<T>> =
		asOctGraphWeight { a, b -> if (connected(a, b)) 1.s else null }
}


fun <T> List<List<T>>.grid(): SimpleGrid<T> {
	if (isEmpty() || first().isEmpty())
		error("empty grid")
	val l = this[0].size
	if (subList(0, lastIndex - 1).any { it.size != l })
		error("non consistent length")
	if (last().isEmpty())
		return SimpleGrid(subList(0, lastIndex))
	if (last().size != l)
		error("non consistent length")
	return SimpleGrid(this)
}

fun <T> Iterable<List<List<T>>>.grids(): List<SimpleGrid<T>> = map { it.grid() }

inline fun <T, R> SimpleGrid<T>.map(block: (T) -> R) = this.items.map2(block).grid()

@Deprecated("exists for historical reasons")
inline fun <T, R> SimpleGrid<T>.mapIndexedOld(block: (PointI, T) -> R) = this.items.mapIndexed { i, a ->
	a.mapIndexed { j, b ->
		val p = if (Clock.nX != 0) {
			// x is first index
			i toPI j
		} else {
			// y is first index
			j toPI i
		}
		block(p, b)
	}
}

inline fun <T, R> SimpleGrid<T>.mapIndexed(block: (PointI, T) -> R) = this.items.mapIndexed { i, a ->
	a.mapIndexed { j, b ->
		val p = if (Clock.nX != 0) {
			// x is first index
			i toPI j
		} else {
			// y is first index
			j toPI i
		}
		block(p, b)
	}
}.grid()

inline fun <T, R> SimpleGrid<T>.forEachIndexed(block: (PointI, T) -> R) = this.boundsI.forEach { block(it, get(it)) }
