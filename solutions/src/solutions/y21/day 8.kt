@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d8

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
    var data = getLines(2021_08).map{it.split("|")[1].words()}

    data.flatten().count{it.length in arrayOf(2,4,3,7)}.log()
}

private fun part2() {
    var data = getLines(2021_08).map{it.split("|")[1].words()}
    var dd = getLines(2021_08).map{it.split("|").words()}

    var c = 0

    for ((l, u) in dd) {
        var one = l.find{it.length == 2}!!.toCharArray().toTypedArray().toSet()
        var four = l.find{it.length == 4}!!.toCharArray().toTypedArray().toSet()
        var seven = l.find{it.length == 3}!!.toCharArray().toTypedArray().toSet()
        var eight = l.find{it.length == 7}!!.toCharArray().toTypedArray().toSet()

        var ttf = l.filter{it.length == 5}.map{it.toCharArray().toSet()}
        var snz = l.filter { it.length == 6 }.map{it.toCharArray().toSet()}

        var top = seven.find{it !in one}!!
        var six = snz.find { it.toSet().intersect(one).size == 1 }!!
        var bottomRight = one.find{it in six}!!
        var topRight = one.find{it !in six}!!
        var two = ttf.find{bottomRight !in it}!!
        var five = ttf.find{topRight !in it}!!
        var zn = snz.filter { it.toSet().intersect(one).size == 2 }
        var nine = zn.find{it.intersect(five).size == five.size}!!
        var zero = zn.find{it != nine}!!
        var bottom = nine.find{it !in four && it != top}!!
        var bottomLeft = eight.find{it !in nine}!!
        var middle = ttf[0].toSet().intersect(ttf[1]).intersect(ttf[2]).find{it != top && it != bottom}!!
        var three = ttf.find{it != two && it != five}!!

        var numbers = listOf(zero, one, two, three, four, five, six, seven, eight, nine)

        var ii = u.map {
            numbers.filterIndexed { i, p ->
                if (p == it.toSet()) return@map i.toString()
                false
            }
            error("")
        }.joinToString(separator = "").toInt()

        c += ii
    }



    c.log()
}


fun main() {
    println("Day  8: ")
    part1()
    part2()
}


fun <T> T.log(): T = also { println(this) }