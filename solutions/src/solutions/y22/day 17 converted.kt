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
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import me.kroppeb.aoc.helpers.collections.list.Het3
import me.kroppeb.aoc.helpers.collections.list.toH
import kotlin.math.*


private val xxxxx = Clock(3, 12);

/*
####

.#.
###
.#.

..#
..#
###

#
#
#
#

##
##
 */
private val rocks = listOf(
	listOf(0 toP 0, 1 toP 0, 2 toP 0, 3 toP 0),
	listOf(1 toP 0, 0 toP 1, 1 toP 1, 2 toP 1, 1 toP 2),
	listOf(0 toP 0, 1 toP 0, 2 toP 0, 2 toP 1, 2 toP 2),
	listOf(0 toP 0, 0 toP 1, 0 toP 2, 0 toP 3),
	listOf(0 toP 0, 1 toP 0, 0 toP 1, 1 toP 1),
)

private fun part2() {
	var data = getLines(2022_17).first().map{it == '<'}

	val room = (0..6).map{it toP 0}.toMutableSet()

	pureStateLoopScore (0 to 0, 1_000_000_000_000.s, 1000.s) {(rock, x) ->
		var base = room.maxOf { it.y } + 4

		var pos = rocks[rock % 5].map{it + (2 toP base)}
		var x = x;

		while(true){
			var i = data[x++ % data.size]
			var next =
				if (i) {
					pos.map{it.left}
				} else {
					pos.map{it.right}
				}

			if (next anyIn room || next.any{it.x < 0 || it.x > 6}) {

			} else {
				pos = next
			}

			next = pos.map{it.down}

			if (next anyIn room || next.any{it.x < 0 || it.x > 6}) {
				break
			} else {
				pos = next
			}

//			(room or pos).bounds().print{if (it in room || it in pos) '#' else '.'}
//			println()
		}


		room.addAll(pos)
//		room.bounds().print{if (it in room) '#' else '.'}
//		println("---------")

		(rock + 1) % 5 to x % data.size to room.maxOf{it.y}
	} log 2
}


fun main() {
	println("Day 17: ")
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T = also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
	.also { if (meta in listOf("1","2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}