@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d08

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
	var data = getLines(2017_08).map {
		it.split(" if ").let { (a, b) ->
			a.split(" ").let { (x, y, z) -> x to z.int() to (y == "inc") } to
					b.split(" ").let { (x, y, z) -> x to z.int() to y }}
	}

	val regs = mutableMapOf<String, Int>()

	for ((a,cond) in data) {
		val condReg = regs.getOrPut(cond.first.first){0}
		val r = when (cond.second) {
			">" -> condReg > cond.first.second
			">=" -> condReg >= cond.first.second
			"<" -> condReg < cond.first.second
			"<=" -> condReg <= cond.first.second
			"==" -> condReg == cond.first.second
			"!=" -> condReg != cond.first.second
			else -> error(cond.second)
		}

		if (r) {
			regs[a.first.first] = regs.getOrDefault(a.first.first, 0) + a.first.second * if (a.second) 1 else -1
		}
	}

	regs.values.max().log()

}

private fun part2() {
	var data = getLines(2017_08).map {
		it.split(" if ").let { (a, b) ->
			a.split(" ").let { (x, y, z) -> x to z.int() to (y == "inc") } to
					b.split(" ").let { (x, y, z) -> x to z.int() to y }}
	}
	var max = 0;

	val regs = mutableMapOf<String, Int>()

	for ((a,cond) in data) {
		val condReg = regs.getOrPut(cond.first.first){0}
		val r = when (cond.second) {
			">" -> condReg > cond.first.second
			">=" -> condReg >= cond.first.second
			"<" -> condReg < cond.first.second
			"<=" -> condReg <= cond.first.second
			"==" -> condReg == cond.first.second
			"!=" -> condReg != cond.first.second
			else -> error(cond.second)
		}

		if (r) {
			regs[a.first.first] = regs.getOrDefault(a.first.first, 0) + a.first.second * if (a.second) 1 else -1

			max = max(max, regs[a.first.first]!!)
		}
	}

	max.log()
}


fun main() {
	println("Day 8: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }