@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d03c


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
import java.nio.file.Path
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.io.path.div
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2022_03).e().map{it.chunked(it.size / 2)}.sumOf{(a,b) ->
		val p = a.toSet().intersect(b.toSet()).first()
		val p2 = (a.toSet() * b.toSet()).first()
		val p3 = a.find{it in b}!!
		val p4 = a.single{it in b}
		if (p in 'a'..'z') p - 'a' + 1 else p - 'A' + 27
	}.log()

	getLines(2022_03).e().map{it.splitIn(2)}.sumOf{(a,b) ->
		val p = a.toSet().intersect(b.toSet()).first()
		val p2 = (a.toSet() * b.toSet()).first()
		val p3 = a.find{it in b}!!
		val p4 = a.single{it in b}
		if (p in 'a'..'z') p - 'a' + 1 else p - 'A' + 27
	}.log()
}


private fun part2() {
	var data = getLines(2022_03).e().chunked(3).sumOf{(a,b,c ) ->
		val p = a.toSet().intersect(b.toSet()).intersect(c.toSet()).first()
		val p2 = (a.toSet() * b.toSet() * c.toSet()).first()
		val p3 = a.filter{it in b}.single{it in c}
		val p4 = a.single{it in b && it in c}
		if (p in 'a'..'z') p - 'a' + 1 else p - 'A' + 27
	}.log()
}


fun main() {
	println("Day 3: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }