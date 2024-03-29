@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d08c2


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

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
import me.kroppeb.aoc.helpers.grid.grid


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_08).e().map2 { it - '0' }.grid()

	data.count {
		p -> listOf(p.norths(), p.easts(), p.souths(), p.wests()).any { dir -> dir.all { it.v < p.v } } } log 1
}


private fun part2() {
	var data = getLines(2022_08).e().map2 { it - '0' }.grid()

	data.maxOf { p ->
		listOf(p.norths(), p.easts(), p.souths(), p.wests())
			.productOf { dir -> dir.takeUntilInc { it.v >= p.v }.size }
	} log 2


}

fun main() {
	println("Day 8: ")
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