package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.Bounds
import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toB
import me.kroppeb.aoc.helpers.point.toP

class SimpleGrid<out T>(val items: List<List<T>>) : StrictGrid<T>, Iterable<BoundedGridPoint<T>> {
	override val bounds: Bounds

	init {
		if (Clock.nX != 0) {
			// x is first index
			bounds = (0 toP 0) toB (items.lastIndex toP items[0].lastIndex)
		} else {
			// y is first index
			bounds = (0 toP 0) toB (items[0].lastIndex toP items.lastIndex)
		}
	}

	override fun get(index: Point): T {
		return when {
			Clock.nX < 0 -> items[index.x][index.y]
			Clock.nX > 0 -> items[items.size - 1 - index.x][index.y]
			Clock.nY < 0 -> items[index.y][index.x]
			else -> items[items.size - 1 - index.y][index.x]
		}
	}

	fun rows(): Iterable<List<T>> = items
	fun cols(): Iterable<List<T>> = items.transpose()
	fun rowsCols(): Iterable<List<T>> = items + items.transpose()
	fun diag1(): List<T> {
		assert(bounds.isSquare)
		return items.mapIndexed { i, row -> row[i] }
	}
	fun diag2(): List<T> {
		assert(bounds.isSquare)
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
	override fun iterator(): Iterator<BoundedGridPoint<T>> = bounds.map{this.getBp(it)}.iterator()

	fun getBpOrNull(point: Point): BoundedGridPoint<T>? =
		if (point in bounds) BoundedGridPoint(point, this[point], this) else null

	fun getBp(point: Point) = getBpOrNull(point) ?: throw IndexOutOfBoundsException(point.toString())
}


fun <T> List<List<T>>.grid(): SimpleGrid<T> {
	if(isEmpty() || first().isEmpty())
		error("empty grid")
	val l = this[0].size
	if(subList(0,lastIndex - 1).any{it.size != l})
		error("non consistent length")
	if(last().isEmpty())
		return SimpleGrid(subList(0, lastIndex))
	if(last().size != l)
		error("non consistent length")
	return SimpleGrid(this)
}

fun <T> Iterable<List<List<T>>>.grids(): List<SimpleGrid<T>> = map{it.grid()}

inline fun <T, R> SimpleGrid<T>.map(block: (T) -> R) = this.items.map2(block).grid()
@Deprecated("exists for historical reasons")
inline fun <T, R> SimpleGrid<T>.mapIndexedOld(block: (Point, T) -> R) = this.items.mapIndexed { i, a ->
	a.mapIndexed{ j,b ->
		val p = if (Clock.nX != 0) {
			// x is first index
			i toP j
		} else {
			// y is first index
			j toP i
		}
		block(p, b)
	}
}

inline fun <T, R> SimpleGrid<T>.mapIndexed(block: (Point, T) -> R) = this.items.mapIndexed { i, a ->
	a.mapIndexed{ j,b ->
		val p = if (Clock.nX != 0) {
			// x is first index
			i toP j
		} else {
			// y is first index
			j toP i
		}
		block(p, b)
	}
}.grid()
inline fun <T, R> SimpleGrid<T>.forEachIndexed(block: (Point, T) -> R) = this.bounds.forEach { block(it, get(it)) }
