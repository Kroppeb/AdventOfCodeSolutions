@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


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
import kotlin.math.*

 */


import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_15).points()

	var be = data.map { it[1] }.toSet()

	var maxDist = data.maxOf { (a, b) -> a.manDistTo(b) } + 10 log 0

	var c = 0
	for (i in data.minOf { it.map { it.y }.min() } - maxDist..data.maxOf { it.map { it.y }.max() } + maxDist) {
		if (i toP 2000000 in be) {
			continue
		}

		val p = i toP 2000000

		if (data.any { (a, b) ->
				val d = p.manDistTo(a)
				val d0 = b.manDistTo(a)

				d <= d0
			}) {
			c++
		}
	}

	c log 1
}


private fun part2() {
	var data = getLines(2022_15).points()

	var be = data.map { it[1] }.toSet()

	var maxDist = data.maxOf { (a, b) -> a.manDistTo(b) } + 10 log 0

	fun find(b: Bounds) {
		val p = (b.lower + b.upper) / 2

		if (b.area == 1.s) {
			if (data.any { (a, b) ->
					val d = p.manDistTo(a)
					val d0 = b.manDistTo(a)

					d <= d0
				}) {
			} else {
				p log 2
				p.x * 4000000 + p.y log 2
			}
		} else {
			val mx = b.sizes.manDist() + 5

			if (data.any { (a, b) ->
					val d = p.manDistTo(a)
					val d0 = b.manDistTo(a)

					d <= d0 - mx
				}) {

			} else {
				b.frac(10).forEach(::find)
			}
		}
	}

	find(0 toP 0 toB (4000000 toP 4000000))
}


fun main() {
	println("Day 15: ")
//	part1()
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