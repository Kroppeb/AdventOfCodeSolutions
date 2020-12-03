@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d03c

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*

val xxxxx = Clock(3, 6);

private fun check(data: Data, q: Int, d: Int): Int {
	var s = 0
	for (y in data.bounds.ys step d) {
		val p = q * y / d toP y
		if (data[p % data.bounds] == '#')
			s++
	}
	return s
}

private fun part1(data:Data) {
	println(check(data, 3,1))
}

private fun part2(data: Data) {
	val l = check(data, 1, 1).toLong() * check(data, 3, 1) * check(data, 5, 1) * check(data, 7, 1) * check(data, 1, 2)
	println(l)
}

private typealias Data = CGrid

fun main() {
	val data: Data = getCGrid(2020_03)
	part1(data)
	part2(data)
}