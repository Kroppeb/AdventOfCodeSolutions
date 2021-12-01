@file:Suppress("PackageDirectoryMismatch", "PackageName")

package trainings.`2016`.`02`

import grid.Clock
import helpers.Lines
import helpers.getLines
import kotlinx.coroutines.runBlocking
import java.lang.Math.abs

private val xxxxx = Clock(3, 6);

private fun part1(data: Data) = runBlocking {
    var code = ""
    var x = 1
    var y = 1
    for (line in data) {
        for (char in line) {
            val (newX, newY) = when (char) {
                'U' -> x to y - 1
                'D' -> x to y + 1
                'L' -> x - 1 to y
                'R' -> x + 1 to y
                else -> throw IllegalArgumentException("$char")
            }

            if (newX in 0..2 && newY in 0..2) {
                x = newX
                y = newY
            }
        }
        code += y * 3 + x + 1
    }
    println(code)
}

private fun part2(data: Data) = runBlocking {
    var code = ""
    var x = 0
    var y = 0
    for (line in data) {
        for (char in line) {
            val (newX, newY) = when (char) {
                'U' -> x to y - 1
                'D' -> x to y + 1
                'L' -> x - 1 to y
                'R' -> x + 1 to y
                else -> throw IllegalArgumentException("$char")
            }

            if (abs(newX ) + abs(newY) < 3) {
                x = newX
                y = newY
            }
        }
        /*
    1
  2 3 4
5 6 7 8 9
  A B C
    D
         */

        code += when {
            x == 0 && y == -2 -> '1'
            x == -1 && y == -1 -> '2'
            x == 0 && y == -1 -> '3'
            x == 1 && y == -1 -> '4'
            x == -2 && y == 0 -> '5'
            x == -1 && y == 0 -> '6'
            x == 0 && y == 0 -> '7'
            x == 1 && y == 0 -> '8'
            x == 2 && y == 0 -> '9'
            x == -1 && y == 1 -> 'A'
            x == 0 && y == 1 -> 'B'
            x == 1 && y == 1 -> 'C'
            x == 0 && y == 2 -> 'D'
            else -> throw IllegalArgumentException("$x, $y")
        }
    }
    println(code)
}

private typealias Data = Lines


fun main() {
    val data: Data = getLines(2016_02)
    part1(data)
    part2(data)
}