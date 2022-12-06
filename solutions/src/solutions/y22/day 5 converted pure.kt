@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d05cp


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
	val (stack, steps) = getLines(2022_05).splitOnEmpty()

	val s = stack.e().reversed().drop(1).transpose()
	val ss = s.chunked(4){it[1]}.map{it.filter{it != ' '}}

	steps.ints().fold(ss) { acc, (move, from, to) ->
		acc.mapIndexed{i, st ->
			when(i) {
				from - 1 -> st.dropLast(move)
				to - 1 -> st + acc[from - 1].takeLast(move).reversed()
				else -> st
			}
		}
	}.map{it.last()}.join().log()
}


private fun part2() {
	val (stack, steps) = getLines(2022_05).splitOnEmpty()

	val s = stack.e().reversed().drop(1).transpose()
	val ss = s.chunked(4){it[1]}.map{it.filter{it != ' '}}

	steps.ints().fold(ss) { acc, (move, from, to) ->
		acc.mapIndexed{i, st ->
			when(i) {
				from - 1 -> st.dropLast(move)
				to - 1 -> st + acc[from - 1].takeLast(move)
				else -> st
			}
		}
	}.map{it.last()}.join().log()
}


fun main() {
	println("Day 5: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }