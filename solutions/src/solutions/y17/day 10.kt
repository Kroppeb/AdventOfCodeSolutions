@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d10

/*
import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2017_10).first().ints()

	var cur = 0
	var skip = 0

	var state = (0..255).toList()

	for (l in data) {
		val end = (cur + l - 1) % state.size
		if (l > 1) {
			if (end >= cur) {
				state = state.take(cur) + state.drop(cur).take(l).reversed() + state.drop(cur + l)
			} else {
				var inner = state.drop(cur) + state.take(end + 1)
				inner = inner.reversed()
				state = inner.takeLast(end + 1) + state.take(cur).drop(end + 1) + inner.dropLast(end + 1)
			}
		}

		cur += l + skip
		skip += 1
		cur %= state.size

		state.log()
	}

	if (!state.areDistinct()) error("h")
	state.size.log()
	(state[0] * state[1]).log()
}

private fun part2() {
	var data = getLines(2017_10).first().e().map{it.toInt()} + listOf(17, 31, 73, 47, 23)

	var cur = 0
	var skip = 0

	var state = (0..255).toList()

	repeat(64) { _ ->
		for (l in data) {
			val end = (cur + l - 1) % state.size
			if (l > 1) {
				if (end >= cur) {
					state = state.take(cur) + state.drop(cur).take(l).reversed() + state.drop(cur + l)
				} else {
					var inner = state.drop(cur) + state.take(end + 1)
					inner = inner.reversed()
					state = inner.takeLast(end + 1) + state.take(cur).drop(end + 1) + inner.dropLast(end + 1)
				}
			}

			cur += l + skip
			skip += 1
			cur %= state.size

		}
	}

	val dens  = state.chunked(16).map{it.reduce{a,b -> a xor b}}

	if (!state.areDistinct()) error("h")
	state.size.log()

	dens.map{it.toString(16).let{if(it.length < 2) "0" + it else it}}.joinToString(separator = "").log()
}


fun main() {
	println("Day 10: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }