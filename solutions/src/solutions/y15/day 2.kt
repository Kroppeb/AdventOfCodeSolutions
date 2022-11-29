@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d02

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.e
import helpers.getIntLines
import helpers.getLines
import helpers.transpose

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getIntLines(2015_02)
    data.sumBy { v ->
        val (x, y, z) = v.sorted()

        x * y * 3 + x * z * 2 + y * z * 2
    }.log()
}

private fun part2() {
    var data = getIntLines(2015_02)
    data.sumBy { v ->
        val (x, y, z) = v.sorted()

        x * y * z + x + x + y + y
    }.log()
}


fun main() {
    println("Day 1: ")
    part1()
    part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }