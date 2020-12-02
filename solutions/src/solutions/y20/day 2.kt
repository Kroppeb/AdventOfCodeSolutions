@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y20.d02

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*

val xxxxx = Clock(3,6);

private fun part1(data: Data) {
	data.filter{it.isNotBlank()}.map{it.split(' ')}.map{(a,b,c) ->
		val (x,y) = a.split('-').map{it.toInt()}
		val range = x..y
		val l = b[0]
		c.count { it == l } in range
	}.count{it}.let{println(it)}
}

private fun part2(data: Data) {
	data.filter{it.isNotBlank()}.map{it.split(' ')}.map{(a,b,c) ->
		val (x,y) = a.split('-').map{it.toInt()}
		val l = b[0]
		(c[x - 1] == l) != (c[y-1] == l)
	}.count{it}.let{println(it)}
}

private typealias Data = List<String>

fun main() {
	val data: Data = getLines(2020_02)
	part1(data)
	part2(data)
}