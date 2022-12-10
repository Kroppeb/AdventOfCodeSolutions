@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d09ct


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

import grid.*
import helpers.*
import helpers.attributes.*
import helpers.context.launch
import helpers.contextual.cumSum


private val xxxxx = Clock(6, 3);


private fun part1() = launch {
	getLines(2022_09)
		.rleDecode({ it[0].toPoint() }, { it.int() }) let
			cumSum(0 toP 0) let
			scan(0 toP 0) with { t, h ->
		if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t
	} filter distinct take size log 1
}

private fun part2() = launch {
	getLines(2022_09)
		.rleDecode({ it[0].toPoint() }, { it.int() }) cumSum (0 toP 0) applyNTimes 9 use
			{
				scan(0 toP 0) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
			} filter distinct take size log 2
}


fun main() {
	println("Day 9: ")
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