@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d13

/*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2015_13)
		.map { it.split(" ") }
		.map { it.first() to it.last().dropLast(1) to it.map { it.getInt() }.first { it != null }!! * if ("gain" in it) 1 else -1 }
	data.first().log()

	val allNames = data.flatMap { listOf(it.first.first, it.first.second) }.toSet()

	val map = data.toMap()

	allNames
		.permutations()
		.map {
			it.zipWithNext().sumBy { map.getOrDefault(it, 0) } +
					it.zipWithNext().sumBy { map.getOrDefault(it.second to it.first, 0) } +
					map.getOrDefault(it.last() to it.first(), 0) +
					map.getOrDefault(it.first() to it.last(), 0)
		}
		.max().log()


}

private fun part2() {
	var data = getLines(2015_13)
		.map { it.split(" ") }
		.map { it.first() to it.last().dropLast(1) to it.map { it.getInt() }.first { it != null }!! * if ("gain" in it) 1 else -1 }
	data.first().log()

	val allNames = data.flatMap { listOf(it.first.first, it.first.second) }.toSet()

	val map = data.toMap()

	allNames
		.permutations()
		.map {
			it.zipWithNext().sumBy { map.getOrDefault(it, 0) } +
					it.zipWithNext().sumBy { map.getOrDefault(it.second to it.first, 0) }
		}
		.max().log()
}


fun main() {
	println("Day 13: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }