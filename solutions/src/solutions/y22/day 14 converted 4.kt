@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d14


/*
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

 */

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*


private val xxxxx = Clock(3, 6);


private fun part1() {
	var data = getLines(2022_14).points()

	val walls = data.map { it.zipWithNext { s, e -> s toL e }.union() }.union()

	val sand = walls.tas()

	o@ while (500 toP 0 !in sand) {
		var s = 500 toP 0
		while (true) {
			if (s.y > 5000) {
				break@o
			} else if (s.down !in walls && s.down !in sand) {
				s = s.down
			} else if (s.downLeft !in walls && s.downLeft !in sand) {
				s = s.downLeft
			} else if (s.rightDown !in walls && s.rightDown !in sand) {
				s = s.rightDown
			} else {
				break
			}
		}

		sand += s
	}

	sand.size log 1

}


private fun part2() {
	var data = getLines(2022_14).points()

	var walls = data.map { it.zipWithNext { s, e -> s toL e }.union() }.union()
	val bounds = walls.bounds() log 0
	walls.size log 0
	walls += (-10000 toP bounds.upper.y + 2) toL (10000 toP bounds.upper.y + 2) log 0
	walls.size log 0

	val sand = walls.tas()

	o@ while (500 toP 0 !in sand) {
		var s = 500 toP 0
		while (true) {
			if (s.y > 5000) {
				error("no")
			} else if (s.down !in walls && s.down !in sand) {
				s = s.down
			} else if (s.downLeft !in walls && s.downLeft !in sand) {
				s = s.downLeft
			} else if (s.rightDown !in walls && s.rightDown !in sand) {
				s = s.rightDown
			} else {
				break
			}
		}

		sand += s
	}

	sand.size log 2
}


fun main() {
	println("Day 14: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(data: Any?) {
	val s: String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
//	clipboard.setContents(selection, selection)
}