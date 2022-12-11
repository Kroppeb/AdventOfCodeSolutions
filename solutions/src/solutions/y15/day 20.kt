@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d20


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
	var data = getLines(2015_20).first().int()

	var m = 0
	var max = 0
	while (true) {
		m++
		val x = m.allDivisors().sum() * 10
		// println("$m: $x")
		if (x >= data) {
			m.log()
			return
		} else if (x > 2 * max) {
			max = x
			println(":" + x)
		} else if (m > 10) {
//			return
		}
	}
}

private fun part2() {
	var data = getLines(2015_20).first().int()

	var m = 0
	var max = 0
	while (true) {
		m++
		var sum = 0
		for (i in 1..50) {
			if (m % i == 0) {
				sum += 11 * m / i
			}
		}

		if (sum >= data) {
			m.log()
			return
		} else if (sum > 2 * max) {
			max = sum
			println(":" + sum)
		}
	}
}

fun main() {
	println("Day 20: ")
	// part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }