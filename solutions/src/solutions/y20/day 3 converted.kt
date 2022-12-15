@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d03c

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.rem

import me.kroppeb.aoc.helpers.point.toPI

private val xxxxx = Clock(3, 6);

private fun check(data: Data, q: Int, d: Int): Int {
	var s = 0
	for (y in data.boundsI.ys step d) {
		val p = q * y / d toPI y
		if (data[p % data.boundsI] == '#')
			s++
	}
	return s
}

private fun part1(data: Data) {
	println(check(data, 3, 1))
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