@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d05c


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	val (stack, steps) = getLines(2022_05).splitOnEmpty()

	val s = stack.e().reversed().drop(1).transpose().chunked(4){it[1]}.map{it.filter{it != ' '}}.toMutableList()

	for ((move, from, to) in steps.ints()) {
		s[to - 1] = s[to - 1] + s[from - 1].takeLast(move).reversed()
		s[from - 1] = s[from - 1].dropLast(move)
	}

	s.map{it.last()}.join().log()
}


private fun part2() {
	val (stack, steps) = getLines(2022_05).splitOnEmpty()

	val s = stack.e().reversed().drop(1).transpose().chunked(4){it[1]}.map{it.filter{it != ' '}}.toMutableList()

	for ((move, from, to) in steps.ints()) {
		s[to - 1] = s[to - 1] + s[from - 1].takeLast(move)
		s[from - 1] = s[from - 1].dropLast(move)
	}

	s.map{it.last()}.join().log()
}


fun main() {
	println("Day 5: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
