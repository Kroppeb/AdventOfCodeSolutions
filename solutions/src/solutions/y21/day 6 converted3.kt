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
	var data = getLines(2021_06)[0].ints().countEachI()

	repeat(80) {
		data = data.filterKeys { k -> k > 0 }.mapKeys { (k) -> k - 1 }
			.merge(mapOf(8 to (data[0] ?: 0), 6 to (data[0] ?: 0))) { a, b -> a + b }
	}

	data.values.sum().log()
}

private fun part2() {
	var data = getLines(2021_06)[0].ints().countEachI().mapValues { (_,v) -> v.toLong()}

	repeat(256) {
		data = data.filterKeys { k -> k > 0 }.mapKeys { (k) -> k - 1 }
			.merge(mapOf(8 to (data[0] ?: 0), 6 to (data[0] ?: 0))) { a, b -> a + b }
	}

	data.values.sum().log()
}

fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private fun <T> T.log(): T = also { println(this) }