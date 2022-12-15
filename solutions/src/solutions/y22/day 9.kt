@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d09


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
import me.kroppeb.aoc.helpers.point.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_09)

	val seen = msopi()
	var head = 0 toPI 0
	var tail = head

	seen.add(tail)

	for (line in data) {
		val rep = line.int()
		repeat(rep) {
			when (line[0]) {
				'U' -> head = head.up
				'D' -> head = head.down
				'L' -> head = head.left
				'R' -> head = head.right
				else -> error(line)
			}

			var (a, b) = tail
			var (c, d) = head

			if (abs(a - c) > 1 || abs(b - d) > 1) {
				val p = tail toL head
				if (p.isAxisAligned()) {
					tail = p.toList()[1]
				} else {

					a += (c - a).sign
					b += (d - b).sign

					tail = a toPI b
				}
			}

			seen.add(tail)
		}

		println("head: $head, tail: $tail")
	}

	seen.size log 1

}

private fun part2() {
	var data = getLines(2022_09)

	val seen = msopi()
	var head = 0 toPI 0
	var knots = (1..9).map { head }.mut()

	seen.add(head)

	for (line in data) {
		val rep = line.int()
		repeat(rep) {
			when (line[0]) {
				'U' -> head = head.up
				'D' -> head = head.down
				'L' -> head = head.left
				'R' -> head = head.right
				else -> error(line)
			}

			var follow = head
			repeat(9) { i ->
				var (a, b) = knots[i]
				var (c, d) = follow

				if (abs(a - c) > 1 || abs(b - d) > 1) {
					val p = knots[i] toL follow
					if (p.isAxisAligned()) {
						knots[i] = p.toList()[1]
					} else {

						a += (c - a).sign
						b += (d - b).sign

						knots[i] = a toPI b
					}
				}

				follow = knots[i]

			}
			seen.add(follow)
			//println(knots[9])
		}
		// println(head)
		// println(knots)
	}

	seen.size log 1

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