@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d05


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
	var (stack, steps) = getLines(2022_05).splitOnEmpty()

	val s = stack.e().reversed().drop(1).transpose()
	val ss = s.chunked(4){it[1]}.map{it.filter{it != ' '}}

	val q = ss.map{ArrayDeque(it)}

	for ((a,b,c) in steps.ints()) {
		var ax = q[b - 1]
		var bx = q[c - 1]

		repeat(a) {
			bx.addLast(ax.removeLast())
		}
	}

	q.map{it.last()}.join().log()



}


private fun part2() {
	var (stack, steps) = getLines(2022_05).splitOnEmpty()

	val s = stack.e().reversed().drop(1).transpose()
	val ss = s.chunked(4){it[1]}.map{it.filter{it != ' '}}

	val q = ss.map{ArrayDeque(it)}

	for ((a,b,c) in steps.ints()) {
		var ax = q[b - 1]
		var bx = q[c - 1]

		var p = generateTimes(a, '0') {ax.removeLast()}

		for (i in p.reversed()){
			bx.addLast(i)
		}
	}

	q.map{it.last()}.join().log()



}


fun main() {
	println("Day 5: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }