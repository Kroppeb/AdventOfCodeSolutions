@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d04c


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
	getLines(2022_04).posInts().map { it.chunked(2) { (a, b) -> a..b } }
		.count { (x, y) -> x.all { it in y } || y.all { it in x } }
		.log()
}

private fun part2() {
	var data = getLines(2022_04).posInts().map { it.chunked(2) { (a, b) -> a..b } }
		.count { (x, y) -> x.any { it in y } || y.any { it in x } }
		.log()
}


fun main() {
	println("Day 4: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }