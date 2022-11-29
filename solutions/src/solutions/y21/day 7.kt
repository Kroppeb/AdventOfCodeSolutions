@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d7

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
    var data = getLines(7).first().ints()

    var x = data.minMaxIR().minBy { data.sumBy { a -> abs(it - a) } }

    println(data.sumBy { abs(it - x)  })
}

private fun part2() {
    var data = getLines(7).first().ints()

    var x = data.minMaxIR().minBy { data.sumBy { a -> abs(it - a) * (abs(it - a) + 1)/2 } }

    println(data.map{ abs(it - x) * (abs(it - x) + 1)/2}.sum())
}

fun main() {
    println("Day  7: ")
    part1()
}


private fun <T> T.log(): T = also { println(this) }