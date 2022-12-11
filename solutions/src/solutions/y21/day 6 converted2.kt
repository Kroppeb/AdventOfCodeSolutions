@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d6c2

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_06)[0].ints().countEach().xArray(9,0).toIntArray();

    repeat(80){
        var next = IntArray(9)
        data.forEachIndexed{ i, it ->
            next[(i + 8) % 9] += it
        }
        next[6] += next[0]
        data = next
    }

    data.sum().log()
}

private fun part2() {
    var data = getLines(2021_06)[0].ints().countEach().xArray(9,0).toLongArray();
    AtomicInteger(0).getAndAccumulate(5, ::min)
    repeat(256){
        var next = LongArray(9)
        data.forEachIndexed{ i, it ->
            next[(i + 8) % 9] += it
        }
        next[6] += next[0]
        data = next
    }

    data.sum().log()
}

fun main() {
    println("Day 6: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }