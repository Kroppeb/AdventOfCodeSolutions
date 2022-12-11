@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d24

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2017_24).ints().mapIndexed{id, (a,b) -> a to b to id}

	fun find(slot:Int, rem:MutableSet<Pair<Pair<Int,Int>,Int>>): Int {
		var valid = rem.filter{(p) -> p.first == slot || p.second == slot}

		var max = 0
		for ((p, id) in valid) {
			val (a,b) = p
			if (a == slot) {
				rem.remove(p to id)
				val score = find(b, rem) + a + b
				max = max(max, score)
				rem.add(p to id)
			} else {
				rem.remove(p to id)
				val score = find(a, rem) + a + b
				max = max(max, score)
				rem.add(p to id)
			}
		}

		return max
	}

	find(0, data.toMutableSet()).log()

}

private fun part2() {
	var data = getLines(2017_24).ints().mapIndexed{id, (a,b) -> a to b to id}

	fun find(slot:Int, rem:MutableSet<Pair<Pair<Int,Int>,Int>>): Pair<Int, Int> {
		var valid = rem.filter{(p) -> p.first == slot || p.second == slot}

		var max = 0 to 0
		for ((p, id) in valid) {
			val (a,b) = p
			if (a == slot) {
				rem.remove(p to id)
				var score = find(b, rem)
				score = score.first + 1 to score.second + a + b

				if (score.first > max.first || (score.first == max.first && score.second > max.second)){
					max = score
				}
				rem.add(p to id)
			} else {
				rem.remove(p to id)
				var score = find(a, rem)
				score = score.first + 1 to score.second + a + b
				if (score.first > max.first || (score.first == max.first && score.second > max.second)){
					max = score
				}
				rem.add(p to id)
			}
		}

		return max
	}

	find(0, data.toMutableSet()).log()

}


fun main() {
	println("Day 24: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }