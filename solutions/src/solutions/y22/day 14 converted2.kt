@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d14c2


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

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.compareTo
import me.kroppeb.aoc.helpers.sint.*


private val xxxxx = Clock(3, 6);

private fun part1() {
	val walls = getLines(2022_14).points { line -> line.zipWithNext { s, e -> s toL e }.flatten() }.flatten().toSet()

	val sand = msop()

	while (true) {
		sand += generateSequence(500 toP 0) { p -> listOf(p.d, p.dl, p.dr).firstOrNull { it !in walls or sand } }
			.takeWhile { p -> p.y < 10000 }.last().takeIf { it.y < 5000 } ?: break
	}

	sand.size log 1
}


private fun part2() {
	val walls = getLines(2022_14).points { line -> line.zipWithNext { s, e -> s toL e }.flatten() }.flatten().toMutableSet()
	val yy = walls.maxOf { it.y } + 2
	walls += -1000 toP yy toL (2000 toP yy)

	val sand = msop()

	while (500 toP 0 !in sand) {
		sand += generateSequence(500 toP 0) { p -> listOf(p.d, p.dl, p.dr).firstOrNull { it !in walls or sand } }.last()
	}

	sand.size log 2
}


fun main() {
	println("Day 14: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
// .also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}