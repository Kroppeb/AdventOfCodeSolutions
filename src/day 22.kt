@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d4c

/*
import grid.Clock
import helpers.*
import kotlin.math.*
 */

import grid.Clock
import grid.grid
import grid.grids
import helpers.*

val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(1)
	var dd = data.splitOnEmpty()
	val items = dd.first().first().getInts()
	var grids = dd.drop(1).ints().grids()


	val seen = mutableSetOf<Int>()
	for (i in items) {
		seen.add(i)

		// check bingo
		for (grid in grids) {
			for (row in grid.rowsCols()) {
				if (row.all { it in seen }) {
					(grid.allItems().filter { it !in seen }.sum() * i).log()
					return;
				}
			}
		}
	}
}

private fun part2() {
	var data = getLines(1)
	var dd = data.splitOnEmpty()
	val items = dd.first().first().getInts()
	var grids = dd.drop(1).ints().grids()


	val seen = mutableSetOf<Int>()
	val won = mutableSetOf<Any>()
	for (i in items) {
		seen.add(i)

		// check bingo
		for (grid in grids) {
			if (grid in won)
				continue;

			for (row in grid.rowsCols()) {
				if (row.all { it in seen }) {
					won.add(grid)
					if (won.size == grids.size) {
						(grid.allItems().filter { it !in seen }.sum() * i).log()
						return;
					}
				}
			}
		}
	}
}

fun main() {
	println("Day 4: ")
	part1()
	part2()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }