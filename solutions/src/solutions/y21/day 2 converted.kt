@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d2c

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
    var d = 0
    var x = 0

    for ((a, b) in data) {
        when (a) {
            "forward" -> x += b.toInt()
            "up" -> d -= b.toInt()
            "down" -> d += b.toInt()
        }
    }

    println(d * x)
}

private fun part2(data: Data) {
    var d = 0
    var x = 0
    var y = 0

    for ((a, b) in data) {
        when (a) {
            "forward" -> {
                x += b.toInt()
                y += b.toInt() * d
            }
            "up" -> d -= b.toInt()
            "down" -> d += b.toInt()
        }
    }

    println(y * x)
}

private typealias Data = SSV

fun main() {
    val data: Data = getSSV(2).log()
    part1(data)
    part2(data)
}


fun <T> T.log(): T = also { println(this) }