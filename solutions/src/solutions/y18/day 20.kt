@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d20

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.SimpleGraph
import me.kroppeb.aoc.helpers.graph.bfs

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.toP


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_20).first().drop(1)

	val graph = SimpleGraph<Point>()
	var i = 0

	fun parse(point: List<Point>): Collection<Point> {
		var poss = point
		while (true) {
			when (data[i]) {
				'|' -> return poss
				')' -> return poss
				'$' -> return poss
				'N' -> poss = poss.map { graph.connect(it, it.north); it.north }
				'S' -> poss = poss.map { graph.connect(it, it.south); it.south }
				'E' -> poss = poss.map { graph.connect(it, it.east); it.east }
				'W' -> poss = poss.map { graph.connect(it, it.west); it.west }
				'(' -> {
					if (data[++i] == ')') {
						continue
					}

					val newPos = mutableSetOf<Point>()

					while (true) {
						newPos += parse(poss)
						if (data[i] == ')') {
							break
						}
						i++
					}
					poss = newPos.toList()
				}
			}
			++i
		}
	}

	parse(listOf(0 toP 0))
//	val bounds = graph.neighbours.keys.bounds()
//	for (y in bounds.ys) print("##")
//	println("#")
//	for (x in bounds.xs) {
//		print("#")
//		for (y in bounds.ys) {
//			print(if (x == 0 && y == 0) "X" else ".")
//			if (graph.neighboursOf(x toP y).containsKey(x toP y + 1)) {
//				print("|")
//			} else {
//				print("#")
//			}
//		}
//		println()
//		print("#")
//		for (y in bounds.ys) {
//			if (graph.neighboursOf(x toP y).containsKey(x+1 toP y)) {
//				print("-")
//			} else {
//				print("#")
//			}
//			print("#")
//		}
//		println()
//	}
	bfs(0 toP 0, { false }) { graph.neighboursOf(it).keys }.second.log()
}

private fun part2() {

	var data = getLines(2018_20).first().drop(1)

	val graph = SimpleGraph<Point>()
	var i = 0

	fun parse(point: List<Point>): Collection<Point> {
		var poss = point
		while (true) {
			when (data[i]) {
				'|' -> return poss
				')' -> return poss
				'$' -> return poss
				'N' -> poss = poss.map { graph.connect(it, it.north); it.north }
				'S' -> poss = poss.map { graph.connect(it, it.south); it.south }
				'E' -> poss = poss.map { graph.connect(it, it.east); it.east }
				'W' -> poss = poss.map { graph.connect(it, it.west); it.west }
				'(' -> {
					if (data[++i] == ')') {
						continue
					}

					val newPos = mutableSetOf<Point>()

					while (true) {
						newPos += parse(poss)
						if (data[i] == ')') {
							break
						}
						i++
					}
					poss = newPos.toList()
				}
			}
			++i
		}
	}

	parse(listOf(0 toP 0))
//	val bounds = graph.neighbours.keys.bounds()
//	for (y in bounds.ys) print("##")
//	println("#")
//	for (x in bounds.xs) {
//		print("#")
//		for (y in bounds.ys) {
//			print(if (x == 0 && y == 0) "X" else ".")
//			if (graph.neighboursOf(x toP y).containsKey(x toP y + 1)) {
//				print("|")
//			} else {
//				print("#")
//			}
//		}
//		println()
//		print("#")
//		for (y in bounds.ys) {
//			if (graph.neighboursOf(x toP y).containsKey(x+1 toP y)) {
//				print("-")
//			} else {
//				print("#")
//			}
//			print("#")
//		}
//		println()
//	}
	val start = 0 toP 0
	val seen = mutableSetOf(start)
	val mark = mutableSetOf<Point>()
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<Point>()
	var dist = -1

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (dist >= 1000) {
				mark.add(current)
			}
			for (i in graph.neighboursOf(current).keys) {
				if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}

	mark.size.log()

}


fun main() {
	println("Day 20: ")
	part1()
	part2()
}

private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }