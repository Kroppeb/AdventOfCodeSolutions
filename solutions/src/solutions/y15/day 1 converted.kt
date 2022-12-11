@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d01c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.cumSum
import me.kroppeb.aoc.helpers.getLines

private val xxxxx = Clock(6, 3);

/*
	This was made by Leah (Pluie/Pluiedev)
	I just switch the calls to my helper library
*/

private fun part1() {
	getLines(2015_01).first().map { if (it == '(') 1 else -1 }.sum().log()
}

private fun part2() {
	getLines(2015_01).first().map { if (it == '(') 1 else -1 }.cumSum().indexOf(-1).log()
}


fun main() {
	println("Day 1: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }