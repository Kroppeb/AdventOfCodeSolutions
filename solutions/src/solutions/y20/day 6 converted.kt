@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d06c

import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	println(data.splitOn { it.isBlank() }.sumBy {
		it.map { it.toSet() }.reduce { a, b -> a + b }.size
	})
}

private fun part2(data: Data) {
	println(data.splitOn { it.isBlank() }.sumBy {
		it.map { it.toSet() }.reduce { a, b -> a * b }.size
	})
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_06)
	part1(data)
	part2(data)
}