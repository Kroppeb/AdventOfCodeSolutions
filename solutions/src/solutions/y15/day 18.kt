@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d18

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.grid.mapIndexed
import me.kroppeb.aoc.helpers.grid.mutable
import me.kroppeb.aoc.helpers.grid.mutableGrid


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	var data = getLines(2015_18).e().map2 { it == '#' }.grid()

	repeat(100) {
		data = data.mapIndexed { p, x ->
			if (x) {
				val n = p.getOctNeighbours().count { it in data.boundsI && data[it] }
				n == 2 || n == 3
			} else {
				p.getOctNeighbours().count { it in data.boundsI && data[it] } == 3
			}
		}
	}

	data.allItems().count{ it }.log()

}

private fun part2() {

	var data = getLines(2015_18).e().map2 { it == '#' }.mutableGrid()


	data[data.boundsI.lowerLeft] = true
	data[data.boundsI.lowerRight] = true
	data[data.boundsI.upperLeft] = true
	data[data.boundsI.upperRight] = true

	repeat(100) {
		data = data.mapIndexed { p, x ->
			if (x) {
				val n = p.getOctNeighbours().count { it in data.boundsI && data[it] }
				n == 2 || n == 3
			} else {
				p.getOctNeighbours().count { it in data.boundsI && data[it] } == 3
			}
		}.mutable()

		data[data.boundsI.lowerLeft] = true
		data[data.boundsI.lowerRight] = true
		data[data.boundsI.upperLeft] = true
		data[data.boundsI.upperRight] = true
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