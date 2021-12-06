@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d6

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

    var times = arrayOf(0,0,0,0,0,0,0,0,0)

    data.forEach{
        times[it]++
    }

    for(x in 1..80){
        var newTimes = arrayOf(0,0,0,0,0,0,0,0,0)
        for(i in 1..8){
            newTimes[i - 1] = times[i]
        }
        newTimes[8] = times[0]
        newTimes[6] += times[0]
        times = newTimes
    }

    times.toList().sum().log()
}

private fun part2() {
    var data = getLines(2021_06)[0].ints();

    var times = arrayOf(0L,0,0,0,0,0,0,0,0)

    data.forEach{
        times[it]++
    }

    for(x in 1..256){
        var newTimes = arrayOf(0L,0,0,0,0,0,0,0,0)
        for(i in 1..8){
            newTimes[i - 1] = times[i]
        }
        newTimes[8] = times[0]
        newTimes[6] += times[0]
        times = newTimes
    }

    times.toList().sum().log()
}

fun main() {
    println("Day 6: ")
    part1()
    part2()
}


fun <T> T.log(): T = also { println(this) }