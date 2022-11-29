@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import collections.counter
import grid.Clock
import helpers.*
import itertools.count
import java.util.*
import kotlin.collections.ArrayDeque

private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
    var data = getLines(2021_15).digits()

    val queue = ArrayDeque<Pair<Point, Long>>()
    queue.addLast(Pair(Point(0, 0), 0))

    val bestPoints = mutableMapOf<Point, Long>()

    var best = Long.MAX_VALUE;


    while(queue.isNotEmpty()) {
        val (p, steps) = queue.removeFirst()
        if(bestPoints.containsKey(p) && bestPoints[p]!! < steps) continue
        if(p.x + 1 == data.size && p.y + 1 == data[0].size) {
            best = min(best, steps)
        }
        for(np in p.getQuadNeighbours()) {
            if(np.x >= 0 && np.y >= 0 && np.x < data.size && np.y < data[0].size) {
                var second = steps + data[np.x][np.y]
                if(np !in bestPoints || bestPoints[np]!! > second) {
                    bestPoints[np] = second
                    queue.addLast(Pair(np, second))
                }
            }
        }
    }

    best.log()
}

private fun part2() {
    var data = getLines(2021_15).digits()

    val queue = ArrayDeque<Pair<Point, Long>>()
    queue.addLast(Pair(Point(0, 0), 0))

    val bestPoints = mutableMapOf<Point, Long>()

    var best = Long.MAX_VALUE;

    var newData = data
    repeat(4){u->
        newData += data.map { it.map{x -> (x + u) % 9 + 1} }
    }

    newData = newData.transpose()
    data = newData

    repeat(4){u->
        newData += data.map { it.map{x -> (x + u) % 9 + 1} }
    }

    data = newData.transpose()


    while(queue.isNotEmpty()) {
        val (p, steps) = queue.removeFirst()
        if(bestPoints.containsKey(p) && bestPoints[p]!! < steps) continue
        if(p.x + 1 == data.size && p.y + 1 == data[0].size) {
            best = min(best, steps)
        }
        for(np in p.getQuadNeighbours()) {
            if(np.x >= 0 && np.y >= 0 && np.x < data.size && np.y < data[0].size) {
                var second = steps + data[np.x][np.y]
                if(np !in bestPoints || bestPoints[np]!! > second) {
                    bestPoints[np] = second
                    queue.addLast(Pair(np, second))
                }
            }
        }
    }

    best.log()
}


fun main() {
    println("Day 15: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }