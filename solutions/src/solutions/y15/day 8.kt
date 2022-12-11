@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d08

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	val data = getLines(2015_08).e().map { line ->
		var count = 2

		var i = 1
		while (i + 1 < line.size) {
			if (line[i] == '\\') {
				if (line[i + 1] == '\\') {
					i++
					count++
				} else if (line[i + 1] == '"') {
					i++
					count++
				} else if (line[i + 1] == 'x') {
					i += 3
					count += 3
				}
			}
			i++
		}
		count
	}.sum().log()

}

private fun part2() {
	val data = getLines(2015_08).e().map { line ->
		line.count{it == '\"' || it == '\\'} + 2
	}.sum().log()
}


fun main() {
	println("Day 8: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }