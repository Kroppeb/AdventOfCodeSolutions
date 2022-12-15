@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d5c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toPI
import kotlin.math.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var q = getLines(2021_05).ints().map { (a, b, c, d) -> (a toPI b) to (c toPI d) }

    var single = msoa()
    var double = msoa()

    for ((start, end) in q) {
        if (start.x == end.x) {
            for (i in minMaxIR(start.y, end.y)) {
                if (!single.add(start.x toPI i))
                    double.add(start.x toPI i)
            }
        } else if (start.y == end.y) {
            for (i in minMaxIR(start.x, end.x)) {
                if (!single.add(i toPI start.y))
                    double.add(i toPI start.y)
            }
        }

    }

    double.size.log()
}

private fun part2() {
    var q = getLines(2021_05).ints().map { (a, b, c, d) -> (a toPI b) to (c toPI d) }

    var single = msoa()
    var double = msoa()

    for ((start, end) in q) {
        if (start.x == end.x) {
            for (i in minMaxIR(start.y, end.y)) {
                if (!single.add(start.x toPI i))
                    double.add(start.x toPI i)
            }
        } else if (start.y == end.y) {
            for (i in minMaxIR(start.x, end.x)) {
                if (!single.add(i toPI start.y))
                    double.add(i toPI start.y)
            }
        } else {
            val dx = end.x - start.x
            val dy = end.y - start.y
            val dd = abs(dx)
            val xx = dx / dd
            val yy = dy / dd
            for (i in 0..dd) {
                val x = start.x + i * xx
                val y = start.y + i * yy
                if (!single.add(x toPI y))
                    double.add(x toPI y)
            }
        }

    }

    double.size.log()
}

fun main() {
    println("Day 5: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }