package me.kroppeb.aoc.helpers.grid

import me.kroppeb.aoc.helpers.point.BoundsI
import me.kroppeb.aoc.helpers.point.PointI

interface Grid<out T> {
	operator fun get(index: PointI): T
	val boundsI: BoundsI
}

interface StrictGrid<out T> : Grid<T> {
}

interface ResizeableGrid<out T> : Grid<T> {
	fun expand(amount:Int)
	fun expand(x:Int=0, y:Int=0)

	fun contract(amount:Int)
	fun contract(x:Int=0, y:Int=0)
}

interface MutableGrid<T> : Grid<T> {
	operator fun set(index: PointI, item:T)
}
