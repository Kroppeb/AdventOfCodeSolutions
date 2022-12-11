@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d17

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
	var data = getLines(2017_17).first().int()

	var state = listOf(0)
	var cur = 0

	repeat(2017) { it ->
		cur += data
		cur %= state.size
		state = state.take(cur + 1) + listOf(it + 1) + state.drop(cur + 1)
		cur++
	}

	cur++
	cur %= state.size
	state[cur].log()
}

private fun part2() {
	var data = getLines(2017_17).first().int()

	var afterZero = 0
	var size = 1
	var index = 0
	var next = 1

	while (next < 50_000_000) {
		// (index to size to next).log()
		val skip = (size - index) / (data + 1)

		if (skip != 0) {
			// println(skip)
			next += skip
			index += skip * (data + 1)
			size += skip
			if (index >= size) error("gnnn")
			continue
		}

		index += data
		index %= size

		if (index == 0) {
			afterZero = next
			// next.log()
		}
		next++
		index++
		size++


	}

	afterZero.log()
}


fun main() {
	println("Day 17: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }