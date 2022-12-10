@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d04ct


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

import grid.*
import helpers.*
import helpers.attributes.*
import helpers.context.launch
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


private val xxxxx = Clock(6, 3);

private fun part1() {
	getLines(2022_04).posInts() count { (a, b, c, d) -> a..b allIn c..d || c..d allIn a..b } log 1
	getLines(2022_04).posInts() map { it splitIn2 { (a, b) -> a..b } } count { (x, y) -> x allIn y || y allIn x } log 1
	getLines(2022_04).posInts() let
			map { it splitIn2 { (a, b) -> a..b } } let
			count { (x, y) -> x allIn y || y allIn x } log 1
	getLines(2022_04).posInts() let
			map { it splitIn 2 } let
			map2 { (a, b) -> a..b } let
			count { (x, y) -> x allIn y || y allIn x } log 1
}

private fun part2() = launch {
	getLines(2022_04).posInts() count { it splitIn2 { (a, b) -> a..b } intersect each iss notEmpty} log 2
	getLines(2022_04).posInts() count { it splitIn2 { (a, b) -> a..b } intersect each isNot empty } log 2
	getLines(2022_04).posInts() count { it splitIn 2 map { (a, b) -> a..b } intersect each iss notEmpty } log 2
	getLines(2022_04).posInts() count { it splitIn 2 map { (a, b) -> a..b } intersect each isNot empty } log 2
}


fun main() {
	println("Day 4: ")
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