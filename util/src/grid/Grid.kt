package grid

import helpers.Bounds
import helpers.Point

interface Grid<out T> {
	operator fun get(index: Point): T
	val bounds: Bounds
}

interface StrictGrid<out T> : Grid<T> {
}

interface ResizeableGrid<out T> : Grid<T> {
	fun expand(amount:Int)
	fun expand(x:Int=0, y:Int=0)

	fun contract(amount:Int)
	fun contract(x:Int=0, y:Int=0)
}

interface MutableGrid<T> : Grid<T>{
	operator fun set(index: Point, item:T)
}
