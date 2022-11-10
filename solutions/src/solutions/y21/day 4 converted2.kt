@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d04c2

/*
import grid.Clock
import helpers.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2021_04)
	var dd = data.splitOnEmpty()
	val items = dd.first().first().getInts()
	var grids = dd.drop(1).ints()
	grids += grids.map{it.transpose()}


	val seen = mutableSetOf<Int>()
	for (i in items) {
		seen.add(i)

		// check bingo
		for (grid in grids) {
			for (row in grid) {
				if (row.all { it in seen }) {
					(grid.flatten().filter { it !in seen }.sum() * i).log()
					return;
				}
			}
		}
	}
}

private fun part2() {
	var data = getLines(2021_04)
	var dd = data.splitOnEmpty()
	val items = dd.first().first().getInts()
	var grids = dd.drop(1).ints()


	val seen = mutableSetOf<Int>()
	val won = mutableSetOf<Any>()
	for (i in items) {
		seen.add(i)

		// check bingo
		for (grid in grids) {
			if (grid in won)
				continue;

			for (list in grid + grid.transpose()) {
				if (list.all { it in seen }) {
					won.add(grid)
					if (won.size == grids.size) {
						(grid.flatten().filter { it !in seen }.sum() * i).log()
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