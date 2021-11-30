@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d05

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*
import kotlin.math.pow

val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	data.map {
		val x = it.substring(0, 7).mapIndexed { a, b -> if (b == 'B') 2.0.pow(6 - a) else 0.0 }.sumBy { it.toInt() }
		val y = it.substring(7).mapIndexed { a, b -> if (b == 'R') 2.0.pow(2 - a) else 0.0 }.sumBy { it.toInt() }

		x * 8 + y
	}.toSet().let { println(it) }
}

private fun part2(data: Data) {
	data.map {
		val x = it.substring(0, 7).mapIndexed { a, b -> if (b == 'B') 2.0.pow(6 - a) else 0.0 }.sumBy { it.toInt() }
		val y = it.substring(7).mapIndexed { a, b -> if (b == 'R') 2.0.pow(2 - a) else 0.0 }.sumBy { it.toInt() }

		x * 8 + y
	}.toSet().let {
		for (i in it) {
			if ((i + 2) in it) {
				if (i + 1 !in it)
					println(i + 1)
			}
		}
	}
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_05)
	part1(data)
	part2(data)
}