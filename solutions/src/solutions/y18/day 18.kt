@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d18

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.grid.mapIndexed


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_18).e().grid()

	repeat(10) {
		data = data.mapIndexed { p, c ->  when(c) {
			'.' -> if (p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '|'} >= 3) '|' else '.'
			'|' -> if (p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '#'} >= 3) '#' else '|'
			'#' -> if (p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '#'} >= 1 && p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '|'} >= 1) '#' else '.'
			else -> error("u")
		} }
	}

	(data.allItems().count{it == '|'} * data.allItems().count{it == '#'}).log()

}

private fun part2() {

	var data = getLines(2018_18).e().grid()

	val seen = mutableMapOf(data to 0)

	loop{i ->
		val index = i + 1
		data = data.mapIndexed { p, c ->  when(c) {
			'.' -> if (p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '|'} >= 3) '|' else '.'
			'|' -> if (p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '#'} >= 3) '#' else '|'
			'#' -> if (p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '#'} >= 1 && p.getOctNeighbours().filter{it in data.boundsI}.count{data[it] == '|'} >= 1) '#' else '.'
			else -> error("u")
		} }

		if (data in seen) {
			val cycle = index - seen[data]!!
			val left = (1000000000  - index) % cycle
			val target = seen.entries.filter { it.value == left + seen[data]!!}.first().key

			(target.allItems().count{it == '|'} * target.allItems().count{it == '#'}).log()
			return
		}

		seen[data] = index
	}
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }