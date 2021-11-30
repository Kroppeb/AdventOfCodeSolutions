@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d24

import collections.counter
import grid.Clock
import helpers.Lines
import helpers.getLines
import helpers.toP

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val points = data.map { line ->
		var i = 0
		var p = 0 toP 0
		println(line)
		while (i < line.length) {
			when (line[i]) {
				'e' -> {p =  p.east; i++ }
				'w' -> {p =  p.west; i++ }
				's' -> {p =  if (line[i+1] == 'e') p.south else p.south.west; i += 2 }
				'n' -> {p =  if (line[i+1] == 'w') p.north else p.north.east; i += 2 }
			}
			println(p)
		}
		p
	}.counter()
	val pp = points.counts.filter { (_,v) -> v % 2 == 1 }
	println(points.counts)
	println(pp.count())
}

private fun part2(data: Data) {
	val points = data.map { line ->
		var i = 0
		var p = 0 toP 0
		println(line)
		while (i < line.length) {
			when (line[i]) {
				'e' -> {p =  p.east.east; i++ }
				'w' -> {p =  p.west.west; i++ }
				's' -> {p =  if (line[i+1] == 'e') p.south.east else p.south.west; i += 2 }
				'n' -> {p =  if (line[i+1] == 'w') p.north.west else p.north.east; i += 2 }
			}
		}
		p
	}.counter()
	var pp = points.counts.filter { (_,v) -> v % 2 == 1 }.keys.toSet()

	repeat(100){
		//println(pp)
		//for(x in pp.minBy { it.x }!!.x..(pp.maxBy { it.x }!!.x)){
		//	for(y in pp.minBy { it.y }!!.y..(pp.maxBy { it.y }!!.y)){
		//		print(if(x toP y in pp) '#' else '.')
		//	}
		//	println()
		//}
		val ne = pp.flatMap { listOf(it.east.east, it.west.west, it.south.east, it.south.west, it.north.west, it.north.east) }.counter().counts
		//println(ne)

		val new = (pp.union(ne.keys)).toSet().filter{
			if(it in pp){
				ne[it] == 1 || ne[it] == 2
			} else {
				ne[it] == 2
			}
		}.toSet()
		pp = new
		println("$it: ${pp.size}")
	}

	println(pp.size)
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_24)
	part1(data)
	part2(data)
}


fun <T> T.log(): T = also { println(this) }