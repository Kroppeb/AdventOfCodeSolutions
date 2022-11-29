@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d18

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.bfs
import grid.*
import helpers.*
import itertools.*
import solutions.solutions.y19.d20c.p
import java.util.Comparator.comparing
import java.util.stream.Collectors.toSet
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_18).e().grid()

	repeat(10) {
		data = data.mapIndexed { p, c ->  when(c) {
			'.' -> if (p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '|'} >= 3) '|' else '.'
			'|' -> if (p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '#'} >= 3) '#' else '|'
			'#' -> if (p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '#'} >= 1 && p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '|'} >= 1) '#' else '.'
			else -> error("u")
		} }.grid()
	}

	(data.allItems().count{it == '|'} * data.allItems().count{it == '#'}).log()

}

private fun part2() {

	var data = getLines(2018_18).e().grid()

	val seen = mutableMapOf<List<List<Char>>, Int>(data.items to 0)

	loop{i ->
		val index = i + 1
		val l = data.mapIndexed { p, c ->  when(c) {
			'.' -> if (p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '|'} >= 3) '|' else '.'
			'|' -> if (p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '#'} >= 3) '#' else '|'
			'#' -> if (p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '#'} >= 1 && p.getOctNeighbours().filter{it in data.bounds}.count{data[it] == '|'} >= 1) '#' else '.'
			else -> error("u")
		} }

		data = l.grid()

		if (l in seen) {
			val cycle = index - seen[l]!!
			val left = (1000000000  - index) % cycle
			val target = seen.entries.filter { it.value == left + seen[l]!!}.first().key

			(target.flatten().count{it == '|'} * target.flatten().count{it == '#'}).log()
			return
		}

		seen[l] = index
	}
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }