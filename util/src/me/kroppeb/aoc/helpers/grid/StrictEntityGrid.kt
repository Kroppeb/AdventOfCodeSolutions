package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.point.BoundsI
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toBI
import me.kroppeb.aoc.helpers.point.toPI

class StrictPointGrid(override val boundsI: BoundsI, var points: Set<PointI>) : StrictGrid<Boolean> {
	override fun get(index: PointI) = index in boundsI && index in points
}

inline fun <T> SimpleGrid<T>.entityGrid(predicate: (T) -> Boolean): StrictPointGrid {
	val points = mutableSetOf<PointI>()
	forEachIndexed { point, t -> if(predicate(t)) points.add(point) }
	return StrictPointGrid(boundsI, points);
}

inline fun <T> List<List<T>>.entityGrid(predicate: (T) -> Boolean): StrictPointGrid = grid().entityGrid(predicate)

fun Iterable<PointI>.entityGrid(): StrictPointGrid {
	var maxX = Int.MIN_VALUE
	var maxY = Int.MIN_VALUE
	var minX = Int.MAX_VALUE
	var minY = Int.MAX_VALUE

	val points = mutableSetOf<PointI>()
	this.forEach { p ->
		val (x, y) = p
		if (x > maxX)
			maxX = x
		if (x < minX)
			minX = x
		if (y > maxY)
			maxY = y
		if (y < minY)
			minY = y
		points += p

	}
	return StrictPointGrid((minX toPI minY) toBI (maxX toPI maxY), points)
}


class StrictTypedEntityGrid<T>(override val boundsI: BoundsI, val items: Map<PointI, T>) : StrictGrid<T?> {
	override fun get(index: PointI) = items[index]
}
