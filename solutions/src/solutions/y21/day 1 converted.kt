@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d1c

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.Ints
import me.kroppeb.aoc.helpers.getInts

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	data.windowed(2).count { (a, b) -> a < b }.log()
	data.zipWithNext().count { (a, b) -> a < b }.log()
	data.zipWithNext { a, b -> a < b }.count { it }.log()
	data.zip(data.drop(1)).count { (a, b) -> a < b }.log()
	data.zip(data.drop(1)) { a, b -> a < b }.count { it }.log()
}

private fun part2(data: Data) {
	data.windowed(3) { it.sum() }.windowed(2).count { (a, b) -> a < b }.log()
	data.zip(data.drop(3)).count { (a, b) -> a < b }.log()
}

private typealias Data = Ints

fun main() {
	val data: Data = getInts(1)
	part1(data)
	part2(data)
}


private fun <T> T.log(): T = also { println(this) }