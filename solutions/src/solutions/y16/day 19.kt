@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d19


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
	var data = getLines(2016_19).first().int().toString(2)

	var l = data.drop(1) + data[0]

	l.toInt(2).log()


}

private fun part2() {
	var data = getLines(2016_19).first().int()

	val look = mutableMapOf<Int, Int>(1 to 0)

	var x = 2
	while (x <= data) {
		val kill = x / 2
		var win = look[x-1]!! + 1
		if (win >= kill) win++
		if (win >= x) win -= x
		look[x] = win
		x++
	}

	(look[data]!! + 1).log()


}


fun main() {
	println("Day 19: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }