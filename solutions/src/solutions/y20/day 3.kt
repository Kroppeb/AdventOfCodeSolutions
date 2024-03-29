@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d03

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid

import me.kroppeb.aoc.helpers.point.toPI

private val xxxxx = Clock(3, 6);

private fun part1(data: Data, q: Int = 3, d: Int = 1): Int {
	val grid = data.e().grid()
	var s = 0
	for (y in grid.boundsI.lower.y..grid.boundsI.higher.y step d) {
		if (grid[(q * y / d % (grid.boundsI.higher.x + 1)) toPI y] == '#')
			s++
	}
	println(s)
	return s
}

private fun part2(data: Data) {
	val l = part1(data, 1, 1).toLong() * part1(data, 3, 1) * part1(data, 5, 1) * part1(data, 7, 1) * part1(data, 1, 2)
	println(l)
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_03)
	part1(data)
	part2(data)
}