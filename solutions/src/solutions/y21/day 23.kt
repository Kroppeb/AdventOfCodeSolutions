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
import me.kroppeb.aoc.helpers.point.Point3D
import me.kroppeb.aoc.helpers.point.toP

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_22).map { it ->
        val v = it.ints().let { (x1, x2, y1, y2, z1, z2) ->
            (x1 toP y1 toP z1) to (x2 toP y2 toP z2)
        }

        val s = it.split(" ").first() == "on"

        s to v
    }

    val cells = (-50..50).flatMap { x ->
        (-50..50).flatMap { y ->
            (-50..50).map { z ->
                x toP y toP z to false
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
            (x1 toP y1 toP z1) to (x2 toP y2 toP z2)
        }

        val s = it.split(" ").first() == "on"

        s to v
    }

    var cells = listOf<Pair<Point3D, Point3D>>()


    for ((s, d) in data) {
        var (dStart, dStop) = d

        cells = cells.flatMap { (start, stop) ->
            if (dStart.x in start.x..stop.x && (stop.y>=dStart.y && start.y <= dStop.y) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (dStart.x - 1 toP stop.y toP stop.z),
                        (dStart.x toP start.y toP start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStop.x in start.x..stop.x && (stop.y>=dStart.y && start.y <= dStop.y) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (dStop.x toP stop.y toP stop.z),
                        (dStop.x + 1 toP start.y toP start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStart.y in start.y..stop.y && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (stop.x toP dStart.y - 1 toP stop.z),
                        (start.x toP dStart.y toP start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStop.y in start.y..stop.y && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.z>=dStart.z && start.z <= dStop.z)) {
                listOf(
                        start to (stop.x toP dStop.y toP stop.z),
                        (start.x toP dStop.y + 1 toP start.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStart.z in start.z..stop.z && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.y>=dStart.y && start.y <= dStop.y)) {
                listOf(
                        start to (stop.x toP stop.y toP dStart.z - 1),
                        (start.x toP start.y toP dStart.z) to stop
                )
            } else {
                listOf(start to stop)
            }
        }.flatMap { (start, stop) ->
            if (dStop.z in start.z..stop.z && (stop.x>=dStart.x && start.x <= dStop.x) && (stop.y>=dStart.y && start.y <= dStop.y)) {
                listOf(
                        start to (stop.x toP stop.y toP dStop.z),
                        (start.x toP start.y toP dStop.z + 1) to stop
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