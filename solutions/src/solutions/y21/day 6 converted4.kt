@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d6c2

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	getLines(2021_06)[0].ints().countEachI()
		.applyNTimes(80) { data ->
			data.filterKeys { k -> k > 0 }
				.mapKeys { (k) -> k - 1 }
				.merge(mapOf(8 to (data[0] ?: 0), 6 to (data[0] ?: 0))) { a, b -> a + b }
		}.values.sum() log 1
}

private fun part2() {
	getLines(2021_06)[0].ints().countEachI().mapValues { (_, v) -> v.toLong() }
		.applyNTimes(256) { data ->
			data.filterKeys { k -> k > 0 }
				.mapKeys { (k) -> k - 1 }
				.merge(mapOf(8 to (data[0] ?: 0), 6 to (data[0] ?: 0))) { a, b -> a + b }
		}.values.sum() log 2
}

fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
private infix fun <T> T.log(_ignored: Any?): T = this.log()