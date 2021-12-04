@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d4c

/*
import grid.Clock
import helpers.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
    var dd = data.splitOn { it.isEmpty() }
    val items = dd.first().first().getInts()
    dd = dd.subList(1, dd.size);
    var grids = dd.map { it.map { it.getInts() } }


    val seen = mutableSetOf<Int>()
    for (i in items) {
        seen.add(i)

        // check bingo
        for (grid in grids) {
            for (list in grid) {
                if (list.all { it in seen }) {
                    (grid.flatten().filter { it !in seen }.sum() * i).log()
                    return;
                }
            }

            for (list in grid.transpose()) {
                if (list.all { it in seen }) {
                    (grid.flatten().filter { it !in seen }.sum() * i).log()
                    return;
                }
            }
        }
    }
}

private fun part2(data: Data) {
    var dd = data.splitOn { it.isEmpty() }
    val items = dd.first().first().getInts()
    dd = dd.subList(1, dd.size);
    var grids = dd.map { it.map { it.getInts() } }


    val seen = mutableSetOf<Int>()
    val won = mutableSetOf<Any>()
    for (i in items) {
        seen.add(i)

        // check bingo
        for (grid in grids) {
            if (grid in won)
                continue;

            for (list in grid) {
                if (list.all { it in seen }) {
                    won.add(grid)
                    if (won.size == grids.size) {
                        (grid.flatten().filter { it !in seen }.sum() * i).log()
                        return;
                    }
                }
            }

            for (list in grid.transpose()) {
                if (list.all { it in seen }) {
                    won.add(grid)
                    if (won.size == grids.size) {
                        (grid.flatten().filter { it !in seen }.sum() * i).log()
                        return;
                    }
                }
            }
        }
    }
}

private typealias Data = Lines

fun main() {
    println("Day 4: ")
    val data: Data = getLines(2021_04)
    part1(data)
    part2(data)
}


fun <T> T.log(): T = also { println(this) }