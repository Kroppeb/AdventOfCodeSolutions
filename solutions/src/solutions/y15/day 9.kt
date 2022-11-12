@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d09

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.SimpleGraph
import graph.dijkstra
import graph.dijkstraD
import grid.Clock
import grid.mutableGrid
import helpers.*
import itertools.*
import solutions.solutions.y19.permutations
import kotlin.math.*


val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	val data = getLines(2015_09)

	val graph = SimpleGraph<String>()

	for(line in data){
		val (a, _, b, _, c) = line.split(" ")

		graph.connect(a, b, c.toDouble())
	}

	graph.neighbours.keys.map{start -> dijkstraD(setOf(start) to start, {(s) -> s.size == graph.neighbours.size}){(set, l) ->
		graph.neighboursOf(l).filter{it.key !in set}.map{set + it.key to it.key to it.value}
	} }.filterNotNull().map{it.cost}.min().log()

}

private fun part2() {
	val data = getLines(2015_09)

	val graph = SimpleGraph<String>()

	for(line in data){
		val (a, _, b, _, c) = line.split(" ")

		graph.connect(a, b, c.toDouble())
	}

	graph.neighbours.keys.permutations().map{perm ->
		perm.zipWithNext().map{(a,b) -> graph.neighboursOf(a)[b] ?: Double.POSITIVE_INFINITY}.sum()
	}.max().log()

}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }