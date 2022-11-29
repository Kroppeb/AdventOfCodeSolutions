@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d15

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import grid.mutableGrid
import helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	val data = getLines(2015_15).ints().map { it.take(4) }.log().transpose()

	var p = 0L
	for (a in 0..100) {
		for (b in 0..(100 - a)) {
			for (c in 0..(100 - a - b)) {
				val d = 100 - a - b - c
				// listOf(a,b,c,d).log()
				if (d < 0) continue
				val res = data.map { it[0] * a + it[1] * b + it[2] * c + it[3] * d }.map { max(it, 0) }.product()
				// res.log()
				p = max(res, p)
			}
		}
	}

	p.log()

}

private fun part2() {
	val data = getLines(2015_15).ints().log().transpose()

	var p = 0L
	for (a in 0..100) {
		for (b in 0..(100 - a)) {
			for (c in 0..(100 - a - b)) {
				val d = 100 - a - b - c
				// listOf(a,b,c,d).log()
				if (d < 0) continue
				val res =
					data.take(4).map { it[0] * a + it[1] * b + it[2] * c + it[3] * d }.map { max(it, 0) }.product()
				val cal = data.last().zip(listOf(a, b, c, d)) { x, y -> x * y }.sum()
				if (cal == 500)
				// res.log()
					p = max(res, p)
			}
		}
	}

	p.log()

}


fun main() {
	println("Day 15: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }