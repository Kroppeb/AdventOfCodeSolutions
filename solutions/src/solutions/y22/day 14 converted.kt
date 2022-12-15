@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d14c


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
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
import me.kroppeb.aoc.helpers.point.toPI
import me.kroppeb.aoc.helpers.sint.compareTo
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(3, 6);


private fun part1() {
	var data = getLines(2022_14).points{ line -> line.zipWithNext{ s, e -> s toL e}.flatten() }.flatten().toSet()

	val sand = msop()

	o@ while (500 toP 0 !in sand) {

		var s = 500 toP 0
		while (true) {
			if (s.y > 5000) {
				break@o
			}
			when {
				s.d !in data or sand -> s = s.d
				s.dl !in data or sand -> s = s.dl
				s.dr !in data or sand -> s = s.dr
				else -> break
			}
		}



		sand += s
	}

//	(data or sand).bounds().print{ when{
//		it in sand -> 'o'
//		it in data -> '#'
//		else -> ' '
//	}}

	sand.size log 1

}


private fun part2() {
	var data = getLines(2022_14).points{ line -> line.zipWithNext{ s, e -> s toL e}.flatten() }.flatten().toSet()
	val yy = data.maxOf{it.y} + 2
	data += -1000 toP yy toL (2000 toP yy)

	val sand = msop()

	o@ while (500 toP 0 !in sand) {

		var s = 500 toP 0
		while (true) {
			if (s.y > 5000) {
				break@o
			}
			when {
				s.d !in data or sand -> s = s.d
				s.dl !in data or sand -> s = s.dl
				s.dr !in data or sand -> s = s.dr
				else -> break
			}
		}



		sand += s
	}
	sand.size log 1

}


fun main() {
	println("Day 14: ")
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