@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d20

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

val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(20)

    var x = data[0].map{if(it == '#') 1 else 0}

    var image = data.drop(2).e().map2{if(it == '#') 1 else 0}

    var xx = image.biIndexed().filter{it.second > 0}.map{it.first}.toSet()

    repeat(25){
        it.log()
        var nextLight = mutableMapOf<Pair<Int,Int>, Int>()

        for (pair in xx.flatMap { it.getOctNeighbours() }.flatMap { it.getOctNeighbours() }.toSet()) {
            var count = 0
            for (i in -1..1) {
                for (j in -1..1) {
                    val ne = (pair.first + i) to (pair.second + j)
                    val p = if(ne in xx) 1 else 0
                    count += p * (1 shl ((1 - i) * 3 + (1 - j)))
                }
            }
            nextLight[pair] = x[count]
        }

        var nextDark = mutableMapOf<Pair<Int,Int>, Int>()

        for (pair in xx.flatMap { it.getOctNeighbours() }.flatMap { it.getOctNeighbours() }.toSet().flatMap { it.getOctNeighbours() }.toSet().flatMap { it.getOctNeighbours() }.toSet()) {
            var count = 0
            for (i in -1..1) {
                for (j in -1..1) {
                    val ne = (pair.first + i) to (pair.second + j)
                    val p = nextLight[ne] ?: 1
                    count += p * (1 shl ((1 - i) * 3 + (1 - j)))
                }
            }
            nextDark[pair] = x[count]
        }

        xx = nextDark.filter{it.value == 1}.keys.toSet()
    }

    xx.size.log()
}


fun main() {
    println("Day 20: ")
    part1()
}

fun <T> T.log(): T = also { println(this) }