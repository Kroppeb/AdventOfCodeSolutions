@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d07

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import grid.mutableGrid
import helpers.*
import itertools.*
import kotlin.math.*


val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	val data = getLines(2015_07)

	val consts = mutableMapOf<String, String>()
	val ands = mutableMapOf<String, Pair<String, String>>()
	val ors = mutableMapOf<String, Pair<String, String>>()
	val lshifts = mutableMapOf<String, Pair<String, Int>>()
	val rshifts = mutableMapOf<String, Pair<String, Int>>()
	val nots = mutableMapOf<String, String>()

	val values = mutableMapOf<String, Int>()

	for (line in data) {
		when {
			"AND" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				ands[c] = a to b
			}

			"OR" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				ors[c] = a to b
			}

			"LSHIFT" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				lshifts[c] = a to b.toInt()
			}

			"RSHIFT" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				rshifts[c] = a to b.toInt()
			}

			"NOT" in line -> {
				val (_, a, _, b) = line.split(" ")
				nots[b] = a
			}

			else -> {
				val (a, _, b) = line.split(" ")
				consts[b] = a
			}
		}
	}

	fun find(x: String): Int {
		values[x]?.let { return it }

		val i = x.toIntOrNull()
		if (i != null) {
			values[x] = i
			return i
		}

		consts[x]?.let {
			values[x] = find(it); return values[x]!!
		}


		ands[x]?.let { values[x] = find(it.first) and find(it.second); return values[x]!! }
		ors[x]?.let { values[x] = find(it.first) or find(it.second); return values[x]!! }
		lshifts[x]?.let { values[x] = (find(it.first) shl it.second) and UShort.MAX_VALUE.toInt(); return values[x]!! }
		rshifts[x]?.let { values[x] = find(it.first) shr it.second; return values[x]!! }
		nots[x]?.let { values[x] = find(it).inv() and UShort.MAX_VALUE.toInt(); return values[x]!! }
		error("Can't find $x")
	}

	find("a").log()

}

private fun part2() {

	val data = getLines(2015_07)

	val consts = mutableMapOf<String, String>()
	val ands = mutableMapOf<String, Pair<String, String>>()
	val ors = mutableMapOf<String, Pair<String, String>>()
	val lshifts = mutableMapOf<String, Pair<String, Int>>()
	val rshifts = mutableMapOf<String, Pair<String, Int>>()
	val nots = mutableMapOf<String, String>()

	val values = mutableMapOf<String, Int>()

	for (line in data) {
		when {
			"AND" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				ands[c] = a to b
			}

			"OR" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				ors[c] = a to b
			}

			"LSHIFT" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				lshifts[c] = a to b.toInt()
			}

			"RSHIFT" in line -> {
				val (a, _, b, _, c) = line.split(" ")
				rshifts[c] = a to b.toInt()
			}

			"NOT" in line -> {
				val (_, a, _, b) = line.split(" ")
				nots[b] = a
			}

			else -> {
				val (a, _, b) = line.split(" ")
				consts[b] = a
			}
		}
	}

	fun find(x: String): Int {
		values[x]?.let { return it }

		val i = x.toIntOrNull()
		if (i != null) {
			values[x] = i
			return i
		}

		consts[x]?.let {
			values[x] = find(it); return values[x]!!
		}


		ands[x]?.let { values[x] = find(it.first) and find(it.second); return values[x]!! }
		ors[x]?.let { values[x] = find(it.first) or find(it.second); return values[x]!! }
		lshifts[x]?.let { values[x] = (find(it.first) shl it.second) and UShort.MAX_VALUE.toInt(); return values[x]!! }
		rshifts[x]?.let { values[x] = find(it.first) shr it.second; return values[x]!! }
		nots[x]?.let { values[x] = find(it).inv() and UShort.MAX_VALUE.toInt(); return values[x]!! }
		error("Can't find $x")
	}

	val x = find("a")
	values.clear()
	values["b"] = x
	find("a").log()


}


fun main() {
	println("Day 7: ")
	part1()
	part2()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }