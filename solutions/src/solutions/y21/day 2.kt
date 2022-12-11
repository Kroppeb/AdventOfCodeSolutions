@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d2

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*

private val xxxxx = Clock(6, 3);

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

    for (datum in data) {
        var(a,b) = datum.split(" ")

        when(a){
            "forward" -> x += b.toInt()
            "up" -> d -= b.toInt()
            "down" -> d += b.toInt()
            else-> throw IllegalArgumentException("Unknown command $a")
        }
    }

    println(d * x)
}

private fun part2(data: Data) {
    var d = 0
    var x = 0
    var y = 0

    for (datum in data) {
        var(a,b) = datum.split(" ")

        when(a){
            "forward" -> {
                x += b.toInt()
                y += b.toInt() * d
            }
            "up" -> d -= b.toInt()
            "down" -> d += b.toInt()
            else-> throw IllegalArgumentException("Unknown command $a")
        }
    }

    println(y * x)
}

private typealias Data = Lines

fun main() {
    val data: Data = getLines(2).log()
    part1(data)
    part2(data)
}


private fun <T> T.log(): T = also { println(this) }