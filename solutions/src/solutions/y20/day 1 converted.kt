@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d01c

import helpers.*


private fun part1(data: Data) {
	val (x, y) = data.pairWise().find { (a, b) -> a + b == 2020 }!!
	println(x * y)
}

private fun part2(data: Data) {
	println(data.areDistinct())
	val (a, b, c) = data.cartesianPower(3).find { it.sum() == 2020 && it.areDistinct() }!!
	println(a * b * c)
}

private typealias Data = List<Int>

fun main() {
	val data: Data = getInts(2020_01)
	part1(data)
	part2(data)
}