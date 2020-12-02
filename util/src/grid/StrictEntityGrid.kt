package grid

import helpers.Bounds
import helpers.Point
import helpers.toB
import helpers.toP

class StrictPointGrid(override val bounds: Bounds, val points: Set<Point>) : StrictGrid<Boolean> {
	override fun get(index: Point) = index in bounds && index in points
}

inline fun <T> SimpleGrid<T>.entityGrid(predicate: (T) -> Boolean): StrictPointGrid {
	val points = mutableSetOf<Point>()
	forEachIndexed { point, t -> if(predicate(t)) points.add(point) }
	return StrictPointGrid(bounds, points);
}

inline fun <T> List<List<T>>.entityGrid(predicate: (T) -> Boolean): StrictPointGrid = grid().entityGrid(predicate)

fun Iterable<Point>.entityGrid(): StrictPointGrid {
	var maxX = Int.MIN_VALUE
	var maxY = Int.MIN_VALUE
	var minX = Int.MAX_VALUE
	var minY = Int.MAX_VALUE

	val points = mutableSetOf<Point>()
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
	return StrictPointGrid((minX toP minY) toB (maxX toP maxY), points)
}


class StrictTypedEntityGrid<T>(override val bounds: Bounds, val items: Map<Point, T>) : StrictGrid<T?> {
	override fun get(index: Point) = items[index]
}
