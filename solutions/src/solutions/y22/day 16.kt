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
	var data = getLines(202216)
	val pres = mutableMapOf<String, Sint>()
	val tunn = mutableMapOf<String, List<String>>()

	for (line in data) {
		val items = line.split(" ")
		val name = items[1]
		val amount = line.sint()
		val t = items.drop(9).map { if (it.endsWith(",")) it.dropLast(1) else it }
		tunn[name] = t
		pres[name] = amount
	}

	var best = mutableMapOf<Het3<Set<String>, String, Int>, Sint>(setOf<String>() toH "AA" toH 0 to 0.s)
	var queue = mutableListOf<Het3<Set<String>, String, Int>>(setOf<String>() toH "AA" toH 0).let{ArrayDeque(it)}
	var long = 0

	while (queue.isNotEmpty()) {
		val (open, name, time) = queue.removeLast()
		if (time == 30) {
			continue
		}
		if (time > long) {
			long = time
			println(long)
		}
		val score = best[open toH name toH time]!!
		val nextScore = score + open.sumOf { pres[it]!! }
		if (name !in open && pres[name]!! > 0) {
			val next = open.toMutableSet().also{it.add(name)} toH name toH time + 1
			if (best[next] == null || best[next]!! < nextScore) {
				best[next] = nextScore
				queue.addFirst(next)
			}
		}
		for (n in tunn[name]!!) {
			val next = open toH n toH time + 1
			if (best[next] == null || best[next]!! < nextScore) {
				best[next] = nextScore
				queue.addFirst(next)
			}
		}
	}

	best.values.max() log 1
}

private fun part2() {
	var data = getLines(2022_16)
	val pres = mutableMapOf<String, Sint>()
	val tunn = mutableMapOf<String, List<String>>()

	for (line in data) {
		val items = line.split(" ")
		val name = items[1]
		val amount = line.sint()
		val t = items.drop(9).map { if (it.endsWith(",")) it.dropLast(1) else it }
		tunn[name] = t
		pres[name] = amount
	}

	var best = mutableMapOf<Het3<Set<String>, String, Int>, Sint>(setOf<String>() toH "AA" toH 0 to 0.s)
	var queue = mutableListOf<Het3<Set<String>, String, Int>>(setOf<String>() toH "AA" toH 0).let{ArrayDeque(it)}
	var long = 0

	while (queue.isNotEmpty()) {
		val (open, name, time) = queue.removeLast()
		if (time == 26) {
			continue
		}
		if (time > long) {
			long = time
			println(long)
		}
		val score = best[open toH name toH time]!!
		val nextScore = score + open.sumOf { pres[it]!! }
		if (name !in open && pres[name]!! > 0) {
			val next = open.toMutableSet().also{it.add(name)} toH name toH time + 1
			if (best[next] == null || best[next]!! < nextScore) {
				best[next] = nextScore
				queue.addFirst(next)
			}
		}
		for (n in tunn[name]!!) {
			val next = open toH n toH time + 1
			if (best[next] == null || best[next]!! < nextScore) {
				best[next] = nextScore
				queue.addFirst(next)
			}
		}
	}

	val keys = best.keys.filter{(a,b,c) -> c == 26}.groupBy{(a,b,c) -> a}.values.map{l -> l.maxBy{best[it]!!}!!}
	keys.size log 0
	keys.pairWise().filter{(a,b) -> a.a noneIn b.a}.maxOf{(a,b) -> best[a]!! + best[b]!!} log 2

}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
//	.also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}