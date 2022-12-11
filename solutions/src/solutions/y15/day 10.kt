@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d10

/*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = (listOf(getLines(2015_10).first().digits()))

	val cache = mutableMapOf<List<Int>, List<List<Int>>>()
	val cache2 = mutableMapOf<List<List<Int>>, List<List<List<Int>>>>()

	fun trueExpand(i: List<Int>): List<Int> {
		var c = i.first()
		var count = 1
		val p = mutableListOf<Int>()

		for (i in i.drop(1)) {
			if (c == i) {
				count++
			} else {
				p += count
				p += c
				c = i
				count = 1
			}
		}

		p += count
		p += c
		return p
	}

	fun expand(i: List<Int>): List<List<Int>> {
		if (i in cache) {
			return cache[i]!!
		}

		return trueExpand(i).chunked(10)
	}

	fun fix(i: List<List<Int>>): List<List<Int>> {
		var cur = i.first()
		val res = mutableListOf<List<Int>>()
		for (x in i.drop(1)){
			cur += x

			while (cur.size > 10) {
				val last = cur[10]
				val take = cur.take(10).dropLastWhile { it == last }
				res += take
				cur = cur.drop(take.size)
			}
		}

		while (cur.size > 10) {
			val last = cur[10]
			val take = cur.take(10).dropLastWhile { it == last }
			res += take
			cur = cur.drop(take.size)
		}

		if (cur.isNotEmpty()) {
			res += cur
		}

		return res
	}

	fun expand2(i: List<List<Int>>): List<List<List<Int>>> {
		if (i in cache2) {
			return cache2[i]!!
		}

		i.map{expand(it)}
		TODO()
	}

	repeat(40) {
		data = fix(data.flatMap { expand(it) })

		"${data.size}: ${data.sumBy { it.size }}".log()
		// data.log()

	}

	data.sumBy { it.size }.log()
}

private fun part2() {
	var data = (listOf(getLines(2015_10).first().digits()))

	val cache = mutableMapOf<List<Int>, List<List<Int>>>()
	val cache2 = mutableMapOf<List<List<Int>>, List<List<List<Int>>>>()

	fun trueExpand(i: List<Int>): List<Int> {
		var c = i.first()
		var count = 1
		val p = mutableListOf<Int>()

		for (i in i.drop(1)) {
			if (c == i) {
				count++
			} else {
				p += count
				p += c
				c = i
				count = 1
			}
		}

		p += count
		p += c
		return p
	}

	fun expand(i: List<Int>): List<List<Int>> {
		if (i in cache) {
			return cache[i]!!
		}

		return trueExpand(i).chunked(10)
	}

	fun fix(i: List<List<Int>>): List<List<Int>> {
		var cur = i.first()
		val res = mutableListOf<List<Int>>()
		for (x in i.drop(1)){
			cur += x

			while (cur.size > 10) {
				val last = cur[10]
				val take = cur.take(10).dropLastWhile { it == last }
				res += take
				cur = cur.drop(take.size)
			}
		}

		while (cur.size > 10) {
			val last = cur[10]
			val take = cur.take(10).dropLastWhile { it == last }
			res += take
			cur = cur.drop(take.size)
		}

		if (cur.isNotEmpty()) {
			res += cur
		}

		return res
	}

	fun expand2(i: List<List<Int>>): List<List<List<Int>>> {
		if (i in cache2) {
			return cache2[i]!!
		}

		i.map{expand(it)}
		TODO()
	}

	repeat(50) {
		data = fix(data.flatMap { expand(it) })

		"${data.size}: ${data.sumBy { it.size }}".log()
		// data.log()

	}

	data.sumBy { it.size }.log()
}


fun main() {
	println("Day 10: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }