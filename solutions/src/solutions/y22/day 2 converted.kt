@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d02c


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


// Not an actual suggestion to improve, but to see what it would take to do this "not hardcoded"
private fun part1() {
	var data = getLines(2022_02).e().sumOf { (a, _, x) -> (x - 'X' + 1) + 3 * ((x - 'X' - (a - 'A') + 1 + 999) % 3) }.log()
}


private fun part2() {
	var data = getLines(2022_02).e().sumOf { (a, _, x) -> (((a - 'A') + (x - 'X') - 1 + 999) % 3) + 1 + 3 * (x - 'X') }.log()
}


fun main() {
	println("Day 2: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }