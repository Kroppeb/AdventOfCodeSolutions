@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d10c


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

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	getLines(2022_10).map { it.split(" ") }
		.scan(listOf(1)) { xs, line ->
			val x = xs.last()
			when (line[0]) {
				"noop" -> listOf(x)
				"addx" -> listOf(x, x + line[1].int())
				else -> error(line)
			}
		}
		.flatten()
		.withIndex().drop(19).chunked(40) { it[0] }
		.sumOf { (i, l) -> (i + 1) * l } log 1
}

private fun part2() {
	getLines(2022_10).map { it.split(" ") }
		.scan(listOf(1)) { xs, line ->
			val x = xs.last()
			when (line[0]) {
				"noop" -> listOf(x)
				"addx" -> listOf(x, x + line[1].int())
				else -> error(line)
			}
		}
		.flatten()
		.chunked(40) { it.mapIndexed{ i, x -> x in i-1..i+1 } }.printCrt()
}


fun main() {
	println("Day 10: ")
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