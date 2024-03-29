@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d14

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.counter
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.getLines
import me.kroppeb.aoc.helpers.max
import me.kroppeb.aoc.helpers.min
import me.kroppeb.aoc.helpers.splitOn
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.drop
import kotlin.collections.forEach
import kotlin.collections.iterator
import kotlin.collections.map
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.set
import kotlin.collections.toList
import kotlin.collections.toMap
import kotlin.collections.zipWithNext

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_14).splitOn { it.isEmpty() }

    var poly = data[0][0].toCharArray().toList()

    var rules = data[1].map { (it[0] to it[1]) to it[6] }.toMap()

    repeat(10) {
        var newPoly = mutableListOf<Char>()

        var last = poly[0]
        newPoly.add(last)

        poly.drop(1).forEach { c ->
            if (rules.containsKey(last to c)) {
                newPoly.add(rules[last to c]!!)
            }
            newPoly.add(c)
            last = c
        }

        poly = newPoly
    }

    // println(poly.joinToString(""))
    var counts = poly.counter().counts

    println(counts.map { it.value }.max() - counts.map { it.value }.min())

}


private fun part2() {
    var data = getLines(2021_14).splitOn { it.isEmpty() }

    var polyx = data[0][0].toCharArray().toList()

    var poly = polyx.zipWithNext().counter().counts.map { it.key to it.value.toLong() }.toMap()

    var rules = data[1].map { (it[0] to it[1]) to it[6] }.toMap()

    repeat(40) {
        var newPoly = mutableMapOf<Pair<Char, Char>, Long>()

        for ((pair, count) in poly) {
            if (rules.containsKey(pair)) {
                newPoly[pair.first to rules[pair]!!] = newPoly.getOrDefault(pair.first to rules[pair]!!, 0) + count
                newPoly[rules[pair]!! to pair.second] = newPoly.getOrDefault(rules[pair]!! to pair.second, 0) + count
            } else {
                newPoly[pair] = newPoly.getOrDefault(pair, 0) + count
            }
        }

        poly = newPoly
    }

    var counts = mutableMapOf<Char, Long>()

    poly.forEach { (pair, count) ->
        counts[pair.first] = counts.getOrDefault(pair.first, 0) + count
        counts[pair.second] = counts.getOrDefault(pair.second, 0) + count
    }

    counts[polyx[0]] = counts.getOrDefault(polyx[0], 0) + 1
    counts[polyx[polyx.size - 1]] = counts.getOrDefault(polyx[polyx.size - 1], 0) + 1

    println(counts.map { it.value }.max() / 2 - counts.map { it.value }.min() / 2)

}


fun main() {
    println("Day 14: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }