@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d14

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

val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(14).splitOn { it.isEmpty() }

    var poly = data[0][0].toCharArray().toList()

    var rules = data[1].map{(it[0] to it[1]) to it[6]}.toMap()

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

    println(poly.joinToString(""))
    var counts = mutableMapOf<Char, Long>()

    poly.forEach { c ->
        counts[c] = counts.getOrDefault(c, 0) + 1
    }

    println(counts.map{it.value}.max() - counts.map{it.value}.min())

}


private fun part2() {
    var data = getLines(14).splitOn { it.isEmpty() }

    var polyx = data[0][0].toCharArray().toList()

    var poly = polyx.zipWithNext().counter().counts.map { it.key to it.value.toLong() }.toMap()

    var rules = data[1].map{(it[0] to it[1]) to it[6]}.toMap()

    repeat(40) {
        var newPoly = mutableMapOf<Pair<Char,Char>, Long>()

        for ((pair, count) in poly) {
            if(rules.containsKey(pair)) {
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

    println(counts.map{it.value}.max()/2 - counts.map{it.value}.min()/2)

}


fun main() {
    println("Day 14: ")
    part2()
}


fun <T> T.log(): T = also { println(this) }