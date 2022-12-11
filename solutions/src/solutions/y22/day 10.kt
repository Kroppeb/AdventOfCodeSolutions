@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d10


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
	var data = getLines(2022_10).map{it.split(" ")}

	var sum = 0
	var x = 1

	var i = 0

	for (line in data) {
		when(line[0] ) {
			"noop" -> {
				i++
				if ((i % 40) == 20) {
					sum += x * i
				}
			}
			"addx" -> {
				i++
				if ((i % 40) == 20) {
					sum += x * i
				}
				i++
				if ((i % 40) == 20) {
					sum += x * i
				}
				x += line[1].toInt()
			}
		}
	}

	sum log 1



}

private fun part2() {
	var data = getLines(2022_10).map{it.split(" ")}

	var sum = 0
	var x = 1

	var i = 0

	var p = (0..39).map{x -> (0..5).map{false}}.mut2()
	for (line in data) {
		when(line[0] ) {
			"noop" -> {
				i++
				if ((i % 40) - x in -1..1) {
					p[i % 40][i/40] = true
				}
			}
			"addx" -> {
				i++
				if ((i % 40) - x in -1..1) {
					p[i % 40][i/40] = true
				}
				i++
				x += line[1].toInt()
				if ((i % 40) - x in -1..1) {
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