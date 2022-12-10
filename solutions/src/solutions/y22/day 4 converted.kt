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
import solutions.solutions.y19.d20c.p
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	getLines(2022_04).posInts().map { it.chunked(2) { (a, b) -> a..b } }
		.count { (x, y) -> x.all { it in y } || y.all { it in x } }.log()
	getLines(2022_04).posInts().count { (a, b, c, d) -> (a..b).allIn(c..d) || (c..d).allIn(a..b) }.log()
	getLines(2022_04).posInts().map { it.splitIn2 { (a, b) -> a..b } }
		.count { (x, y) -> x.allIn(y) || y.allIn(x) }.log()
}

private fun part2() {
	getLines(2022_04).posInts().map { it.chunked(2) { (a, b) -> a..b } }
		.count { (x, y) -> x.any { it in y } || y.any { it in x } }.log()
	getLines(2022_04).posInts().map { it.chunked(2) { (a, b) -> a..b } }
		.count { (x, y) -> x.any { it in y } }.log()
	getLines(2022_04).posInts().count { (a, b, c, d) -> (a..b).anyIn(c..d) || (c..d).anyIn(a..b) }.log()
	getLines(2022_04).posInts().count { (a, b, c, d) -> (a..b).anyIn(c..d) }.log()
	getLines(2022_04).posInts().map { it.splitIn2 { (a, b) -> a..b } }
		.count { it.intersect().isNotEmpty() }.log()
	getLines(2022_04).posInts().count { it.splitIn2 { (a, b) -> a..b }.intersect().isNotEmpty() }.log()
	getLines(2022_04).posInts().count { it.splitIn2 { it.minMaxIR() }.intersect().isNotEmpty() }.log()
	getLines(2022_04).posInts().count { it.splitIn(2) { it.minMaxIR() }.intersect().isNotEmpty() }.log()
}


fun main() {
	println("Day 4: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }