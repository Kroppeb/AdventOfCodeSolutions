@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d12c2


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

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import me.kroppeb.aoc.helpers.*
import itertools.*
import me.kroppeb.aoc.helpers.point.toP
import solutions.solutions.y19.d20c.p
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


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

	bfs(data.first { it.v == 'S' }, { it.v == 'E' }) { p ->
		p.getQuadNeighbours().filter { h(p.v) - h(it.v) >= -1 }
	} log 1


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

	bfsl(data.filter { it.v == 'a' }, { it.v == 'E' }) { p ->
		p.getQuadNeighbours().filter { h(p.v) - h(it.v) >= -1 }
	} log 2
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
	.also { setClipboard(it) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(data: Any?) {
	val s:String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}