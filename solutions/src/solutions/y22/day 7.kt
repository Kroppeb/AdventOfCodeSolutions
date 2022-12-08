@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


/*
import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_07)

	val s = mutableMapOf<List<String>, Int>()

	var d = listOf<String>()

	var i = 0
	while (i in data.indices) {
		var line = data[i]
		if (line.startsWith("$ cd ")) {
			val dir = line.drop(5)
			if (dir == "..") {
				d = d.dropLast(1)
			} else {
				d = d + listOf(dir)
			}
			i++
		} else {
			i++
			while (i in data.indices && !data[i].startsWith("$")) {
				line = data[i]
				val (a, b) = line.split(" ")
				val x = a.toIntOrNull()
				if (x != null) {
					s[d + listOf(b, "_")] = x
				} else {
					s[d + listOf(b, ".")] = 0
				}
				i++
			}
		}
	}

	for (dir in s.keys.sortedBy { -it.size }) {
		val k = dir.dropLast(2) + listOf(".")
//		dir to k log 0
		s[k] = (s[k] ?: 0) + s[dir]!!
	}

	s.entries.filter{(k,v) ->  k.size > 0 && k.last() == "." && v<= 100000 }.sumBy{(k,v) -> v}.log()
}


private fun part2() {
	var data = getLines(2022_07)

	val s = mutableMapOf<List<String>, Int>()

	var d = listOf<String>()

	var i = 0
	while (i in data.indices) {
		var line = data[i]
		if (line.startsWith("$ cd ")) {
			val dir = line.drop(5)
			if (dir == "..") {
				d = d.dropLast(1)
			} else {
				d = d + listOf(dir)
			}
			i++
		} else {
			i++
			while (i in data.indices && !data[i].startsWith("$")) {
				line = data[i]
				val (a, b) = line.split(" ")
				val x = a.toIntOrNull()
				if (x != null) {
					s[d + listOf(b, "_")] = x
				} else {
					s[d + listOf(b, ".")] = 0
				}
				i++
			}
		}
	}

	for (dir in s.keys.sortedBy { -it.size }) {
		val k = dir.dropLast(2) + listOf(".")
//		dir to k log 0
		s[k] = (s[k] ?: 0) + s[dir]!!
	}

	// s.entries.filter{(k,v) ->  k.size > 0 && k.last() == "." && v<= 100000 }.sumBy{(k,v) -> v}.log()
	val needed = 30000000 - (70000000 - s[listOf("/", ".")]!!)  log 0

	s.entries.filter{(k,v) -> k.last() == "." && v >= needed }.minBy{(_,v) -> v}.value.log()
}


fun main() {
	println("Day 7: ")
	part1()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
	.also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}