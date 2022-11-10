@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d5c2

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

private fun part1() {
    var lines = getLines(2021_05).ints().map { (a, b, c, d) -> (a toP b) toL (c toP d) }

    var single = msoa()
    var double = msoa()

    for (line in lines) {
        if (line.diff.x == 0 || line.diff.y == 0) {
            for (p in line)
                if (!single.add(p)) {
                    double.add(p)
                }
        }
    }

    double.size.log()
}

private fun part2() {
    var lines = getLines(2021_05).ints().map { (a, b, c, d) -> (a toP b) toL (c toP d) }

    var single = msoa()
    var double = msoa()

    for (line in lines) {
        for (p in line)
            if (!single.add(p)) {
                double.add(p)
            }
    }

    double.size.log()
}

fun main() {
    println("Day 5: ")
    part1()
    part2()
}


fun <T> T.log(): T = also { println(this) }