@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d13

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2017_13).ints()

	var sev = 0
	for ((a,b) in data) {
		val l = (2 * (b - 1))

		if(a % l == 0) {
			sev += a * b
		}
	}

	sev.log()

}

private fun part2() {

	var data = getLines(2017_13).ints()

	var d = 0
	o@ while(true) {
		for ((a, b) in data) {
			val l = (2 * (b - 1))

			if ((a + d) % l == 0) {
				d++
				continue@o
			}
		}

		d.log()
		return

	}
}


fun main() {
	println("Day 13: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }