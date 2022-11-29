@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d25

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.e
import helpers.getLines
import helpers.transpose

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_25).e()

    var itteration = 0
    while (true) {
        itteration++;
        var moved = 0

        data = data.map { line ->
            val x = listOf(line.last()) + line + listOf(line.first())

            x.windowed(3) { (a, b, c) ->
                if (b == '.' && a == '>')
                    '>'.also{moved++}
                else if (b == '>' && c == '.')
                    '.'
                else
                    b
            }

        }

        data = data.transpose()

        data = data.map { line ->
            val x = listOf(line.last()) + line + listOf(line.first())

            x.windowed(3) { (a, b, c) ->
                if (b == '.' && a == 'v')
                    'v'.also{moved++}
                else if (b == 'v' && c == '.')
                    '.'
                else
                    b
            }

        }

        data = data.transpose()//.log()
        if(moved == 0) itteration.log().also{return}
    }

}


fun main() {
    println("Day 25: ")
    part1()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }