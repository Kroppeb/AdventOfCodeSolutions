@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d15c

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import collections.counter
import graph.dijkstra
import grid.Clock
import grid.grid
import helpers.*
import itertools.count
import java.util.*
import kotlin.collections.ArrayDeque

val xxxxx = Clock(6, 3);

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


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }