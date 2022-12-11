@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d01

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.e
import me.kroppeb.aoc.helpers.getLines

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2015_01).first().e()
    var floor = 0
    data.forEach {
        if (it == '(') floor++
        if (it == ')') floor--
    }
    floor.log()
}

private fun part2() {
    var data = getLines(2015_01).first().e()
    var floor = 0
    data.forEachIndexed() { index, it ->
        if (it == '(') floor++
        if (it == ')') floor--

        if (floor == -1) {
            (index + 1).log()
            return
        }
    }
}


fun main() {
    println("Day 1: ")
    part1()
    part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }