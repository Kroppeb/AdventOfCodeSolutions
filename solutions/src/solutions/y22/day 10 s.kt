@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d10s


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
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.contextual.sumOf
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import me.kroppeb.aoc.helpers.sint.contains
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.context.start
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_10).map{it.split(" ")}

	var sum = 0.s
	var x = 1.s

	var i = 0.s

	for (line in data) {
		when(line[0] ) {
			"noop" -> {
				i++
				if ((i % 40) == 20.s) {
					sum += x * i
				}
			}
			"addx" -> {
				i++
				if ((i % 40) == 20.s) {
					sum += x * i
				}
				i++
				if ((i % 40) == 20.s) {
					sum += x * i
				}
				x += line[1].sint()
			}
		}
	}

	sum log 1



}

private fun part2() {
	var data = getLines(2022_10).map{it.split(" ")}

	var sum = 0.s
	var x = 1.s

	var i = 0.s

	var p = (0..39.s).map{x -> (0..5.s).map{false}}.mut2()
	for (line in data) {
		when(line[0] ) {
			"noop" -> {
				i++
				if ((i % 40) - x in -1..1.s) {
					p[i % 40][i/40] = true
				}
			}
			"addx" -> {
				i++
				if ((i % 40) - x in -1..1.s) {
					p[i % 40][i/40] = true
				}
				i++
				x += line[1].toInt()
				if ((i % 40) - x in -1..1.s) {
					p[i % 40][i/40] = true
				}
			}
		}
	}

	p.transpose().forEach { println(it.map{if (it) "#" else "."}.joinToString("")) }



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