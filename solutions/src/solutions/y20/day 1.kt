@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d01

import helpers.*
import grid.*

private val xxxxx = Clock(3, 6);


// Data = List<Int>
private fun part1(data: Data) {
	data.forEach {
		data.forEach { a ->
			if (it + a == 2020) {
				println(it * a); return
			}
		}
	}
}

private fun part2(data: Data) {
	val m = data.flatMap { a -> data.map { a + it to a * it } }.toMap()
	val q = data.find { m[2020 - it] != null }!!
	println(q * m[2020 - q]!!) // totaly did not forget the `2020-` here
}

private typealias Data = List<Int>

fun main() {
	val data: Data = getInts(2020_01)
	part1(data)
	part2(data)
}