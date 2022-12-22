@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d21


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
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

 */



import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.sint.Sint
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


private val xxxxx = Clock(6, 3);


private fun part1() {

	var data = getLines(2022_21)

	var queue = ArrayDeque(mutableListOf<List<String>>())
	val values = mutableMapOf<String, Sint>()

	for (line in data) {
		val i = line.getSint()
		if (i != null) {
			values[line.split(":")[0]] = i
			continue
		}

		val (name, r) = line.split(": ")
		val opp = r.split(" ")

		queue.addLast(listOf(name, opp[0], opp[1], opp[2]))
	}

	while (queue.isNotEmpty()) {
		val (name, a, opp, b) = queue.removeFirst()
		if (values.containsKey(a) && values.containsKey(b)) {
			values[name] = when (opp) {
				"+" -> values[a]!! + values[b]!!
				"*" -> values[a]!! * values[b]!!
				"/" -> values[a]!! / values[b]!!
				"-" -> values[a]!! - values[b]!!
				else -> error("Unknown opp $opp")
			}
		} else {
			queue.addLast(listOf(name, opp, a, b))
		}
	}

	values["root"] log 1

}


fun part2() : Boolean {
	var data = getLines(2022_21)

	var queue = ArrayDeque(mutableListOf<List<String>>())
	val values = mutableMapOf<String, Sint>()

	for (line in data) {
		val i = line.getSint()
		if (i != null) {
			values[line.split(":")[0]] = i
			continue
		}

		val (name, r) = line.split(": ")
		val opp = r.split(" ")

		queue.addLast(listOf(name, opp[0], opp[1], opp[2]))
	}

	values["humn"] log 0

	values.remove("humn")


	var l = 0
	while (queue.isNotEmpty()) {
		val (name, a, opp, b) = queue.removeFirst()
		if (values.containsKey(a) && values.containsKey(b)) {
			values[name] = when (opp) {
				"+" -> values[a]!! + values[b]!!
				"*" -> values[a]!! * values[b]!!
				"/" -> values[a]!! / values[b]!!
				"-" -> values[a]!! - values[b]!!
				else -> error("Unknown opp $opp")
			}
			l = 0
		} else {
			queue.addLast(listOf(name, a, opp, b))
			l++
			if (l > queue.size * 2) {
				break;
			}
		}
	}

	val ro = queue.single{it[0] == "root"} log 0
	values[ro[1]] log 0
	values[ro[3]] log 0
	if (ro[1] in values) {
		values[ro[3]] = values[ro[1]]!!
	} else {
		values["lntp"] = values[ro[3]]!! log 0
		ro[1] log 0
	}

	l = 0
	while (queue.isNotEmpty()) {
		val (name, a, opp, b) = queue.removeFirst() //log 0
		if (name == "root") continue

		if (a == "lntp") error(name)
		if (b == "lntp") error(name)

		if (values.containsKey(name) && values.containsKey(a)) {
			values[b] = when (opp) {
				"+" -> values[name]!! - values[a]!!
				"*" -> values[name]!! / values[a]!!
				"/" -> values[a]!! / values[name]!!
				"-" -> values[a]!! - values[name]!!
				else -> error("Unknown opp $opp")
			}
			l = 0
		} else if (values.containsKey(name) && values.containsKey(b)) {
			values[a] = when (opp) {
				"+" -> values[name]!! - values[b]!!
				"*" -> values[name]!! / values[b]!!
				"/" -> values[name]!! * values[b]!!
				"-" -> values[name]!! + values[b]!!
				else -> error("Unknown opp $opp")
			}
			l = 0
		} else if (values.containsKey(a) && values.containsKey(b)) {
			values[name] = when (opp) {
				"+" -> values[a]!! + values[b]!!
				"*" -> values[a]!! * values[b]!!
				"/" -> values[a]!! / values[b]!!
				"-" -> values[a]!! - values[b]!!
				else -> error("Unknown opp $opp")
			}
			l = 0
		} else {
			queue.addLast(listOf(name, a, opp, b))
			l++
			if (l > queue.size * 2) {
				break;
			}
		}
	}


	val use = queue.flatMap { listOf(it[1], it[2]) }.toSet()
	values.intersect(use) log 0
	queue.toList().map2{values[it]?.toString() ?: it} log 0
	use.size log 0

	"?" log -1
	values["humn"] log 2

	return true

}
fun main() {
	println("Day 21: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}