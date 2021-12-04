@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d4

/*
import grid.Clock
import helpers.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

/*

*/

private fun part1(data: Data) {

}

private fun part2(data: Data) {

}

private typealias Data = Lines

fun main() {
    println("Day 5: ")
    val data: Data = getLines(5)
    part1(data)
    part2(data)
}


fun <T> T.log(): T = also { println(this) }