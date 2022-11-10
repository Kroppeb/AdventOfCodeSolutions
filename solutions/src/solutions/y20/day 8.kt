@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d08

import helpers.*
import grid.*

val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	val d = data.map{
		val(a,b) = it.split(" ")
		a to b.toInt()
	}

	var ins = 0
	var acc = 0
	var s = mutableSetOf<Int>()
	while (s.add(ins)){
		val (a,b) = d[ins]
		when(a){
			"jmp" -> ins += b
			"acc" -> {ins ++; acc += b;}
			"nop" -> ins++;
		}

	}
	println(acc)
}

private fun part2(data: Data) {
	val d = data.map{
		val(a,b) = it.split(" ")
		a to b.toInt()
	}
	loop@ for(i in d.indices) {
		val dd = d.toMutableList()
		when (dd[i].first) {
			"jmp" -> dd[i] = "nop" to dd[i].second
			"nop" -> dd[i] = "jmp" to dd[i].second
			else -> continue@loop
		}
		var ins = 0
		var acc = 0
		val s = mutableSetOf<Int>()
		while (s.add(ins)) {
			if(ins == d.size){
				println(acc)
				return
			}

			if(ins > d.size || ins < 0)
				break
			val (a, b) = dd[ins]
			when (a) {
				"jmp" -> ins += b
				"acc" -> {
					ins++; acc += b;
				}
				"nop" -> ins++;
			}

		}
	}

}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(8)
	part1(data)
	part2(data)
}