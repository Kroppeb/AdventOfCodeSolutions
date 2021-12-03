@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d3

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

/*
forward 5
down 5
forward 8
up 3
down 8
forward 2
*/

private fun part1(data: Data) {

}

private fun part2(data: Data) {

}

private typealias Data = Lines

fun main() {
    val data: Data = getLines(3).log()
    part1(data)
    part2(data)
}


fun <T> T.log(): T = also { println(this) }