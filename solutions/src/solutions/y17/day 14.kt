@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d14

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid


private val xxxxx = Clock(6, 3);

fun hash(s: String): List<Int> {
	var data = s.e().map{it.toInt()} + listOf(17, 31, 73, 47, 23)

	var cur = 0
	var skip = 0

	var state = (0..255).toList()

	repeat(64) { _ ->
		for (l in data) {
			val end = (cur + l - 1) % state.size
			if (l > 1) {
				if (end >= cur) {
					state = state.take(cur) + state.drop(cur).take(l).reversed() + state.drop(cur + l)
				} else {
					var inner = state.drop(cur) + state.take(end + 1)
					inner = inner.reversed()
					state = inner.takeLast(end + 1) + state.take(cur).drop(end + 1) + inner.dropLast(end + 1)
				}
			}

			cur += l + skip
			skip += 1
			cur %= state.size

		}
	}

	val dens  = state.chunked(16).map{it.reduce{a,b -> a xor b}}

	if (!state.areDistinct()) error("h")
	// state.size.log()

	return dens
}

private fun part1() {
	var data = getLines(2017_14).first()

	val grid = (0..127).map{ hash(data + "-" + it).flatMap { x -> (0..7).reversed().map{(x and (1 shl it)) != 0} }}

	grid.sumBy { it.count{it} }.log()
}

private fun part2() {
	var data = getLines(2017_14).first()

	val grid = (0..127).map{ hash(data + "-" + it).flatMap { x -> (0..7).reversed().map{(x and (1 shl it)) != 0} }}.grid()

	val uf = UnionFind()

	for (p in grid.bounds.log()) {
		if (!grid[p]) continue

		uf.getRoot(p)

		for (u in p.getQuadNeighbours()) {
			if (u in grid.bounds && grid[u]) {
				uf.join(p, u)
			}
		}
	}


	uf.getAllRoots().size.log()

}


fun main() {
	println("Day 14: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }