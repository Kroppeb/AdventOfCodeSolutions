@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d5c4

/*
import grid.Clock
import helpers.*
import kotlin.math.*
 */

import collections.Counter
import collections.counter
import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	getLines(2021_05).points()
		.map { (a,b) -> a toL b }
		.filter { it.isAxisAligned() }
		.flatten()
		.countEach()
		.count{ (k,v) -> v > 1 }
		.log()
}

private fun part2() {
	getLines(2021_05).points()
		.map { (a,b) -> a toL b }
		.flatten()
		.countEach()
		.count{ (k,v) -> v > 1 }
		.log()
}

fun main() {
	println("Day 5: ")
	part1()
	part2()
}


private fun <T> T.log(): T = also { println(this) }