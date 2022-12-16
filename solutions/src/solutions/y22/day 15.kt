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

	extracted(be, data, (0..4050000 step 0x1_00_00).cartesianSquare().map { (x, y) -> x toP y }, 0x10000, 0..4000000.s)
}

private fun extracted(
	be: Set<Point>,
	data: List<List<Point>>,
	chunks: List<Point>,
	oldSize: Int,
	intRange: SintRange
) {
	var newSize = oldSize / 2 log 0
	var halfSize = newSize / 2
	var found = mlop()
	for (p0 in chunks) {
		for (p in listOf(
			p0.x - halfSize toP p0.y - halfSize,
			p0.x - halfSize toP p0.y + halfSize,
			p0.x + halfSize toP p0.y - halfSize,
			p0.x + halfSize toP p0.y + halfSize,
		)) {

			if (newSize == 1) {
				if (p.x !in intRange || p.y !in intRange) {
					continue
				}
				if (p in be) {
					continue
				}

				if (data.any { (a, b) ->
						val d = p.manDistTo(a)
						val d0 = b.manDistTo(a)

						d <= d0
					}) {
					continue
				}
				p log 1
				p.x * 4000000 + p.y log 1
//				error("f")
				return
			} else {
				if (data.any { (a, b) ->
						val d = p.manDistTo(a)
						val d0 = b.manDistTo(a)

						d <= d0 - newSize - 3
					}) {
					continue
				}
				found += p
			}
		}
	}

	if (newSize == 1) {
		error("f")
	}

	found.size log 0
//	found log 0

	if (halfSize == 1) {
		extracted(be, data, found.flatMap { it.getOctNeighbourHood() }, newSize, intRange)
	} else {
		extracted(be, data, found, newSize, intRange)
	}
}


fun main() {
	println("Day 15: ")
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