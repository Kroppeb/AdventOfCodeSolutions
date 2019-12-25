package solutions

import helpers.*
import kotlin.collections.*
import grid.entityGrid


private fun bio1(s: Set<Point>): Int {
	var b = 0
	var p = 1
	for (i in (0..4))
		for (j in (0..4)) {
			if (i toP j in s)
				b += p
			p *= 2
		}
	return b
}

private fun part1(data: Data) {
	val bios = mutableSetOf<Int>()
	var current = data
	while (true) {
		for (i in (0..4)) {
			for (j in (0..4)) {
				if (i toP j in current)
					print('#')
				else
					print('.')
			}
			println()
		}
		println()
		val bio = bio1(current)
		if( bio in bios){
			println(bio)
			return
		}
		bios.add(bio)
		val counts = current.flatMap { it.getQuadNeighbours() }.groupingBy { it }.eachCount()
		current = counts.filter { (p, c) ->
			p in (0 toP 0) toB (4 toP 4) &&
			if (p in current) {
				c == 1
			}
			else{
				c == 1 || c == 2
			}
		}.map{(p,_)->p}.toSet()
	}
}

private val empty = setOf<Point>()

private fun part2(data: Data) {
	var layers = mutableMapOf(0 to data)
	repeat(200){
		val leftO = layers.map{(k,v) -> k to v.count{it.y == 0}}.toMap()
		val rightO = layers.map{(k,v) -> k to v.count{it.y == 4}}.toMap()
		val upO = layers.map{(k,v) -> k to v.count{it.x == 0}}.toMap()
		val downO = layers.map{(k,v) -> k to v.count{it.x == 4}}.toMap()

		for(l in layers.keys.flatMap { listOf(it-1, it+1) }.filter { it !in layers }){
			layers[l] = empty
		}

		layers = layers.map{(i,current) ->
			val counts = current.flatMap { it.getQuadNeighbours()}
					.groupingBy { it }.eachCount().toMutableMap()
			counts.merge(1 toP 2, upO[i+1]?:0, Int::plus)
			counts.merge(3 toP 2, downO[i+1]?:0, Int::plus)
			counts.merge(2 toP 1, leftO[i+1]?:0, Int::plus)
			counts.merge(2 toP 3, rightO[i+1]?:0, Int::plus)

			for(c in 0..4){
				counts.merge(0 toP c, if(layers[i-1]?.contains(1 toP 2) == true) 1 else 0, Int::plus)
				counts.merge(c toP 0, if(layers[i-1]?.contains(2 toP 1) == true) 1 else 0, Int::plus)
				counts.merge(4 toP c, if(layers[i-1]?.contains(3 toP 2) == true) 1 else 0, Int::plus)
				counts.merge(c toP 4, if(layers[i-1]?.contains(2 toP 3) == true) 1 else 0, Int::plus)
			}

			counts[2 toP 2] = 0

			i to counts.filter { (p, c) ->
				p in (0 toP 0) toB (4 toP 4) &&
						if (p in current) {
							c == 1
						}
						else{
							c == 1 || c == 2
						}
			}.map{(p,_)->p}.toSet()
		}.filter{(_,v) -> v.isNotEmpty()}.toMap().toMutableMap()
/*
		println("step ${it+1}")
		println()
		for((i, l) in layers.map{it}.sortedBy { it.key }){
			println("Depth $i:")
			for (i in (0..4)) {
				for (j in (0..4)) {
					if (i toP j in l)
						print('#')
					else
						print('.')
				}
				println()
			}
		}*/
	}

	println(layers.map{(_,v)->v.size}.sum())
}

typealias Data = Set<Point>

fun main() {
	val data: Data = getLines(24).map { it.map { it } }.entityGrid { it == '#' }.points
	//part1(data)
	part2(data)
}