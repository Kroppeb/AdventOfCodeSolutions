@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d5c3

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.Counter
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.isAxisAligned
import me.kroppeb.aoc.helpers.point.toL
import me.kroppeb.aoc.helpers.point.toPI

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	var data = getLines(2021_05).ints().map { (a, b, c, d) -> a toPI b toL (c toPI d) }

	val points = Counter<PointI>()

	for (line in data) {
		if (line.isAxisAligned()) {
			points.addAll(line)
		}
	}

	points.counts.count { (_, v) -> v > 1 }.log()
}

private fun part2() {
	var data = getLines(2021_05).ints().map { (a, b, c, d) -> a toPI b toL (c toPI d) }

	val points = Counter<PointI>()

	for (line in data) {
		points.addAll(line)
	}

	points.counts.count { (_, v) -> v > 1 }.log()
}

fun main() {
	println("Day 5: ")
	part1()
	part2()
}


private fun <T> T.log(): T = also { println(this) }