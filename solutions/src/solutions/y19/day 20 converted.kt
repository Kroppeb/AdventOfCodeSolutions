@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d20c

import me.kroppeb.aoc.helpers.collections.defaultMapOf
import me.kroppeb.aoc.helpers.graph.bfs

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.SimpleGrid
import me.kroppeb.aoc.helpers.grid.entityGrid
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.point.PointI
import kotlin.collections.*

val p = Clock(3, 6)


private fun part1(data: Data) {
	val walkable = data.entityGrid { it == '.' }
	val portalP = data.entityGrid { it.isLetter() }

	val portals = defaultMapOf<String, MutableList<PointI>> { mutableListOf() }

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

	val gportals: Map<PointI, String> = portals.flatMap { (k, v) -> v.map { it to k } }.toMap()

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

private data class State(val p: PointI, val d: Int, val depth: Int)

private fun part2(data: Data) {
	val walkable = data.entityGrid { it == '.' }
	val portalP = data.entityGrid { it.isLetter() }

	fun isOutside(p: PointI) = p.x == 2 || p.y == 2 || p.x == data.boundsI.higher.x - 2 || p.y == data.boundsI.higher.y - 2

	val portals = defaultMapOf<String, MutableList<PointI>> { mutableListOf() }

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

	val gportals: Map<PointI, String> = portals.flatMap { (k, v) -> v.map { it to k } }.toMap()


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
	val data: Data = getLines(2019_20).e().grid().also { println(it.boundsI) }
	part1(data)
	part2(data)
}