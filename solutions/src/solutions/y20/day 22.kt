@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d22

import grid.Clock
import helpers.*
import java.util.ArrayDeque

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val (a, b) = data.splitOn { it.isBlank() }.map{ArrayDeque(it.drop(1).map{it.toInt()})}

	while (a.isNotEmpty() && b.isNotEmpty()){
		val aa = a.poll()
		val bb = b.pop()
		if(aa > bb){
			a.add(aa)
			a.add(bb)
		}else{
			b.add(bb)
			b.add(aa)
		}
		println(a)
		println(b)
		println()
	}

	listOf(a,b).flatMap { it }.reversed().mapIndexed { index: Int, s: Int -> (index + 1) * s}.sum().log()

}


private fun part2(data: Data) {
	val (a, b) = data.splitOn { it.isBlank() }.map{ArrayDeque(it.drop(1).map{it.toInt()})}

	var game = 1

	fun check(ass:List<Int>, bss:List<Int>):Boolean{
		val g = ++game
		println(g)
		val a = ArrayDeque(ass)
		val b = ArrayDeque(bss)

		val states = mutableSetOf<Pair<List<Int>, List<Int>>>()

		while (a.isNotEmpty() && b.isNotEmpty()){
			//println(g)
			//println(a)
			//println(b)
			//println()


			if(!states.add(a.toList() to b.toList())) {
				//println("repeat detected")
				return true
			}

			val aa = a.poll()
			val bb = b.pop()

			val w = if(aa <= a.size && bb <= b.size){
				check(a.toList().take(aa), b.toList().take(bb))
			} else {
				aa > bb
			}
			if(w){
				a.add(aa)
				a.add(bb)
			} else {
				b.add(bb)
				b.add(aa)
			}
			// println(a)
			// println(b)
			// println()
		}
		return b.isEmpty()
	}

	val states = mutableSetOf<Pair<List<Int>, List<Int>>>()
	while (a.isNotEmpty() && b.isNotEmpty()){
		println(1)
		println(a)
		println(b)
		println()

		if(!states.add(a.toList() to b.toList()))
			error("huh");

		val aa = a.poll()
		val bb = b.pop()

		val w = if(aa <= a.size && bb <= b.size){
			check(a.toList().take(aa), b.toList().take(bb))
		} else {
			aa > bb
		}
		if(w){
			a.add(aa)
			a.add(bb)
		} else {
			b.add(bb)
			b.add(aa)
		}
		// println(a)
		// println(b)
		// println()
	}

	listOf(a,b).flatMap { it }.reversed().mapIndexed { index: Int, s: Int -> (index + 1) * s}.sum().log()

}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_22)
	//part1(data)
	part2(data)
}


fun <T> T.log(): T = also { println(this) }