@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d08c1


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


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_08).e().map2 { it - '0' }.grid()

	val seen = msop()

	for (p in data.bounds.eastEdge()) {
		var size = -1
		for (x in data.getBp(p).westsInc()) {
			if (x.v > size) {
				size = x.v
				seen += x.p
			}
		}
	}

	for (p in data.bounds.westEdge()) {
		var size = -1
		for (x in data.getBp(p).eastsInc()) {
			if (x.v > size) {
				size = x.v
				seen += x.p
			}
		}
	}

	for (p in data.bounds.southEdge()) {
		var size = -1
		for (x in data.getBp(p).northsInc()) {
			if (x.v > size) {
				size = x.v
				seen += x.p
			}
		}
	}

	for (p in data.bounds.northEdge()) {
		var size = -1
		for (x in data.getBp(p).southsInc()) {
			if (x.v > size) {
				size = x.v
				seen += x.p
			}
		}
	}

	seen.size log 1
}


private fun part2() {
	var data = getLines(2022_08).e().map2 { it - '0' }.grid()

	data.maxOf { p ->
		p.norths().takeUntilInc { it.v >= p.v }.size *
		p.souths().takeUntilInc { it.v >= p.v }.size *
		p.easts().takeUntilInc { it.v >= p.v }.size *
		p.wests().takeUntilInc { it.v >= p.v }.size
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