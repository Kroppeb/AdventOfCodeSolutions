@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d12

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.getLines
import me.kroppeb.aoc.helpers.mlot
import me.kroppeb.aoc.helpers.mmoat

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_12)


    var conn = mmoat<MutableList<String>>()

    for ((a, b) in data.map { it.split('-') }) {
        conn.getOrPut(a) { mlot() }.add(b)
        conn.getOrPut(b) { mlot() }.add(a)
    }

    val seen = setOf<String>("start")
    var paths = 0
    var queue = mlot<Pair<String, Set<String>>>()
    queue.add("start" to seen)

    while (queue.isNotEmpty()) {
        val (a, b) = queue.removeAt(0)
        if (a == "end") {
            paths++;
            continue
        }
        for (c in conn[a] ?: emptyList()) {
            if (c in b && !c[0].isUpperCase()) continue;
            var nextSeen = b.toMutableSet() + c
            queue.add(c to nextSeen)
        }
    }

    println(paths)


}


private fun part2() {
    var data = getLines(2021_12)


    var conn = mmoat<MutableList<String>>()

    for ((a, b) in data.map { it.split('-') }) {
        conn.getOrPut(a) { mlot() }.add(b)
        conn.getOrPut(b) { mlot() }.add(a)
    }

    val seen = setOf<String>("start")
    var paths = 0
    var queue = mlot<Pair<String, Set<String>>>()
    queue.add("start" to seen)

    while (queue.isNotEmpty()) {
        val (a, b) = queue.removeAt(0)
        if (a == "end") {
            paths++;
            continue
        }
        for (c in conn[a] ?: emptyList()) {
            val nextSeen = b.toMutableSet()
            if (c in b && !c[0].isUpperCase()) {
                if ("$" !in nextSeen && c != "start" && c != "end")
                    nextSeen += "$"
                else
                    continue;
            }

            nextSeen += c
            queue.add(c to nextSeen)
        }
    }

    println(paths)


}


fun main() {
    println("Day 12: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }