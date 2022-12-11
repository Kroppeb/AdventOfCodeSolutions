@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d09c


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
import me.kroppeb.aoc.helpers.point.toP
import me.kroppeb.aoc.helpers.point.toPoint


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_09)

	var head = 0 toP 0
	var tail = head
	val seen = msop(tail)

	for (line in data) {
		val rep = line.int()
		repeat(rep) {
			head += line[0].toPoint()

			if (tail.chebyshevDistTo(head) > 1) { // tail.sqDistTo(head) > 2
				tail += (head - tail).sign()

				// sign: (head - tail).toPair().map{it.sign}.toPoint()
			}

			seen.add(tail)
		}
	}

	seen.size log 1
}

private fun part2() {
	var data = getLines(2022_09)

	val seen = msop()
	var head = 0 toP 0
	var knots = (1..9).map { head }.mut()

	seen.add(head)

	for (line in data) {
		val rep = line.int()
		repeat(rep) {
			head += line[0].toPoint()

			var follow = head
			repeat(9) { i ->
				if (knots[i].chebyshevDistTo(follow) > 1) {
					knots[i] += (follow - knots[i]).sign()
				}

				follow = knots[i]
			}
			seen.add(follow)
		}
	}

	seen.size log 2
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