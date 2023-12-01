@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


/*

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*

 */



import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import me.kroppeb.aoc.helpers.collections.list.Het3
import me.kroppeb.aoc.helpers.collections.list.toH
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_16)
	val presO = mutableMapOf<String, Sint>()
	val graph = buildGraph<String> {
		for (line in data) {
			val items = line.split(" ")
			val name = items[1]
			presO[name] = line.sint()
			name conAll items.drop(9).map { if (it.endsWith(",")) it.dropLast(1) else it }
		}
	}.roi { it.value == "AA" || presO[it.value]!! > 0 }
	val pres = presO.mapKeys { graph[it.key] }


	val start = graph.nodes.emptyMask() toH graph["AA"] toH 0.s
	val best = mutableMapOf(start to 0.s)
	val queue = (0..30).map { ArrayDeque(mutableListOf(start).also { it.clear() }) }
	queue[0].add(start)
	var long = 0.s

	for (qi in 0..30) {
		while (queue[qi].isNotEmpty()) {
			val (open, curr, time) = queue[qi].removeLast()
			if (time == 30.s) {
				continue
			}
			if (time > long) {
				long = time
				long toH queue[qi].size + 1 toH queue[qi].map { it.a }.toSet().size log 0
//				long toH queue[qi].size + 1 log 0
			}
			val score = best[open toH curr toH time]!!
			if (curr !in open && pres[curr]!! > 0) {
				val next = open + curr toH curr toH time + 1
				val newScore = score + (pres[curr]!! * (30 - time - 1))
				if (best[next] == null) {
					queue[next.c].addFirst(next)
					best[next] = newScore
				} else if (best[next]!! < newScore) {
					best[next] = newScore
				}
			}
			for ((n, s) in curr.neighbours) {
				val nextTime = time + s
				if (nextTime > 30) {
					continue
				}
				val next = open toH n toH nextTime
				if (best[next] == null) {
					queue[nextTime].addFirst(next)
					best[next] = score
				} else {
					best[next] = max(best[next]!!, score)
				}
			}
		}
	}

	best.values.max() log 1
}

private fun part2() {
	var data = getLines(2022_16)
	val presO = mutableMapOf<String, Sint>()
	val graph = buildGraph<String> {
		for (line in data) {
			val items = line.split(" ")
			val name = items[1]
			presO[name] = line.sint()
			name conAll items.drop(9).map { if (it.endsWith(",")) it.dropLast(1) else it }
		}
	}.roi { it.value == "AA" || presO[it.value]!! > 0 }
	val pres = presO.mapKeys { graph[it.key] }


	val start = graph.nodes.emptyMask() toH graph["AA"] toH 0.s toH graph["AA"] toH 0.s
	val best = (0..26).map {mutableMapOf(start to 0.s)}
	var bestI = 0.s
	val queue = (0..26).map { ArrayDeque(mutableListOf(start).also { it.clear() }) }
	queue[0].add(start)
	var long = 0.s

	for (qi in 0..26) {
		while (queue[qi].isNotEmpty()) {
			val state = queue[qi].removeLast()
			val (open, cA, tA, cB, tB) = state
			require(qi.s == tA)
			val score = best[qi][state]!!
			bestI = max(bestI, score)
			if (tA == 26.s) {
				continue
			}
			if (tA > long) {
				long = tA
				long toH queue[qi].size + 1 toH queue[qi].map { it.a }.toSet().size log 0
//				long toH queue[qi].size + 1 log 0
			}
			if (cA !in open && pres[cA]!! > 0) {
				val next = open + cA toH cB toH tB toH cA toH tA + 1
				val newScore = score + (pres[cA]!! * (30 - tA - 1))
				if (best[next.c][next] == null) {
					queue[next.c].addFirst(next)
					best[next.c][next] = newScore
				} else if (best[next.c][next]!! < newScore) {
					best[next.c][next] = newScore
					if (tB == tA) { // might have already been processed
						queue[next.c].addFirst(next)
					}
				}
			}
			for ((n, s) in cA.neighbours) {
				val nextTime = tA + s
				if (nextTime > 26) {
					continue
				}
				val next = open toH cB toH tB toH n toH nextTime
				if (best[next.c][next] == null) {
					queue[next.c].addFirst(next)
					best[next.c][next] = score
				} else {
					best[next.c][next] = max(best[next.c][next]!!, score)
					if (tB == tA) { // might have already been processed
						queue[next.c].addFirst(next)
					}
				}
			}
		}
		best[qi].clear()
	}

	bestI log 2

}


fun main() {
	println("Day 16: ")
//	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(data: Any?) {
	val s: String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}