/*package solutions

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*

val xxxxx = Clock(3,6);

private fun part1(data: Data) = runBlocking {
	val clay = data.flatMap{
		val (a,b,c) = it.getInts()
		if(it.first() == 'x'){
			(b..c).map{a toP it}
		} else {
			(b..c).map{it toP a}
		}
	}.entityGrid()
	val bounds = clay.bounds.lower.left toB clay.bounds.higher.right
	val spring = 500 toP max(0,bounds.higher.y)
	val grid = mutableSetOf<Point>()
	fun recurse(point: Point): Boolean{
		if(!grid.add(point))
			return false
		if(point.down in bounds){
			if (point.down !in clay.points){
				if(recurse(point.down)){
					// floored

				}
			}
			TODO()
		} else
			return false
	}
}

private fun part2(data: Data) = runBlocking {
}

private typealias Data = List<String>


fun main() {
	val data: Data = getLines(2018_17)
	part1(data)
	part2(data)
}*/