@file:Suppress("PackageDirectoryMismatch", "PackageName")

package solutions.y16.d01

import grid.Clock
import helpers.Words
import helpers.getAlphaNums
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

private val xxxxx = Clock(3, 6);

private fun part1(data: Data) = runBlocking {
    var x = 0
    var y = 0
    var dx = 1
    var dy = 0

    for (datum in data) {
        when (datum[0]) {
            'L' -> {
                val p = dx
                dx = dy
                dy = -p
            }
            'R' -> {

                val p = dx
                dx = -dy
                dy = p
            }
        }

        val n = datum.substring(1).toInt()

        x += dx * n
        y += dy * n
    }

    println(abs(x) + abs(y))

}

private fun part2(data: Data) = runBlocking {
    var x = 0
    var y = 0
    var dx = 1
    var dy = 0

    val ss = mutableSetOf<Pair<Int, Int>>()

    for (datum in data) {
        when (datum[0]) {
            'L' -> {
                val p = dx
                dx = dy
                dy = -p
            }
            'R' -> {

                val p = dx
                dx = -dy
                dy = p
            }
        }

        val n = datum.substring(1).toInt()

        for (i in 1..n) {
            x += dx
            y += dy
            if (!ss.add(Pair(x, y))) {

                println(abs(x) + abs(y))
                return@runBlocking;
            }
        }
    }

}

private typealias Data = Words


fun main() {
    val data: Data = getAlphaNums(2016_01)
    part1(data)
    part2(data)
}