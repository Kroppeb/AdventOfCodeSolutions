@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d05

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*

import java.math.BigInteger
import java.security.MessageDigest


val xxxxx = Clock(6, 3);

/*

*/

val vowels = "aeiou".e().toSet()
val bad = setOf("ab", "cd", "pq", "xy")

private fun part1() {
    val data = getLines(2015_05)

    data.count {
        it.count{it in vowels} >= 3 && it.zipWithNext().none{(a,b) -> "" + a + b in bad} && it.zipWithNext().any{(a,b) -> a == b}
    }.log()
}

private fun part2() {
    val data = getLines(2015_05)

    data.count {
        it.zipWithNext().zipWithNext().any{(a,b) -> a.first== b.second} &&
                it.indices.drop(1).any{i ->
                    val a = it[i - 1]
                    val b = it[i]

                    it.drop(i + 1).zipWithNext().any { (x,y) -> x == a && y == b }
                }
    }.log()
}


fun main() {
    println("Day 5: ")
    part1()
    part2()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }