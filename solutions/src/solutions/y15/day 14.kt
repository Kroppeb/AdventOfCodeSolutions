@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d14

/*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.Counter
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2015_14).ints()

	data.map{(a,b,c) -> (2503 / (b + c) * a * b) + (2503 % (b + c)).coerceAtMost(b) * a}.max().log()

}

private fun part2() {
	var data = getLines(2015_14).ints()

	var points = Counter<Int>()
	for (l in 1..2503) {
		val i = data.map{(a,b,c) -> (l / (b + c) * a * b) + (l % (b + c)).coerceAtMost(b) * a}.withIndex().allMaxBy { it.value }.map{it.index}
		points.addAll(i)
	}

	points.counts.values.max().log()

}


fun main() {
	println("Day 14: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }