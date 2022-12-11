@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d11


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
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	// var data = getLines(2016_11)

	var data = listOf(
		setOf("PrG", "PrM"),
		setOf("CoG", "CuG", "RuG", "PlG"),
		setOf("CoM", "CuM", "RuM", "PlM"),
		setOf()
	)

	data class State(val floor: Int, val items: List<Set<String>>)

	bfs(State(0, data), { it.items[3].size == 10 }) { (floor, items) ->
		val active = items.map { it.any { x -> x.endsWith("G") } }
		val unprotected = items.zip(active).any { (s, a) -> a && s.any { it.endsWith("M") && it.take(2) + "G" !in s } }
		if (unprotected) {
			return@bfs listOf()
		}

		val fl = items[floor].toList()
		val pos = fl.indices.map { i -> listOf(fl[i]) to fl.take(i) + fl.drop(i + 1) } +
				fl.indices
					.pairWise()
					.map { (i, j) -> listOf(fl[i], fl[j]) to fl.take(i) + fl.take(j).drop(i + 1) + fl.drop(j + 1) }

		val states = mutableListOf<State>()
		for ((el, rem) in pos) {
			if (floor > 0) {
				val nextItems = items.toMutableList()
				nextItems[floor] = rem.toSet()
				nextItems[floor - 1] = nextItems[floor - 1] + el
				states += State(floor - 1, nextItems)
			}

			if (floor < 3) {
				val nextItems = items.toMutableList()
				nextItems[floor] = rem.toSet()
				nextItems[floor + 1] = nextItems[floor + 1] + el
				states += State(floor + 1, nextItems)
			}

		}

		states
	}.log()

}

private fun part2() {
	// adding 1 pair instead of 2 increases the length from 33 to 45, so I guessed 57
	var data = listOf(
		setOf("PrG", "PrM", "ElG", "ElM", "DlG", "DlM"),
		setOf("CoG", "CuG", "RuG", "PlG"),
		setOf("CoM", "CuM", "RuM", "PlM"),
		setOf()
	)

	data class State(val floor: Int, val items: List<Set<String>>)

	bfs(State(0, data), { it.items[3].size == 14 }) { (floor, items) ->
		val fl = items[floor].toList()
		val pos = fl.indices.map { i -> listOf(fl[i]) to fl.take(i) + fl.drop(i + 1) } +
				fl.indices
					.pairWise()
					.map { (i, j) -> listOf(fl[i], fl[j]) to fl.take(i) + fl.take(j).drop(i + 1) + fl.drop(j + 1) }

		val states = mutableListOf<State>()
		for ((el, rem) in pos) {
			if (floor > 0) {
				val nextItems = items.toMutableList()
				nextItems[floor] = rem.toSet()
				nextItems[floor - 1] = nextItems[floor - 1] + el
				states += State(floor - 1, nextItems)
			}

			if (floor < 3) {
				val nextItems = items.toMutableList()
				nextItems[floor] = rem.toSet()
				nextItems[floor + 1] = nextItems[floor + 1] + el
				states += State(floor + 1, nextItems)
			}

		}

		states.filter{state ->
			val (_, items) = state
			val active = items.map { it.any { x -> x.endsWith("G") } }
			val unprotected = items.zip(active).any { (s, a) -> a && s.any { it.endsWith("M") && it.take(2) + "G" !in s } }
			!unprotected
		}
	}.log()
}


fun main() {
	println("Day 11: ")
	// part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }