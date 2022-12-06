@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d06


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

import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_06).first().e()
		.windowed(4)
		.withIndex()
		.find{(_, p) -> p.areDistinct()}

		.log() // note: add 4 to the number
}

private fun part2() {
	var data = getLines(2022_06).first().e()
		.windowed(14)
		.withIndex()
		.find{(_, p) -> p.areDistinct()}

		.log() // note: add 14 to the number
}


fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
	.also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}