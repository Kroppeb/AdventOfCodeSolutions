@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d7c

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import kotlin.math.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_07).first().ints()

    data.minMaxIR().minOf { data.sumOf { a -> abs(it - a) } }.log()
    // could also calculate the median
}

private fun part2() {
    var data = getLines(2021_07).first().ints()

    data.minMaxIR().minOf { data.sumOf { a -> abs(it - a) * (abs(it - a) + 1)/2 } }.log()
    data.minMaxIR().minOf { data.sumOf { a -> (1..abs(it - a)).sum()} }.log()
}

fun main() {
    println("Day  7: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }