@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d11s


/*
import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() = start{
	var data = getLines(2022_11).splitOnEmpty()

	var items = data.map { it[1].sints() }.mut()
	var opp = data.map { it[2].contains('+') to it[2].getSint() }
	var test = data.map { it[3].sint() }
	var steps = data.map { it[4].sint() to it[5].sint() }

	var action = items.map { 0.s }.mut()

	repeat(20) {
		for (m in items.indices) {
			for (item in items[m]) {
				val (oa, ob) = opp[m]
				var new = if (oa) {
					item + ob!!
				} else if (ob == null) {
					item * item
				} else {
					item * ob
				}
				new /= 3
				if (new divBy test[m]) {
					items[steps[m].first] = items[steps[m].first] + listOf(new)
				} else {
					items[steps[m].second] = items[steps[m].second] + listOf(new)
				}
				action[m]++
			}
			items[m] = listOf()
		}
	}
	action.log().max(2).product() log 1


}


private fun part2() = start{
	var data = getLines(2022_11).splitOnEmpty()

	var items = data.map { it[1].sints().map { it } }.mut()
	var opp = data.map { it[2].contains('+') to it[2].getSint() }
	var test = data.map { it[3].sint() }
	var steps = data.map { it[4].sint() to it[5].sint() }

	var action = items.map { 0.s }.toMutableList()

	repeat(10000) {
		for (m in items.indices) {
			for (item in items[m]) {
				val (oa, ob) = opp[m]
				var new = if (oa) {
					item + ob!!
				} else if (ob == null) {
					item * item
				} else {
					item * ob
				}
				new %= 2L * 3L * 5L * 7L * 11L * 13L * 17L * 19L.s
//				new /= 3
				if (new divBy test[m]) {
					items[steps[m].first] = items[steps[m].first] + listOf(new)
				} else {
					items[steps[m].second] = items[steps[m].second] + listOf(new)
				}
				action[m]++
			}
			items[m] = listOf()
		}

		if (it divBy 1000) {
			it.log()
		}
	}
	action.log().max(2).product() log 2


}


fun main() {
	println("Day 11: ")
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