package Grid

import helpers.Bounds
import helpers.Point

interface Grid<out T> {
	operator fun get(index: Point): T
	val bounds: Bounds
}