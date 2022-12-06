@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d04c4

/*
import grid.Clock
import helpers.*
import kotlin.math.*
 */

import grid.Clock
import grid.grid
import grid.grids
import helpers.*

private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2021_04).splitOnEmpty()
	val items = data.first().single().getInts()
	var grids = data.drop(1).ints().grids()


	val seen = msoi()
	for (i in items) {
		seen.add(i)

		// check bingo
		for (grid in grids) {
			if (grid.rowsCols().any { it.allIn(seen) }) {
				grid.allItems().notIn(seen).sum() * i log 1
				return;
			}
		}
	}
}

private fun part2() {
	var data = getLines(2021_04).splitOnEmpty()
	val items = data.first().first().getInts()
	var grids = data.drop(1).ints().grids()


	val seen = msoi()
	val won = msoa()
	for (i in items) {
		seen.add(i)

		// check bingo
		for (grid in grids.notIn(won)) {
			if (grid.rowsCols().any { it.allIn(seen) }) {
				won.add(grid)
				if (won.size == grids.size) {
					grid.allItems().notIn(seen).sum() * i log 2
					return;
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


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
private infix fun <T> T.log(_ignored: Any?): T = this.log()