@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d21

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.getLines
import me.kroppeb.aoc.helpers.*
import kotlin.math.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var (p1, p2) = getLines(2021_21).ints().map { it.last() }

    var die = 0
    var rolls = 0

    var scoreP1 = 0
    var scoreP2 = 0

    while (scoreP1 < 1000 && scoreP2 < 1000) {
        p1 += ++die
        die %= 100
        p1 += ++die
        die %= 100
        p1 += ++die
        die %= 100
        p1--
        p1 %= 10
        p1++

        rolls += 3

        scoreP1 += p1

        if (scoreP1 >= 1000) break

        p2 += ++die
        die %= 100
        p2 += ++die
        die %= 100
        p2 += ++die
        die %= 100
        p2--
        p2 %= 10
        p2++

        rolls += 3

        scoreP2 += p2

        if (scoreP2 >= 1000) break
    }

    println(min(scoreP1/*.log()*/, scoreP2/*.log()*/) * rolls)

}


data class State(val p1: Int, val p2: Int, val scoreP1: Int, val scoreP2: Int)

fun part2() {
    var (op1, op2) = getLines(2021_21).ints().map { it.last() }


    var counter = mutableMapOf(State(op1, op2, 0, 0) to 1L)

    var p1Wins = 0L
    var p2Wins = 0L

    while (!counter.isEmpty()) {
        var iterator = counter.iterator()

        val (state, l) = iterator.next()
        iterator.remove()

        val (p1, p2, scoreP1, scoreP2) = state




        repeat(3) { d1 ->
            repeat(3) { d2 ->
                repeat(3) { d3 ->
                    var px1 = p1
                    px1 += d1 + d2 + d3 + 3
                    px1--
                    px1 %=10
                    px1++

                    var scorePx1 = scoreP1

                    scorePx1 += px1
                    if(scorePx1 >= 21) {
                        p1Wins+= l
                    } else {
                        repeat(3) { dx1 ->
                            repeat(3) { dx2 ->
                                repeat(3) { dx3 ->
                                    var px2 = p2
                                    px2 += dx1 + dx2 + dx3 + 3
                                    px2--
                                    px2 %=10
                                    px2++

                                    var scorePx2 = scoreP2
                                    scorePx2 += px2
                                    if(scorePx2 >= 21) {
                                        p2Wins+= l
                                    } else {
                                        val state1 = State(px1, px2, scorePx1, scorePx2)
                                        if(state1 in counter){
                                            counter[state1] = l + counter[state1]!!
                                        } else {
                                            counter[state1] = l
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    println(max(p1Wins/*.log()*/, p2Wins/*.log()*/))


}


fun main() {
    println("Day 21: ")
    part1()
    part2()
}

private fun <T> T.log(): T = also { println(this) }