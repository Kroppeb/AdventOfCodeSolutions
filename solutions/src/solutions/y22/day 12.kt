@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d12


/*
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

 */


import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toPI


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_12).e().grid()

	fun h(p: Char): Char {
		return when (p) {
			'S' -> 'a'
			'E' -> 'z'
			else -> p
		}
	}

	bfs(data.boundsI.first { data[it] == 'S' }, { data[it] == 'E' }) { p ->
		data.getBp(p).getQuadNeighbours().filter { h(data[p]) - h(it.v) >= -1 }.map { it.p }
	} log 0


}


private fun part2() {
	var data = getLines(2022_12).e().grid()

	fun h(p: Char): Char {
		return when (p) {
			'S' -> 'a'
			'E' -> 'z'
			else -> p
		}
	}

	bfs(-1 toPI -1, { it.x != -1 && data[it] == 'E' }) { p ->
		if (p.x == -1) {
			data.boundsI.filter { data[it] == 'a' }
		} else {
			data.getBp(p).getQuadNeighbours().filter { h(data[p]) - h(it.v) >= -1 }.map { it.p }
		}
	} log 0 // subtract 1 (intentionally off by 1)


}


fun main() {
	println("Day 12: ")
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