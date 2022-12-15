@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d5c4

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.isAxisAligned
import me.kroppeb.aoc.helpers.point.toL

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	getLines(2021_05).pointsI()
		.map { (a,b) -> a toL b }
		.filter { it.isAxisAligned() }
		.flatten()
		.countEach()
		.count{ (k,v) -> v > 1 }
		.log()
}

private fun part2() {
	getLines(2021_05).pointsI()
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