@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d3

import grid.Clock
import helpers.Lines
import helpers.e
import helpers.getLines
import helpers.transpose

val xxxxx = Clock(6, 3);

/*
*/

private fun part1(data: Data) {
    var transpose = data.e().transpose().map { it.map { i -> i - '0' } }
    val gamma = transpose.map { if (it.sum() * 2 > data.size) 1 else 0 }.reversed()
    var epsilon = gamma.map { 1 - it }

    // bin to value
    var g = gamma.mapIndexed { i, v -> if (v == 1) (1 shl i) else 0 }.sum()
    var e = epsilon.mapIndexed { i, v -> if (v == 1) (1 shl i) else 0 }.sum()

    println(g * e)

}

private fun part2(data: Data) {
    var oxygen = 0
    var co2 = 0

    var do2 = data
    var dco2 = data
    var x = 0

    while (x <= data[0].length) {
        var transpose_o2 = do2.e().transpose().map { it.map { i -> i - '0' } }
        var transpose_co2 = dco2.e().transpose().map { it.map { i -> i - '0' } }
        val gamma = transpose_o2.map { if (it.sum() * 2 >= do2.size) 1 else 0 }
        var epsilon = transpose_co2.map { if (it.sum() * 2 < dco2.size) 1 else 0 }

        if(x < data[0].length){
        do2 = do2.filter { (it[x] - '0') == gamma[x] }
        if (dco2.size > 1)
            dco2 = dco2.filter { (it[x] - '0') == epsilon[x] }
        }

        // bin to value
        oxygen = gamma.reversed().mapIndexed { i, v -> if (v == 1) (1 shl i) else 0 }.sum()
        co2 = epsilon.reversed().mapIndexed { i, v -> if (v == 0) (1 shl i) else 0 }.sum()
        x++
    }
    println(oxygen * co2)
}

private typealias Data = Lines

fun main() {
    val data: Data = getLines(2021_03)
    part1(data)
    part2(data)
}


fun <T> T.log(): T = also { println(this) }