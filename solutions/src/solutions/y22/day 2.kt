@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d02


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
	var data = getLines(2022_02).map{it.split(" ")}.map{(a,x) -> when(a to x) {
		"A" to "X" -> 3 + 1
		"A" to "Y" -> 6 + 2
		"A" to "Z" -> 0 + 3
		"B" to "X" -> 0 + 1
		"B" to "Y" -> 3 + 2
		"B" to "Z" -> 6 + 3
		"C" to "X" -> 6 + 1
		"C" to "Y" -> 0 + 2
		"C" to "Z" -> 3 + 3
		else -> error(a to x)
	} }.sum().log()
}



private fun part2() {
	var data = getLines(2022_02).map{it.split(" ")}.map{(a,x) -> when(a to x) {
		"A" to "X" -> 0 + 3
		"A" to "Y" -> 3 + 1
		"A" to "Z" -> 6 + 2
		"B" to "X" -> 0 + 1
		"B" to "Y" -> 3 + 2
		"B" to "Z" -> 6 + 3
		"C" to "X" -> 0 + 2
		"C" to "Y" -> 3 + 3
		"C" to "Z" -> 6 + 1
		else -> error(a to x)
	} }.sum().log()
}


fun main() {
	println("Day 2: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }