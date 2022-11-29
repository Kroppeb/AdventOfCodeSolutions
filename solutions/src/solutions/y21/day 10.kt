@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d10

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val points2 = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)


private fun part1() {
    var data = getLines(2021_10)

    var coo = 0
    for (datum in data) {
        val deque = ArrayDeque<Char>()

        for (c in datum) {
            when(c) {
                ')' -> {
                    if (deque.first() == '(') {
                        deque.removeFirst()
                    } else {
                        coo += points[')']!!
                        break;
                    }
                }
                ']' -> {
                    if (deque.first() == '[') {
                        deque.removeFirst()
                    } else {
                        coo += points[']']!!
                        break;
                    }
                }
                '}' -> {
                    if (deque.first() == '{') {
                        deque.removeFirst()
                    } else {
                        coo += points['}']!!
                        break;
                    }
                }
                '>' -> {
                    if (deque.first() == '<') {
                        deque.removeFirst()
                    } else {
                        coo += points['>']!!
                        break;
                    }
                }
                else -> {
                    deque.addFirst(c)
                }
            }
        }
    }

    coo.log()
}

private fun part2() {
    var data = getLines(2021_10)

    var coo = 0
    var xx = mutableListOf<Long>()
    for (datum in data) {
        val deque = ArrayDeque<Char>()

        var fail = false
        for (c in datum) {
            when(c) {
                ')' -> {
                    if (deque.first() == '(') {
                        deque.removeFirst()
                    } else {
                        coo += points[')']!!
                        fail = true
                        break;
                    }
                }
                ']' -> {
                    if (deque.first() == '[') {
                        deque.removeFirst()
                    } else {
                        coo += points[']']!!
                        fail = true
                        break;
                    }
                }
                '}' -> {
                    if (deque.first() == '{') {
                        deque.removeFirst()
                    } else {
                        coo += points['}']!!
                        fail = true
                        break;
                    }
                }
                '>' -> {
                    if (deque.first() == '<') {
                        deque.removeFirst()
                    } else {
                        coo += points['>']!!
                        fail = true
                        break;
                    }
                }
                else -> {
                    deque.addFirst(c)
                }
            }
        }
        fail//.log()
        if(fail)
            continue
        deque//.log()
        var pp = 0L
        while(!deque.isEmpty()) {
            var cur = deque.removeFirst()
            pp *= 5
            // NOTE: the following was suggested by copilot, not my code
            if (cur == '(') {
                pp += points2['(']!!
            } else if (cur == '[') {
                pp += points2['[']!!
            } else if (cur == '{') {
                pp += points2['{']!!
            } else if (cur == '<') {
                pp += points2['<']!!
            }
        }


        xx += pp//.log();
    }

    // coo.log()
    xx.sort()
    xx[xx.size / 2].log()
}


fun main() {
    println("Day 10: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }