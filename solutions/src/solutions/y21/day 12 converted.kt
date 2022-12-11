@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d12c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.SimpleGraph
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.getLines
import me.kroppeb.aoc.helpers.mlot

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	var data = getLines(2021_12)

	var graph = SimpleGraph<String>()

	for ((a, b) in data.map { it.split('-') }) {
		graph.connect(a,b)
	}

	var paths = 0
	var stack = mlot<Pair<String, Set<String>>>()
	stack.add("start" to setOf("start"))

	while (stack.isNotEmpty()) {
		val (a, b) = stack.removeAt(0)
		if (a == "end") {
			paths++;
			continue
		}
		for (c in graph.neighboursOf(a).keys) {
			if (c in b && !c[0].isUpperCase()) continue;
			var nextSeen = b.toMutableSet() + c
			stack.add(c to nextSeen)
		}
	}

	println(paths)


}


private fun part2() {
	var data = getLines(2021_12)

	var graph = SimpleGraph<String>()

	for ((a, b) in data.map { it.split('-') }) {
		graph.connect(a,b)
	}

	var paths = 0
	var stack = mlot<Pair<String, Set<String>>>()
	stack.add("start" to setOf("start"))

	while (stack.isNotEmpty()) {
		val (a, b) = stack.removeAt(0)
		if (a == "end") {
			paths++;
			continue
		}
		for (c in graph.neighboursOf(a).keys) {
			val nextSeen = b.toMutableSet()
			nextSeen += c
			if (c in b && !c[0].isUpperCase()) {
				if ("==" in b || c == "start") continue;
				nextSeen += "=="
			}
			stack.add(c to nextSeen)
		}
	}

	println(paths)
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}


private fun <T> T.log(): T = also { println(this) }