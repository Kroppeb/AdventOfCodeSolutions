@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d6c

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import kotlin.math.*

val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_06)[0].ints();

    var times = IntArray(9)

    data.forEach{
        times[it]++
    }

    repeat(80){
        var next = IntArray(9)
        times.forEachIndexed{ i, it ->
            next[(i + 8) % 9] += it
        }
        next[6] += next[0]
        times = next
    }

    times.sum().log()
}

private fun part2() {
    var data = getLines(2021_06)[0].ints();

    var times = LongArray(9)

    data.forEach{
        times[it]++
    }

    repeat(256){
        var next = LongArray(9)
        times.forEachIndexed{ i, it ->
            next[(i + 8) % 9] += it
        }
        next[6] += next[0]
        times = next
    }

    times.sum().log()
}

fun main() {
    println("Day 6: ")
    part1()
    part2()
}


fun <T> T.log(): T = also { println(this) }