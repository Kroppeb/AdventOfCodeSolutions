@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d18

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
import kotlin.math.abs

private val xxxxx = Clock(6, 3);

/*

*/



private fun part1() {
    var data = getLines(19).splitOn { it.isEmpty() }.map { it.drop(1) }.ints().map2 { (a, b, c) -> a toP b toP c }

    var diffs = data.mapIndexed { i, l ->
        val m = l.pairWise().map{(a,b) -> b-a}
        i to allDirections().flatMap{applyRotation(it, m)}.toSet()
    }

    var possiblePairs = mutableListOf<Pair<Pair<Int, Int>, Int>>()

    diffs.pairWise().map { (a, b) ->
        val l = b.second.intersect(a.second).size
        if (l >= 66) {
            possiblePairs += (a.first to b.first) to l
        }
    }

    possiblePairs.sortBy { -it.second }
    possiblePairs.log()

    val knownScanners = mutableMapOf<Int, Point3D>(0 to (0 toP 0 toP 0))
    val knownBeacons = data[0].toMutableSet()
    val scannerRotation = mutableMapOf(0 to listOf(1, 2, 3))


    while (knownScanners.size < data.size) {
        knownScanners.log()
        run {
            possiblePairs.filter {
                (it.first.first in knownScanners && it.first.second !in knownScanners) ||
                        (it.first.second in knownScanners && it.first.first !in knownScanners)
            }.forEach {
                var (a, b) = it.first.log()
                val l = it.second

                if (b in knownScanners) {
                    val p = b
                    b = a
                    a = p
                }

                val olds = applyRotation(scannerRotation[a]!!, data[a]).map { it + knownScanners[a]!! }

                val diffs = olds.orderedPairWise().map { (a, b) -> b - a }.toSet()

                for (rotation in allDirections()) {
                    var news = applyRotation(rotation, data[b])

                    var newDiffs = news.orderedPairWise().map { (a, b) -> b - a }.toSet()

//                    if(diffs.intersect(newDiffs).size > 0){
//                        diffs.intersect(newDiffs).size.log()
//                    }

                    // don't know why this is 30
                    if (diffs.intersect(newDiffs).size >= 30) {
                        val offs = olds.cartesianProduct(news).map { (a, b) -> a - b }

                        for (i in offs) {
                            val ss = news.map { it + i }.toSet()
                            if (ss.intersect(olds).size >= 12) {
                                //"".log()
                                //ss.intersect(olds).map{it.log()}
                                knownScanners[b] = i
                                scannerRotation[b] = rotation
                                knownBeacons.addAll(ss)
                                return@run
                            }
                        }
                    }
                }
            }
            error("No solution found: " + knownScanners.size)
        }
    }

    println(knownBeacons.size)
    knownScanners.values.pairWise().map { (a, b) -> b - a }.map{it.manDist()}.max().log()

}

fun applyRotation(a: List<Int>, b: List<Point3D>) = b.map {
    when (a[0]) {
        1 -> it.x
        2 -> it.y
        3 -> it.z
        -1 -> -it.x
        -2 -> -it.y
        -3 -> -it.z
        else -> throw IllegalArgumentException()
    } toP when (a[1]) {
        1 -> it.x
        2 -> it.y
        3 -> it.z
        -1 -> -it.x
        -2 -> -it.y
        -3 -> -it.z
        else -> throw IllegalArgumentException()
    } toP when (a[2]) {
        1 -> it.x
        2 -> it.y
        3 -> it.z
        -1 -> -it.x
        -2 -> -it.y
        -3 -> -it.z
        else -> throw IllegalArgumentException()
    }
}


fun combineDirection(a: List<Int>, b: List<Int>) = a.map {
    val x = b[abs(it) - 1]
    if (it < 0) -x else x
}

fun allDirections() = listOf(
    listOf(1, 2, 3)
).flatMap { listOf(it, it.repeat(2).drop(1).take(3), it.repeat(2).drop(2).take(3)) }
    .flatMap { listOf(it, it.reversed()) }
    .flatMap { listOf(it, listOf(it[0], it[1], -it[2])) }
    .flatMap { listOf(it, listOf(it[0], -it[1], it[2])) }
    .flatMap { listOf(it, listOf(-it[0], it[1], it[2])) }


fun main() {
    println("Day 19: ")
    part1()
}

private fun <T> T.log(): T = also { println(this) }