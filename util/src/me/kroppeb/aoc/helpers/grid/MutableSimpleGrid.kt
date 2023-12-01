package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.BoundsI
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toBI
import me.kroppeb.aoc.helpers.point.toPI

class MutableSimpleGrid<T>(val items: MutableList<MutableList<T>>) : StrictGrid<T>, MutableGrid<T> {
	override val boundsI: BoundsI

	init {
		if (Clock.nX != 0) {
			// x is first index
			boundsI = (0 toPI 0) toBI (items.lastIndex toPI items[0].lastIndex)
		} else {
			// y is first index
			boundsI = (0 toPI 0) toBI (items[0].lastIndex toPI items.lastIndex)
		}
	}

	override fun set(index: PointI, item: T) {
		when {
			Clock.nX < 0 -> items[index.x][index.y] = item
			Clock.nX > 0 -> items[items.size - 1 - index.x][index.y] = item
			Clock.nY < 0 -> items[index.y][index.x] = item
			else -> items[items.size - 1 - index.y][index.x] = item
		}
	}

	override fun get(index: PointI): T {
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
}


fun <T> List<List<T>>.mutableGrid(): MutableSimpleGrid<T> {
	if(isEmpty() || first().isEmpty())
		error("empty grid")
	val l = this[0].size
	if(subList(0,size - 1).any{it.size != l})
		error("non consistent length")
	if(last().isEmpty())
		return MutableSimpleGrid(subList(0, size - 1).mut2())
	if(last().size != l)
		error("non consistent length")
	return MutableSimpleGrid(this.mut2())
}

fun <T> Iterable<List<List<T>>>.mutableGrids(): List<MutableSimpleGrid<T>> = map{it.mutableGrid()}

inline fun <T, R> MutableSimpleGrid<T>.map(block: (T) -> R) = this.items.map2(block).grid()
inline fun <T, R> MutableSimpleGrid<T>.mapIndexed(block: (PointI, T) -> R) = this.items.mapIndexed { i, a ->
	a.mapIndexed{ j,b ->
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

inline fun <T, R> MutableSimpleGrid<T>.forEachIndexed(block: (PointI, T) -> R) = this.boundsI.forEach { block(it, get(it)) }

fun <T> SimpleGrid<T>.mutable() = this.items.mutableGrid()
