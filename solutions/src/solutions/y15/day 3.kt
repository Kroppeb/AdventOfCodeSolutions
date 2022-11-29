@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d03

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2015_03).first().e()

    var pos = Point(0,0)
    val houses = mutableSetOf<Point>()
    houses += pos
    for (d in data){
        when (d) {
            '^' -> pos = pos.up
            '>' -> pos = pos.right
            '<' -> pos = pos.left
            'v' -> pos = pos.down
            else -> error("")
        }
        houses += pos
    }

    houses.size.log()

}

private fun part2() {
    var data = getLines(1).first().e()

    var pos1 = Point(0,0)
    var pos2 = Point(0,0)
    val houses = mutableSetOf<Point>()
    houses += pos1
    for (d in data){
        when (d) {
            '^' -> pos1 = pos1.up
            '>' -> pos1 = pos1.right
            '<' -> pos1 = pos1.left
            'v' -> pos1 = pos1.down
            else -> error("")
        }
        houses += pos1

        val t = pos1
        pos1 = pos2
        pos2 = t
    }

    houses.size.log()

}


fun main() {
    println("Day 3: ")
    part1()
    part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }