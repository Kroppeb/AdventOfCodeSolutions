package solutions

import helpers.getData
import kotlin.math.abs

private fun part1(data:List<List<Pair<Char, Int>>>){
	val w1 = mutableSetOf<Pair<Int,Int>>()
	var x = 0
	var y = 0
	for ((c, d) in data[0]){
		val (dx, dy) = when(c){
			'R' -> 1 to 0
			'U' -> 0 to 1
			'D' -> 0 to -1
			'L' -> -1 to 0
			else -> error("")
		}

		for(dd in 1 .. d){
			x+= dx
			y+=dy
			w1.add(x to y)
		}
	}

	x=0
	y=0
	val w2 = mutableSetOf<Pair<Int,Int>>()
	for ((c, d) in data[1]){
		val (dx, dy) = when(c){
			'R' -> 1 to 0
			'U' -> 0 to 1
			'D' -> 0 to -1
			'L' -> -1 to 0
			else -> error("")
		}


		for(dd in 1 .. d){
			x+= dx
			y+=dy
			w2.add(x to y)
		}
	}



	val keys = w1.intersect(w2)


	val q = keys.map {(a,b) -> abs(a) + abs(b)  }.min()
	println(q)
}

private fun part2(data:List<List<Pair<Char, Int>>>){
	val w1 = mutableMapOf<Pair<Int,Int>,Int>()
	var x = 0
	var y = 0
	var td = 0
	for ((c, d) in data[0]){
		val (dx, dy) = when(c){
			'R' -> 1 to 0
			'U' -> 0 to 1
			'D' -> 0 to -1
			'L' -> -1 to 0
			else -> error("")
		}

		for(dd in 1 .. d){
			x+= dx
			y+=dy
			td+=1

			w1.putIfAbsent(x to y, td)
		}
	}

	x=0
	y=0
	td=0
	val w2 = mutableMapOf<Pair<Int,Int>, Int>()
	for ((c, d) in data[1]){
		val (dx, dy) = when(c){
			'R' -> 1 to 0
			'U' -> 0 to 1
			'D' -> 0 to -1
			'L' -> -1 to 0
			else -> error("")
		}


		for(dd in 1 .. d){
			x+= dx
			y+=dy
			td+=1
			w2.putIfAbsent(x to y, td)
		}
	}


	val keys = w1.keys.toMutableSet()
	keys.retainAll(w2.keys)


	val q = keys.toList().map {abs(w1[it]!!) + abs(w2[it]!!)  }.min()
	println(q)
}

fun main(){
	val ints = getData(3).split("\r\n").map{a->a.split(",").map{it[0] to it.substring(1).toInt()}}
	part1(ints)
	part2(ints)

}