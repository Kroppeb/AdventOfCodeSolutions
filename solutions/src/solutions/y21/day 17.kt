@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d17

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import kotlin.math.*

val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
    var data = getLines(2021_17)

    var targetXRange = 96..125
    var targetYRange = -144..-98


    var startDy = 0;
    var answer  = 0
    while (startDy < 1000) {
        startDy++
        var currentY = 0
        var currentDy = startDy
        var steps = 0
        var highestY = 0
        while (currentY > -98) {
            currentY += currentDy
            steps++
            currentDy--
            highestY = max(highestY, currentY)
        }

        if(currentY >= -144){
        //     println("max y = $highestY, steps: $steps")
            answer = max(answer, highestY)
        }
    }
    println(answer)
}

fun part2(){
    var data = getLines(2021_17)

    var (minX, maxX) = 96 to 125
    var (minY, maxY) = -144 to -98

    var count = 0;

    var set = msoa()

    var startDy = -200;
    while (startDy < 1000) {
        startDy++
        var currentY = 0
        var currentDy = startDy
        var steps = 0
        var highestY = 0
        while (currentY >= minY) {
            if (currentY <= maxY) {
                // println("potenial dy: " + startDy + "with steps: " + steps)
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
                        // println("$startDx, $startDy")
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
    part2()
}


fun <T> T.log(): T = also { println(this) }