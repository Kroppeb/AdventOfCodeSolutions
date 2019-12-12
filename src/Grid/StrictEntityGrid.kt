package Grid

import helpers.Bounds
import helpers.Point
import helpers.toB
import helpers.toP

class StrictPointGrid(override val bounds: Bounds, val points:Set<Point>) :StrictGrid<Boolean> {
	override fun get(index: Point) = index in bounds && index in points
}

inline fun <T>Iterable<Iterable<T>>.entityGrid(predicate: (T)->Boolean): StrictPointGrid{
	var maxX = 0
	var maxY = 0
	val points= mutableSetOf<Point>()
	this.forEachIndexed { x: Int, iterable: Iterable<T> ->
		if(x > maxX)
			maxX = x
		iterable.forEachIndexed { y, t ->
			if (y > maxY)
				maxY = y
			if(predicate(t))
				points += x toP y
		}
	}
	return StrictPointGrid((0 toP 0) toB (maxX toP maxY), points)
}


/*
class StrictTypedEntityGrid<T> : StrictEntityGrid<T>() {
}
 */