@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d05c

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
		it.mapIndexed { a, b -> if (b == 'B' || b == 'R') 1 shl 9 - a else 0 }.sum()
	}.max().let { println(it) }
}

private fun part2(data: Data) {
	val l = data.map {
		it.map { if (it == 'B' || it == 'R') '1' else '0' }.joinToString("").toInt(2)
	}.toSet()
	println(l.find { it + 2 in l && it + 1 !in l })
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_05)
	part1(data)
	part2(data)
}