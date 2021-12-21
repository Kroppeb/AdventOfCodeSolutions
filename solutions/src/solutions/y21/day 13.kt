@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d13

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var (paper, folds) = getLines(2021_13).splitOn { it.isEmpty() }

    val parsed = folds.map {
        (if (it.contains("x=")) 'x' else 'y') to it.getInt()!!
    }

    val papp = paper.ints()

    val maxY = papp.maxBy { it[0] }[0] + 1
    val maxX = papp.maxBy { it[1] }[1] + 1

    var grid = List(maxX) { x ->
        List(maxY) { y -> if (listOf(y, x) in papp) '#' else '.' }
    }


    var a = grid.take(parsed.first().second)
    var b = grid.drop(parsed.first().second + 1).reversed()

    if (a.size > b.size) {
        b = b + List(a.size - b.size) { List(a.first().size) { '.' } }
    } else if (a.size < b.size) {
        a = List(b.size - a.size) { List(a.first().size) { '.' } } + a
    }

    // grid.map{it.log()}

    grid = a.zip(b) { a, b ->
        a.zip(b) { u, v ->
            if (u == v) u else '#'
        }
    }


    // "".log()

    // grid.map { it.log() }

    grid.flatten().count { it == '#' }.log()
}


private fun part2() {
    var (paper, folds) = getLines(2021_13).splitOn { it.isEmpty() }

    val parsed = folds.map {
        (if (it.contains("x=")) 'x' else 'y') to it.getInt()!!
    }

    val papp = paper.ints()

    val maxY = papp.maxBy { it[0] }[0] + 1
    val maxX = papp.maxBy { it[1] }[1] + 1

    var grid = List(maxX) { x ->
        List(maxY) { y -> if (listOf(y, x) in papp) '#' else '.' }
    }


    for ((i, j) in parsed) {
        if (i == 'x')
            grid = grid.transpose()

        var a = grid.take(j)
        var b = grid.drop(j + 1).reversed()

        if (a.size > b.size) {
            b = b + List(a.size - b.size) { List(a.first().size) { '.' } }
        } else if (a.size < b.size) {
            a = List(b.size - a.size) { List(a.first().size) { '.' } } + a
        }

        // grid.map{it.log()}

        grid = a.zip(b) { a, b ->
            a.zip(b) { u, v ->
                if (u == v) u else '#'
            }
        }

        if (i == 'x')
            grid = grid.transpose()
    }

    "".log()

    grid.map { it.log() }

    // grid.flatten().count { it == '#' }.log()

    // added afterwards
    "".log()

    grid.map { it.map { if (it == '#') "##" else "  " }.joinToString(separator = "").log() }
}


fun main() {
    println("Day 13: ")
    part1()
    part2()
}


fun <T> T.log(): T = also { println(this) }