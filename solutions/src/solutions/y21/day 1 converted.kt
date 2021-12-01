@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y1.d1c

import grid.Clock
import helpers.Ints
import helpers.getInts
import helpers.isSorted

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
    data.windowed(2).count { (a, b) -> a < b }.log()
    data.zipWithNext().count { (a, b) -> a < b }.log()
    data.zip(data.drop(1)).count { (a, b) -> a < b }.log()
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


fun <T> T.log(): T = also { println(this) }