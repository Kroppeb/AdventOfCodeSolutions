@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d23

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.Point3DI
import me.kroppeb.aoc.helpers.point.toPI

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_22).map { it ->
        val v = it.ints().let { (x1, x2, y1, y2, z1, z2) ->
            (x1 toPI y1 toPI z1) to (x2 toPI y2 toPI z2)
        }

        val s = it.split(" ").first() == "on"

        s to v
    }

    val cells = (-50..50).flatMap { x ->
        (-50..50).flatMap { y ->
            (-50..50).map { z ->
                x toPI y toPI z to false
            }
        }
    }.toMap().toMutableMap()


    for ((s, d) in data) {
        //d.log()
        val (start, stop) = d
        for (cell in cells.keys) {
            if (cell.x in start.x..stop.x && cell.y in start.y..stop.y && cell.z in start.z..stop.z) {
                cells[cell] = s
            }
        }
    }

    println(cells.count { it.value })

}

private fun part2() {
    var data = getLines(2021_22).map { it ->
        val v = it.ints().let { (x1, x2, y1, y2, z1, z2) ->
            (x1 toPI y1 toPI z1) to (x2 toPI y2 toPI z2)
        }

        val s = it.split(" ").first() == "on"

        s to v
    }

    var cells = listOf<Pair<Point3DI, Point3DI>>()


    for ((s, d) in data) {
        var (dStart, dStop) = d

        cells = cells.flatMap { (start, stop) ->
            if (dStart.x in start.x..stop.x && (stop.y>=dStart.y && start.y <= dStop.y) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (dStart.x - 1 toPI stop.y toPI stop.z),
                        (dStart.x toPI start.y toPI start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStop.x in start.x..stop.x && (stop.y>=dStart.y && start.y <= dStop.y) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (dStop.x toPI stop.y toPI stop.z),
                        (dStop.x + 1 toPI start.y toPI start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStart.y in start.y..stop.y && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (stop.x toPI dStart.y - 1 toPI stop.z),
                        (start.x toPI dStart.y toPI start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStop.y in start.y..stop.y && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (stop.x toPI dStop.y toPI stop.z),
                        (start.x toPI dStop.y + 1 toPI start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStart.z in start.z..stop.z && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.y>=dStart.y && start.y <= dStop.y)) {
                listOf(
                        start to (stop.x toPI stop.y toPI dStart.z - 1),
                        (start.x toPI start.y toPI dStart.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStop.z in start.z..stop.z && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.y>=dStart.y && start.y <= dStop.y)) {
                listOf(
                        start to (stop.x toPI stop.y toPI dStop.z),
                        (start.x toPI start.y toPI dStop.z + 1) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.filter { (start, stop) ->
            start.x <= stop.x && start.y <= stop.y && start.z <= stop.z
        }.filter { (start, stop) ->
            !(start.x in dStart.x..dStop.x &&
                    start.y in dStart.y..dStop.y &&
                    start.z in dStart.z..dStop.z &&
                    stop.x in dStart.x..dStop.x &&
                    stop.y in dStart.y..dStop.y &&
                    stop.z in dStart.z..dStop.z)
        }

//        cells.pairWise().map { (l, r) ->
//            // chekc for overlap
//            if (l.first.x <= r.second.x && l.second.x >= r.first.x && l.first.y <= r.second.y && l.second.y >= r.first.y && l.first.z <= r.second.z && l.second.z >= r.first.z)
//                error("ni")
//
//        }

        if (s) {
            cells = cells.plus(d)
        }

//        cells.pairWise().map { (l, r) ->
//            // chekc for overlap
//            if (l.first.x <= r.second.x && l.second.x >= r.first.x && l.first.y <= r.second.y && l.second.y >= r.first.y && l.first.z <= r.second.z && l.second.z >= r.first.z)
//                error("ni")
//
//        }
        //cells.size.log()

    }

    //cells.size.log()
// cells.log()


    cells.map { (start, stop) -> stop - start }.map { (x, y, z) -> (x + 1).toLong() * (y + 1) * (z + 1) }.sum().log()


    cells.pairWise().map { (l, r) ->
        // chekc for overlap
        if (l.first.x <= r.second.x && l.second.x >= r.first.x && l.first.y <= r.second.y && l.second.y >= r.first.y && l.first.z <= r.second.z && l.second.z >= r.first.z)
            error("ni")

    }

}

fun main() {
    println("Day 23: ")
    part2()
}

private fun <T> T.log(): T = also { println(this) }