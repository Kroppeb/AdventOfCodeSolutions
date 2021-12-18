@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d17

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.getLines
import helpers.msa
import java.lang.Math.max

val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
    var data = getLines(17)

    var (minX, maxX) = 96 to 125
    var (minY, maxY) = -144 to -98

    var count = 0;

    var set = msa()

    var startDy = -500;
    while (startDy < 1000) {
        startDy++
        var currentY = 0
        var currentDy = startDy
        var steps = 0
        var highestY = 0
        while (currentY >= minY) {
            if (currentY <= maxY) {
                println("potenial dy: " + startDy + "with steps: " + steps)
                var startDx = 0;
                while (startDx < maxX + 5) {
                    startDx++
                    var currentX = 0
                    var currentDx = startDx
                    repeat(steps) {
                        currentX += currentDx
                        if (currentDx > 0)
                            currentDx--

                    }

                    if (currentX >= minX && currentX <= maxX) {
                        set.add(startDx to startDy)
                        println("$startDx, $startDy")
                    }
                }
            }
            currentY += currentDy
            steps++
            currentDy--
            highestY = max(highestY, currentY)
        }


    }

    set.size.log()
}


fun main() {
    println("Day 17: ")
    part1()
}


fun <T> T.log(): T = also { println(this) }