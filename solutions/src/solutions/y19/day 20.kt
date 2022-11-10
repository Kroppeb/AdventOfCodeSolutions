@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d20

import grid.entityGrid
import helpers.*
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.ArrayDeque


private fun part1(data: Data) = runBlocking {
	val grid = data.entityGrid { it == '.' }
	val portalP = data.entityGrid { it.isLetter() }

	val portals = mutableMapOf<String, MutableList<Point>>()

	for (i in portalP.points) {
		if (i + (0 toP 1) in portalP.points) {
			val ps = portals.getOrPut("${data[i.x][i.y]}${data[i.x][i.y + 1]}") { mutableListOf() }
			for (j in i.getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
			for (j in (i + (0 toP 1)).getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
		}

		if (i + (1 toP 0) in portalP.points) {
			val ps = portals.getOrPut("${data[i.x][i.y]}${data[i.x + 1][i.y]}") { mutableListOf() }
			for (j in i.getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
			for (j in (i + (1 toP 0)).getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
		}
	}

	val gportals = portals.flatMap { (k, v) -> v.map { it to k } }.toMap()

	val seen = mutableSetOf<Point>()
	val queue = ArrayDeque<Pair<Point, Int>>()
	val end = portals["ZZ"]!!.first()
	queue.add(portals["AA"]!!.first() to 0)
	while (queue.size > 0) {
		val (p, d) = queue.pop()
		if (p == end) {
			println(d)
			return@runBlocking
		}
		val q = gportals[p]
		if (q != null) {
			for (i in portals[q]!!) {
				if (i !in seen) {
					seen.add(i)
					queue.add(i to d + 1)
				}
			}
		}

		for (i in p.getQuadNeighbours()) {
			if (i in grid.points && i !in seen) {
				seen.add(i)
				queue.add(i to d + 1)
			}
		}
	}
	println("no path")
}

private data class State(val p: Point, val d: Int, val depth: Int)

private fun part2(data: Data) = runBlocking {
	val w = data.map { it.size }.max()
	val grid = data.entityGrid { it == '.' }
	val portalP = data.entityGrid { it.isLetter() }

	fun isOutside(p: Point) = p.x == 2 || p.y == 2 || p.x == data.size - 3 || p.y == w - 3

	val portals = mutableMapOf<String, MutableList<Point>>()

	for (i in portalP.points) {
		if (i + (0 toP 1) in portalP.points) {
			val ps = portals.getOrPut("${data[i.x][i.y]}${data[i.x][i.y + 1]}") { mutableListOf() }
			for (j in i.getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
			for (j in (i + (0 toP 1)).getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
		}

		if (i + (1 toP 0) in portalP.points) {
			val ps = portals.getOrPut("${data[i.x][i.y]}${data[i.x + 1][i.y]}") { mutableListOf() }
			for (j in i.getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
			for (j in (i + (1 toP 0)).getQuadNeighbours()) {
				if (j in grid.points)
					ps.add(j)
			}
		}
	}

	val gportals = portals.flatMap { (k, v) -> v.map { it to k } }.toMap()

	// added afterwards
	val back = mutableMapOf<State, State>()
	var deepest = 0

	val seen = mutableSetOf<Pair<Point, Int>>()
	val queue = ArrayDeque<State>()
	val end = portals["ZZ"]!!.first()
	val first = portals["AA"]!!.first()
	queue.add(State(first, 0, 0))
	while (queue.size > 0) {
		val state = queue.pop()
		val (p, d, l) = state
		if (l == 0 && p == end) {
			println(d)
			val seq = generateSequence(state, back::get)
			println(seq.map{it.depth}.max())
			println(deepest)
			return@runBlocking
		}
		val q = gportals[p]
		if (q != null) {
			val nl = if (isOutside(p)) l - 1 else l + 1

			if (nl >= 0)
				for (i in portals[q]!!) {
					if (i != p && i to nl !in seen) {
						seen.add(i to nl)
						val s = State(i, d + 1, nl)
						deepest = deepest.coerceAtLeast(nl)
						back[s] = state
						queue.add(s)
					}
				}
		}

		for (i in p.getQuadNeighbours()) {
			if (i in grid.points && i to l !in seen) {
				seen.add(i to l)
				val s = State(i, d + 1, l)
				back[s] = state
				queue.add(s)
			}
		}
	}
	println("no path")
}

typealias Data = List<List<Char>>

fun main() {
	val data: Data = getLines(2019_20).map { it.map { it } }
	part1(data)
	part2(data)
}