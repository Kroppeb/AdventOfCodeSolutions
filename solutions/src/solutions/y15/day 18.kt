@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d18

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import grid.grid
import grid.mapIndexed
import grid.mutableGrid
import helpers.*
import itertools.*
import kotlinx.coroutines.NonCancellable.children
import solutions.solutions.y19.d20c.p
import solutions.y15.d16.target
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	var data = getLines(2015_18).e().map2 { it == '#' }.grid()

	repeat(100) {
		data = data.mapIndexed { p, x ->
			if (x) {
				val n = p.getOctNeighbours().count { it in data.bounds && data[it] }
				n == 2 || n == 3
			} else {
				p.getOctNeighbours().count { it in data.bounds && data[it] } == 3
			}
		}.grid()
	}

	data.allItems().count{ it }.log()

}

private fun part2() {

	var data = getLines(2015_18).e().map2 { it == '#' }.mutableGrid()


	data[data.bounds.lowerLeft] = true
	data[data.bounds.lowerRight] = true
	data[data.bounds.upperLeft] = true
	data[data.bounds.upperRight] = true

	repeat(100) {
		data = data.mapIndexed { p, x ->
			if (x) {
				val n = p.getOctNeighbours().count { it in data.bounds && data[it] }
				n == 2 || n == 3
			} else {
				p.getOctNeighbours().count { it in data.bounds && data[it] } == 3
			}
		}.mutableGrid()

		data[data.bounds.lowerLeft] = true
		data[data.bounds.lowerRight] = true
		data[data.bounds.upperLeft] = true
		data[data.bounds.upperRight] = true
	}

	data.allItems().count{ it }.log()

}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }