@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d19


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
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

 */



import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


private val xxxxx = Clock(6, 3);



private fun part1() {
	var data = getLines(2022_19).ints()

	data.productOf { (id, oreCost, clayOreCost, obiOreCost, obiClayCost, geodeOreCost, geodeObiCost) ->
		id log 0
		val start = listOf(1, 0, 0, 0) toH listOf(0, 0, 0) toH 0

		val scores = mutableMapOf(0 to mutableMapOf(listOf(1, 0, 0, 0) to mutableListOf(listOf(0, 0, 0) to 0)))

		fun getScore(robots: List<Int>, items: List<Int>, time: Int, force: Boolean): Int {
			val list = scores.getOrPut(time) { mutableMapOf() }.getOrPut(robots) { mutableListOf() }
			val l = list.filter { (a, b) -> a.zip(items).all { (x, y) -> x >= y } }.allMaxBy { it.second }
			if (l.isEmpty()) {
				return -1
			}

			if (l.size > 1) { // there is a better state
				if (force) {
					return 5000000
				} else {
					return -1
				}
			}

			if (l.any { it.first == items }) {
				return l[0].second
			}

			if (force) {
				return 5000000
			} else {
				return -1
			}
		}

		val queue = mutableListOf(start)

		var p = 0

		while (queue.isNotEmpty()) {
			p++
			val (robots, amount, time) = queue.removeAt(0) // log -2

			if (time >= 24) continue

			var score = getScore(robots, amount, time, false)

			if (score < 0) continue

			val (ore, clay, obi) = amount
			score += robots.last()

			var b = false


			if (ore >= oreCost && robots[0] < 4) {
				val next = listOf(robots[0] + 1, robots[1], robots[2], robots[3]) toH
						listOf(ore - oreCost + robots[0], clay  + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			if (ore >= clayOreCost && robots[1] < 20) {
				val next = listOf(robots[0], robots[1] + 1, robots[2], robots[3]) toH
						listOf(ore - clayOreCost + robots[0], clay + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			if (ore >= obiOreCost && clay >= obiClayCost) {
				val next = listOf(robots[0], robots[1], robots[2] + 1, robots[3]) toH
						listOf(ore - obiOreCost + robots[0], clay - obiClayCost + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			if (ore >= geodeOreCost && obi >= geodeObiCost) {
				val next = listOf(robots[0], robots[1], robots[2], robots[3] + 1) toH
						listOf(ore - geodeOreCost + robots[0], clay  + robots[1], obi - geodeObiCost + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			run {
				val next = robots toH listOf(ore + robots[0], clay + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
			}
		}

		scores.values.maxOf { it.values.maxOf { it.maxOf { it.second } } } * id log 0
//		p log -1
	} log 1

}

private fun part2() {
	var data = getLines(2022_19).ints()

	data.take(3).productOf { (id, oreCost, clayOreCost, obiOreCost, obiClayCost, geodeOreCost, geodeObiCost) ->
		id log 0
		val start = listOf(1, 0, 0, 0) toH listOf(0, 0, 0) toH 0

		val scores = mutableMapOf(0 to mutableMapOf(listOf(1, 0, 0, 0) to mutableListOf(listOf(0, 0, 0) to 0)))

		fun getScore(robots: List<Int>, items: List<Int>, time: Int, force: Boolean): Int {
			val list = scores.getOrPut(time) { mutableMapOf() }.getOrPut(robots) { mutableListOf() }
			val l = list.filter { (a, b) -> a.zip(items).all { (x, y) -> x >= y } }.allMaxBy { it.second }
			if (l.isEmpty()) {
				return -1
			}

			if (l.size > 1) { // there is a better state
				if (force) {
//					return 5000000
				} else {
					return -1
				}
			}

			if (l.any { it.first == items }) {
				return l[0].second
			}

			if (force) {
				return l[0].second
			} else {
				return -1
			}
		}

		val queue = mutableListOf(start)

		var p = 0

		while (queue.isNotEmpty()) {
			p++
			val (robots, amount, time) = queue.removeAt(0) // log -2

			if (time >= 32) continue

			var score = getScore(robots, amount, time, false)

			if (score < 0) continue

			val (ore, clay, obi) = amount
			score += robots.last()

			var b = false


			if (ore >= oreCost && robots[0] < 4) {
				val next = listOf(robots[0] + 1, robots[1], robots[2], robots[3]) toH
						listOf(ore - oreCost + robots[0], clay  + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			if (ore >= clayOreCost && robots[1] < 20) {
				val next = listOf(robots[0], robots[1] + 1, robots[2], robots[3]) toH
						listOf(ore - clayOreCost + robots[0], clay + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			if (ore >= obiOreCost && clay >= obiClayCost) {
				val next = listOf(robots[0], robots[1], robots[2] + 1, robots[3]) toH
						listOf(ore - obiOreCost + robots[0], clay - obiClayCost + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			if (ore >= geodeOreCost && obi >= geodeObiCost) {
				val next = listOf(robots[0], robots[1], robots[2], robots[3] + 1) toH
						listOf(ore - geodeOreCost + robots[0], clay  + robots[1], obi - geodeObiCost + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
				b = true
			}

			run {
				val next = robots toH listOf(ore + robots[0], clay + robots[1], obi + robots[2]) toH time + 1
				val prevScore = getScore(next.a, next.b, next.c, true)
				if (prevScore < score) {
					scores[next.c]!![next.a]!!.add(next.b to score)
					queue.add(next)
				}
			}
		}

		scores.values.maxOf { it.values.maxOf { it.maxOf { it.second } } } log 0
//		p log -1
	} log 1

}


fun main() {
	println("Day 19: ")
	part1()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}