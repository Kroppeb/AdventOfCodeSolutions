package grid

import helpers.*

class SimpleGrid<out T>(val items: List<List<T>>) : StrictGrid<T> {
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
inline fun <T, R> SimpleGrid<T>.map(block: (T) -> R) = this.items.map2(block).grid()
inline fun <T, R> SimpleGrid<T>.mapIndexed(block: (Point, T) -> R) = this.bounds.map { block(it, get(it)) }
inline fun <T, R> SimpleGrid<T>.forEachIndexed(block: (Point, T) -> R) = this.bounds.forEach { block(it, get(it)) }