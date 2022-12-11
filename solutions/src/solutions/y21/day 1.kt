@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d1

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.Ints
import me.kroppeb.aoc.helpers.getInts

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
    var u = 0;
    data.drop(1).forEachIndexed { i, c ->
        if (data[i] < c) {
            u++
        }
    }
    u.log()
}

private fun part2(data: Data) {
    var u = 0;
    var q = data.drop(2).mapIndexed() { i, c ->
        data[i] + data[i+1] + data[i+2]
    }

    q .drop(1).forEachIndexed { i, c ->
        if (q [i] < c) {
            u++
        }
    }

    u.log()
}

private typealias Data = Ints

fun main() {
    val data: Data = getInts(1)
    part1(data)
    part2(data)
}


private fun <T> T.log(): T = also { println(this) }