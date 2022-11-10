@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d20c

import collections.defaultMapOf
import graph.bfs
import grid.*
import helpers.*
import kotlin.collections.*

val p = Clock(3, 6)


private fun part1(data: Data) {
	val walkable = data.entityGrid { it == '.' }
	val portalP = data.entityGrid { it.isLetter() }

	val portals = defaultMapOf<String, MutableList<Point>> { mutableListOf() }

	for (i in portalP.points) {
		if (i.right in portalP.points) {
			val ps = portals["${data[i]}${data[i.right]}"]
			for (j in i.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
			for (j in i.right.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
		}

		if (i.down in portalP.points) {
			val ps = portals["${data[i]}${data[i.down]}"]
			for (j in i.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
			for (j in i.down.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
		}
	}

	val gportals: Map<Point, String> = portals.flatMap { (k, v) -> v.map { it to k } }.toMap()

	val end = portals["ZZ"].first()
	val (p, d) = bfs(portals["AA"].first(), { it == end }, {
		buildList {
			val q = gportals[it]
			if (q != null) {
				println("portal: $q")
				addAll(portals[q])
			}

			addAll(it.getQuadNeighbours().filter { i -> i in walkable.points })
		}
	})
	if (p != null)
		println(d)
	else
		println("no path")
}

private data class State(val p: Point, val d: Int, val depth: Int)

private fun part2(data: Data) {
	val walkable = data.entityGrid { it == '.' }
	val portalP = data.entityGrid { it.isLetter() }

	fun isOutside(p: Point) = p.x == 2 || p.y == 2 || p.x == data.bounds.higher.x - 2 || p.y == data.bounds.higher.y - 2

	val portals = defaultMapOf<String, MutableList<Point>> { mutableListOf() }

	for (i in portalP.points) {
		if (i.right in portalP.points) {
			val ps = portals["${data[i]}${data[i.right]}"]
			for (j in i.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
			for (j in i.right.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
		}

		if (i.down in portalP.points) {
			val ps = portals["${data[i]}${data[i.down]}"]
			for (j in i.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
			for (j in i.down.getQuadNeighbours()) {
				if (j in walkable.points)
					ps.add(j)
			}
		}
	}

	val gportals: Map<Point, String> = portals.flatMap { (k, v) -> v.map { it to k } }.toMap()


	val end = portals["ZZ"].first() to 0
	val (p, d) = bfs(portals["AA"].first() to 0, { it == end }) { (p, l) ->
		buildList {
			val q = gportals[p]
			if (q != null) {
				val nl = if (isOutside(p)) l - 1 else l + 1

				if (nl >= 0)
					for (i in portals[q])
						if (i != p) add(i to nl)
			}

			addAll(p.getQuadNeighbours().filter { i -> i in walkable.points }.map{it to l})
		}
	}

	if (p != null)
		println(d)
	else
		println("no path")
}

private typealias Data = SimpleGrid<Char>

fun main() {
	val data: Data = getLines(2019_20).e().grid().also { println(it.bounds) }
	part1(data)
	part2(data)
}