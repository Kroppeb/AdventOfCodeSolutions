@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d15c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.dijkstra
import me.kroppeb.aoc.helpers.Clock

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.point.toP

private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	var data = getLines(2021_15).digits().grid()

	val res = dijkstra(
		0 toP 0,
		{ point -> point == data.bounds.se },
		{ point -> point.getQuadNeighbours().filter{it in data.bounds}.map { it to data[it] } })

	res!!.cost.log()
}

private fun part2() {
	var data = getLines(2021_15).digits()
		.repeatMap(5) { i, it -> it.map { (it + i - 1) % 9 + 1 } }
		.transpose()
		.repeatMap(5) { i, it -> it.map { (it + i - 1) % 9 + 1 } }
		.transpose()
		.grid()

	val res = dijkstra(
		0 toP 0,
		{ point -> point == data.bounds.se },
		{ point -> point.getQuadNeighbours().filter{it in data.bounds}.map { it to data[it] } })

	res!!.cost.log()
}


fun main() {
	println("Day 15: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }