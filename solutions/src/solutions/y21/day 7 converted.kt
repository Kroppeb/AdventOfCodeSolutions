@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d7c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_07).first().ints()

    data.minMaxIR().map { data.sumBy { a -> abs(it - a) } }.min().log()
    // could also calculate the median
}

private fun part2() {
    var data = getLines(2021_07).first().ints()

    data.minMaxIR().map { data.sumBy { a -> abs(it - a) * (abs(it - a) + 1)/2 } }.min().log()
}

fun main() {
    println("Day  7: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }