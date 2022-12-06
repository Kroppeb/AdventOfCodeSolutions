@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d6c2

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.IntBinaryOperator
import kotlin.math.*
import kotlin.text.Typography.times

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	var data = getLines(2021_06)[0].ints().countEach()

	repeat(80) {
		data = data.filterKeys { k -> k > 0 }.mapKeys { (k) -> k - 1 }
			.merge(mapOf(8 to (data[0] ?: 0), 6 to (data[0] ?: 0))) { a, b -> a + b }
	}

	data.values.sum().log()
}

private fun part2() {
	var data = getLines(2021_06)[0].ints().countEach().mapValues { (_,v) -> v.toLong()}

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