@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d18


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
import solutions.solutions.y19.d22.l
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_18).first().map{it == '^'}

	val tiles = listOf(data) + generateTimes(39, data) {l ->
		(listOf(false) + l + listOf(false)).windowed(3).map{(a,b,c) -> (b && (a != c)) || (a != c && !b)}
	}

	tiles.flatten().count{!it}.log()
}

private fun part2() {
	var data = getLines(2016_18).first().map{it == '^'}

	val tiles = listOf(data) + generateTimes(399999, data) {l ->
		(listOf(false) + l + listOf(false)).windowed(3).map{(a,b,c) -> (b && (a != c)) || (a != c && !b)}
	}

	tiles.flatten().count{!it}.log()
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }