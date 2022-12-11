@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d19

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toP


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2017_19).e().grid()

	var pos = 0 toP data.items.first().indexOf('|')
	var dir = 1 toP 0

	var l = mutableListOf<Char>()

	while (true) {
		pos += dir
		val next = if(pos + dir in data.bounds) data[pos + dir] else '\u0000'

		when(val x = if(pos in data.bounds) data[pos] else '\u0000') {
			'|' -> {}
			'-' -> {}
			'\u0000' -> {break}
			'+' -> {
				dir = dir.rotateClock()
				var c = if(pos + dir in data.bounds) data[pos + dir] else '\u0000'
				if(c != '\u0000' && c != ' ' && c != if(dir.x == 0) '|' else '-') {
					continue
				}
				dir = -dir
				c = if(pos + dir in data.bounds) data[pos + dir] else '\u0000'
				if(c != '\u0000' && c != ' ' && c != if(dir.x == 0) '|' else '-') {
					continue
				}
				error("" + pos + ": " + dir)
			}
			' ' -> {break}
			else -> {
				l += x
			}
		}
	}

	l.joinToString (separator = "") .log()

}

private fun part2() {
	var data = getLines(2017_19).e().grid()

	var pos = 0 toP data.items.first().indexOf('|')
	var dir = 1 toP 0

	var l = mutableListOf<Char>()
	var q = 0

	while (true) {
		q++
		pos += dir
		val next = if(pos + dir in data.bounds) data[pos + dir] else '\u0000'

		when(val x = if(pos in data.bounds) data[pos] else '\u0000') {
			'|' -> {}
			'-' -> {}
			'\u0000' -> {break}
			'+' -> {
				dir = dir.rotateClock()
				var c = if(pos + dir in data.bounds) data[pos + dir] else '\u0000'
				if(c != '\u0000' && c != ' ' && c != if(dir.x == 0) '|' else '-') {
					continue
				}
				dir = -dir
				c = if(pos + dir in data.bounds) data[pos + dir] else '\u0000'
				if(c != '\u0000' && c != ' ' && c != if(dir.x == 0) '|' else '-') {
					continue
				}
				error("" + pos + ": " + dir)
			}
			' ' -> {break}
			else -> {
				l += x
			}
		}
	}

	q.log()

}


fun main() {
	println("Day 19: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }