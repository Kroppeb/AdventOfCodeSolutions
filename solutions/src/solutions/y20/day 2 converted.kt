@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d02c

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*

val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	data.count { (a, b, c) ->
		val (x, y) = a.getPosInts()
		val l = b[0]
		c.count { it == l } in x..y
	}.let { println(it) }
}

private fun part2(data: Data) {
	data.count { (a, b, c) ->
		val (x, y) = a.getPosInts()
		val l = b[0]
		(c[x - 1] == l) xor (c[y - 1] == l)
	}.let { println(it) }
}

private typealias Data = WSV

fun main() {
	val data: Data = getWSV(2020_02)
	part1(data)
	part2(data)
}