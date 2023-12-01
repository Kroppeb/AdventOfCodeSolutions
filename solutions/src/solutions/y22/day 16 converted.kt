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
	val pres = mutableMapOf<GNode<String>, Sint>()
	val graph = buildGraph<String> {
		for (line in data) {
			val items = line.split(" ")
			val name = node(items[1])
			pres[name] = line.sint()
			name conAll items.drop(9).map { if (it.endsWith(",")) it.dropLast(1) else it }
		}
	}

	val start = graph.nodes.emptyMask() toH graph["AA"] toH 0.s
	val best = mutableMapOf(start to 0.s)
	val queue = ArrayDeque(mutableListOf(start))
	var long = 0.s

	while (queue.isNotEmpty()) {
		val (open, curr, time) = queue.removeLast()
		if (time == 30.s) {
			continue
		}
		if (time > long) {
			long = time
			long toH queue.size + 1 toH queue.map{it.a}.toSet().size log 0
		}
		val score = best[open toH curr toH time]!!
		if (curr !in open && pres[curr]!! > 0) {
			val next = open + curr toH curr toH time + 1
			val newScore = score + (pres[curr]!! * (30 - time - 1))
			if (best[next] == null) {
				queue.addFirst(next)
				best[next] = newScore
			} else if (best[next]!! < newScore) {
				best[next] = newScore
			}
		}
		for (n in curr.ns) {
			val next = open toH n toH time + 1
			if (best[next] == null) {
				queue.addFirst(next)
				best[next] = score
			} else {
				best[next] = max(best[next]!!, score)
			}
		}
	}

	best.values.max() log 1
}

private fun part2() {
	var data = getLines(2022_16)
	val pres = mutableMapOf<GNode<String>, Sint>()
	val graph = buildGraph<String> {
		for (line in data) {
			val items = line.split(" ")
			val name = node(items[1])
			pres[name] = line.sint()
			name conAll items.drop(9).map { if (it.endsWith(",")) it.dropLast(1) else it }
		}
	}

	val start = graph.nodes.emptyMask() toH (graph["AA"] toU graph["AA"]) toH 0.s
	var best = mutableMapOf(start to 0.s)
	var best2 = mutableMapOf(start to 0.s)
	val queue = ArrayDeque(mutableListOf(start))
	var long = 0.s

	while (queue.isNotEmpty()) {
		val (open, curr, time) = queue.removeLast()
		val (cA, cB) = curr
		if (time == 26.s) {
			continue
		}
		if (time > long) {
			long = time
			long to queue.size + 1 log 0
			val temp = best2
			best2 = best
			best = temp
			best.clear()
		}
		val score = best2[open toH curr toH time]!!
		if (cA !in open && pres[cA]!! > 0) {
			if (cB !in open && pres[cB]!! > 0 && cA != cB) {
				val next = open + cA + cB toH curr toH time + 1
				val newScore = score + (pres[cA]!! * (30 - time - 1)) + (pres[cB]!! * (30 - time - 1))
				if (best[next] == null) {
					queue.addFirst(next)
					best[next] = newScore
				} else if (best[next]!! < newScore) {
					best[next] = newScore
				}
			}
			for (nB in cB.ns) {
				val next = open + cA toH (cA toU nB) toH time + 1
				val newScore = score + (pres[cA]!! * (30 - time - 1))
				if (best[next] == null) {
					queue.addFirst(next)
					best[next] = newScore
				} else if (best[next]!! < newScore) {
					best[next] = newScore
				}
			}
		}
		for (nA in cA.ns) {
			if (cB !in open && pres[cB]!! > 0) {
				val next = open + cB toH (nA toU cB) toH time + 1
				val newScore = score + (pres[cB]!! * (30 - time - 1))
				if (best[next] == null) {
					queue.addFirst(next)
					best[next] = newScore
				} else if (best[next]!! < newScore) {
					best[next] = newScore
				}
			}
			for (nB in cB.ns) {
				val next = open toH (nA toU nB) toH time + 1
				val newScore = score
				if (best[next] == null) {
					queue.addFirst(next)
					best[next] = newScore
				} else if (best[next]!! < newScore) {
					best[next] = newScore
				}
			}
		}
	}

	best.values.max() log 1

}


fun main() {
	println("Day 16: ")
	part1()
//	part2()
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