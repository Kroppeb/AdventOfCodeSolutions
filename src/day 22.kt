@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d22

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.e
import helpers.getLines
import helpers.mmaa
import helpers.transpose
import java.util.*
import kotlin.collections.set
import kotlin.math.abs
import kotlin.math.min
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

val xxxxx = Clock(6, 3);

/*

*/

data class State(val homes: List<List<Char?>>, var pos: List<Char?>)

val cost = intArrayOf(1, 10, 100, 1000)

var prevv = mmaa()


fun estimate(state: State): Long {
    var sum = 0L
    for (i in 0 until 4) {
        for (j in 0 until 4) {
            if (state.homes[i][j] == null) {
                sum += cost[i] * (j + 2)
                //println("A: $sum")
            } else if (state.homes[i][j]!! - 'A' != i) {
                val x = state.homes[i][j]!! - 'A'
                val u = abs(x - i) - 1
                sum += cost[i] * (j + 2) + cost[x] * (j + 2 + u * 2)
                //println("B: $sum")
            } else if (state.homes[i].subList(j, 4).any { it != null && it - 'A' != i }) {
                sum += cost[i] * (j + 2) * 2
                //println("C: $sum")
            } else {
                //  println("D: $sum")
            }
        }
    }

    for (i in 0..10) {
        if (state.pos[i] != null) {
            val targetPos = (state.pos[i]!! - 'A') * 2 + 2
            val diff = abs(targetPos - i) - 1
            sum += diff * cost[state.pos[i]!! - 'A']
            //println("E$i: $sum")
        }
    }
    return sum
}

private fun part1() {
    var data = getLines(23).drop(2).take(4).map { it.e().filter { it in listOf('A', 'B', 'C', 'D') } }.transpose()

    var states = mutableMapOf<State, Long>()

    var priorityQueue = PriorityQueue<Pair<State, Pair<Long, Long>>>(compareBy { it.second.first + it.second.second })

    val start = State(data, List(11) { null })
    /*val start = State(
            listOf(
                    listOf('A', 'A', 'A', 'A'),
                    listOf('B', 'B', 'B', 'B'),
                    listOf('C', 'C', 'C', 'C'),
                    listOf(null, 'D', 'D', 'D')
            )            ,
            listOf(null, null, /**/null, null, /**/null, null, /**/null, null, /**/null, null, 'D')
    )*/
    priorityQueue.add(start to (0L to estimate(start)))
    states[start] = 0


    // return

    var best = 44169 + 100000000L


    fun tryAdd(prev: State, state: State, cost: Long) {
        if (states.containsKey(state)) {
            if (states[state]!! > cost) {
                states[state] = cost
                priorityQueue.add(state to (cost to estimate(state)))
                prevv[state] = prev
            }
        } else {
            states[state] = cost
            val pred = estimate(state)
            if (cost + pred > best) return
            priorityQueue.add(state to (cost to pred))
            prevv[state] = prev
        }

        if (states.size > 10_000_000) {
            states.iterator().also { it.next() }.remove()
        }
    }

    var xxx = 0L

    while (priorityQueue.isNotEmpty()) {
        run {
            val (state, d) = priorityQueue.poll()!!
            val (dist, pred) = d
            if (dist + pred > best) return@run
            if (dist > states.getOrDefault(state, Long.MAX_VALUE))
                return@run

            if (xxx++ % 100000 == 0L) {
                println(priorityQueue.size)
                best.log()
                d.log()
                (dist + pred).log()
                state.log()
                states.size.log()
            }

            if (state.homes.withIndex().all { (index, it) -> it.all { q -> q == it.first() } && it.first() != null && (it.first()!! - 'A') == index }) {
                best = min(best, dist)
                return@run
            }

            for (i in 0..10) {
                val p = state.pos[i]
                if (p != null) {
                    val x = p - 'A'
                    if (state.homes[x].any { it != null && it != p })
                        continue
                    var homeEntrance = x * 2 + 2
                    if (homeEntrance < i) {
                        if ((i - 1 downTo homeEntrance).any { j -> state.pos[j] != null })
                            continue
                        val homePlace = state.homes[x].count { it == null }
                        val steps = i - homeEntrance + homePlace
                        val newHome = state.homes[x].toMutableList().apply { this[homePlace - 1] = p }
                        tryAdd(state, State(
                                state.homes.toMutableList().apply { this[x] = newHome },
                                state.pos.toMutableList().apply { this[i] = null }), dist + steps * cost[x])
                        // break, when we can go home, we should go home
                        return@run
                    } else {
                        assert(homeEntrance > i)
                        if (((i + 1) .. homeEntrance).any { j -> state.pos[j] != null })
                            continue
                        val homePlace = state.homes[x].count { it == null }
                        val steps = homeEntrance - i + homePlace
                        val newHome = state.homes[x].toMutableList().apply { this[homePlace - 1] = p }
                        tryAdd(state, State(
                                state.homes.toMutableList().apply { this[x] = newHome },
                                state.pos.toMutableList().apply { this[i] = null }), dist + steps * cost[x])
                    }
                }
            }

            for (i in 0..3) {
                val home = state.homes[i]
                if (home.all { it == null || it - 'A' == i })
                    continue
                val index = home.indexOfFirst { it != null }

                val x = home[index]!! - 'A'

                val homeExit = i * 2 + 2
                for(j in homeExit - 1 downTo 0){
                    if(state.pos[j] != null) break
                    if(j%2 == 0 && j in 2..8) continue
                    val steps = homeExit - j + 1 + index
                    tryAdd(state, State(
                            state.homes.toMutableList().apply { this[i] = home.toMutableList().apply { this[index] = null } },
                            state.pos.toMutableList().apply { this[j] = home[index] }), dist + cost[x] * steps)
                }
                for(j in homeExit + 1 until 11){
                    if(state.pos[j] != null) break
                    if(j%2 == 0 && j in 2..8) continue
                    val steps = j - homeExit  + 1 + index
                    tryAdd(state, State(
                            state.homes.toMutableList().apply { this[i] = home.toMutableList().apply { this[index] = null } },
                            state.pos.toMutableList().apply { this[j] = home[index] }), dist + cost[x] * steps)
                }
            }
        }


    }

    "f:".log()
    best.log()

    fun prin(s: State) {
        if (s in prevv) {
            prin(prevv[s]!! as State)
        }
        println("${states[s]}: $s")
    }
    states.size.log()

    prin(State(List(4) { List(4) { q -> "ABCD"[it] } }, List(11) { null }));
}


fun main() {
    println("Day 23: ")
    measureNanoTime {
        part1()
    }.log()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }