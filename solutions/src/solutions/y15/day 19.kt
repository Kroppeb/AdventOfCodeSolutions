@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d19

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.bfs
import graph.dijkstra
import grid.*
import helpers.*
import itertools.*
import java.util.ArrayDeque
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/

fun splt(s: String): List<String> {
	val l = mutableListOf<String>()

	var i = 0
	while (i < s.length) {
		if (s[i].isLowerCase()) error(s)
		if (i + 1 < s.length && s[i + 1].isLowerCase()) {
			l += s.substring(i..(i + 1))
			i += 2
		} else {
			l += s.substring(i..i)
			i++
		}
	}

	return l
}

fun decalcify(mol: List<String>, reverse: Map<List<String>, List<List<String>>>): List<String> {
	var m = mol
		.zip(mol.drop(1) + listOf(""))
		.filter { (a, b) -> !(a == "Ca" && b == "Ca") }
		.filter { !(it.first == "Ca" && reverse.getOrDefault(it.toList(), emptyList()).contains(listOf(it.second))) }
		.map { it.first }
	m = m.reversed()
	m = m.zip(m.drop(1) + listOf("")).filter {
		!(it.first == "Ca" && reverse.getOrDefault(it.toList().reversed(), emptyList()).contains(listOf(it.second)))
	}.map { it.first }
	return m.reversed()
}

private fun part1() {
	var data = getLines(2015_19).splitOnEmpty()

	var replacements = data
		.first()
		.map { it.split(" => ").let { (a, b) -> a to b } }
		.groupBy { it.first }
		.mapValues { (_, v) -> v.map { splt(it.second) } }

	var start = splt(data.last().first())

	val results = mutableSetOf<List<String>>()

	for ((k, repls) in replacements) {
		for (i in start.withIndex().filter { it.value == k }.map { it.index }) {
			for (repl in repls) {
				results += start.take(i) + repl + start.drop(i + 1)
			}
		}
	}

	results.size.log()
}

val forced = mapOf(
	listOf("Th", "Rn", "F", "Ar", "Ar") to (listOf("Al", "Ar") to 1),
	listOf("Th", "Rn", "F", "Ar", "Y") to (listOf("Al", "Y") to 1),

	listOf("Si", "Al", "Y") to (listOf("F", "Y") to 1),
	listOf("Si", "Al", "Ar", "Mg") to (listOf("F", "Ar", "Mg") to 1),
	listOf("Si", "Al", "Ar", "Rn") to (listOf("F", "Ar", "Rn") to 1),

	listOf("C", "Rn", "F", "Ar", "Al") to (listOf("N", "Al") to 1),
	listOf("N", "Th", "Rn", "F", "Ar") to (listOf("N", "Al") to 1),
	listOf("O", "Ti", "Mg") to (listOf("N", "Mg") to 1),
	listOf("C", "Rn", "F", "Ar", "Ti", "Mg") to (listOf("N", "Rn", "Mg") to 1),

	listOf("Si", "Rn", "F", "Ar", "Mg", "Y") to (listOf("P", "Mg", "Y") to 1),
	listOf("Si", "Rn", "F", "Ar", "Mg", "Ar") to (listOf("P", "Mg", "Ar") to 1),


	listOf("Th", "Rn", "F", "Ar") to (listOf("Al") to 1),

	listOf("O", "Rn", "F", "Ar") to (listOf("H") to 1),
	listOf("C", "Rn", "Al", "Ar") to (listOf("H") to 1),
	listOf("C", "Rn", "F", "Y", "Mg", "Ar") to (listOf("H") to 1),
)
val maxForced = forced.keys.map { it.size }.max()

tailrec fun applyForced(l: List<String>, c: Int = 0): Pair<Int, List<String>> {
	val mol = mutableListOf<String>()
	var cost = c

	var i = 0
	w@ while (i in l.indices) {
		for (s in 2..maxForced) {
			if (i + s >= l.size) break
			val (repl, c) = forced[l.subList(i, i + s)] ?: continue
			i += s
			mol.addAll(repl)
			cost += c
			continue@w
		}

		mol.add(l[i])
		i++
	}

	if (cost != c) {
		return applyForced(mol, cost)
	} else {
		return cost to mol
	}
}

fun findReversePairs(replacements: Map<String, List<List<String>>>): Map<Pair<String, String>, Set<Pair<String, String>>> {
	val stack = ArrayDeque<Pair<String, String>>()

	val map = mutableMapOf<Pair<String, String>, MutableSet<Pair<String, String>>>()

	for ((k, v) in replacements.entries) {
		for (l in v) {
			for (pair in l.zipWithNext()) {
				stack.add(pair)
				map.computeIfAbsent(pair) { mutableSetOf() }.add(k to "")
			}
		}
	}

	while (stack.isNotEmpty()) {
		val key = stack.poll()!!

		for (i in replacements[key.first] ?: emptyList()) {
			val newKey = i.last() to key.second
			if (map.computeIfAbsent(newKey) { mutableSetOf() }.add(key)) {
				stack.add(newKey)
			}
		}

		for (i in replacements[key.second] ?: emptyList()) {
			val newKey = key.first to i.first()
			if (map.computeIfAbsent(newKey) { mutableSetOf() }.add(key)) {
				stack.add(newKey)
			}
		}
	}

	return map
}


fun findReverseLists(replacements: Map<String, List<List<String>>>, length: Int): Map<List<String>, Set<List<String>>> {
	val stack = ArrayDeque<List<String>>()

	val map = mutableMapOf<List<String>, MutableSet<List<String>>>()

	for (l in replacements["e"]!!) {
		val s = length.coerceAtMost(l.size)
		for (window in l.windowed(s)) {
			stack.add(window)
			map.computeIfAbsent(window) { mutableSetOf() }.add(listOf("e"))
		}
	}

	while (stack.isNotEmpty()) {
		val key = stack.poll()!!

		for (i in key.indices) {
			for (repl in replacements[key[i]] ?: emptyList()) {
				val newKey = key.take(i) + repl + key.drop(i + 1)

				val s = length.coerceAtMost(newKey.size)
				for (window in newKey.windowed(s)) {
					if (map.computeIfAbsent(window) { mutableSetOf() }.add(key)) {
						stack.add(window)
					}
				}
			}
		}
	}

	return map
}

fun findReverseListsH(
	replacements: Map<String, List<List<String>>>,
	length: Int,
	hist: Int
): Map<List<String>, Set<List<List<String>>>> {
	val stack = ArrayDeque<List<List<String>>>()

	val map = mutableMapOf<List<String>, MutableSet<List<List<String>>>>()

	for (l in replacements["e"]!!) {
		val s = length.coerceAtMost(l.size)
		for (window in l.windowed(s)) {
			stack.add(listOf(window, listOf("e")))
			map.computeIfAbsent(window) { mutableSetOf() }.add(listOf(listOf("e")))
		}
	}

	while (stack.isNotEmpty()) {
		val keys = stack.poll()!!
		val key = keys.last()

		for (i in key.indices) {
			for (repl in replacements[key[i]] ?: emptyList()) {
				val newKey = key.take(i) + repl + key.drop(i + 1)

				val s = length.coerceAtMost(newKey.size)
				for (window in newKey.windowed(s)) {
					val newKeys = (listOf(window) + keys).take(hist)
					if (map.computeIfAbsent(window) { mutableSetOf() }.add(keys)) {
						stack.add(newKeys)
					}
				}
			}
		}
	}

	return map
}

private fun part2Old() {
	var data = getLines(2015_19).splitOnEmpty()

	val end = splt(data.last().first()).log()
	end.size.log()

	var replacements = data
		.first()
		.map { it.split(" => ").let { (a, b) -> a to b } }
		.groupBy { it.first }
		.mapValues { (_, v) -> v.map { splt(it.second) } }

	var reverse =
		data.first().map { it.split(" => ").let { (a, b) -> a to b } }.groupBy { it.second }.entries.map { (k, v) ->
			splt(k) to v.map { listOf(it.first) }
		}.toMap()

	val maxLength = reverse.keys.map { it.size }.max()

	val filteredReplacements = replacements.entries.filter { (k, v) ->
		!v.all { x ->
			val (a, b) = x
			x.size == 2 && (a == "Ca" && b == k || a == k && b == "Ca")
		}
	}.map { (k, v) -> k to v }.toMap()

	val seen = reverse.keys.flatten().toSet().filter { it !in filteredReplacements.keys }
	val findReversePairs = findReversePairs(filteredReplacements)
	val validPairs = findReversePairs.log().keys
	val singleReverses = findReversePairs.filter { (_, v) -> v.size == 1 }.log()
	val findReverseTriples = findReverseLists(filteredReplacements, 3)
		.filter { (k, v) -> v.size == 1 && k.zipWithNext().all { it !in singleReverses.keys } }
		.log()
	val findReverseQuadruplets = findReverseLists(filteredReplacements, 4)
		.filter { (k, v) ->
			v.size == 1 &&
					k.zipWithNext().all { it !in singleReverses.keys } &&
					k.windowed(3).all { it !in findReverseTriples }
		}.log()
	val findReverseQuadruplets2 = findReverseListsH(filteredReplacements, 3, 2)
		.map { (k, v) -> k to v.map { it.last() }.toSet() }
		.filter { (k, v) ->
			v.size == 1 &&
					k.zipWithNext().all { it !in singleReverses.keys } &&
					k.windowed(3).all { it !in findReverseTriples }
		}
		.log()

	var shortest = end.size
	decalcify(end, reverse).log().let {
		applyForced(it)
	}.log()

	dijkstra(end, { it.size == 1 && it.first() == "e" }) { mol ->
		var decal = decalcify(mol, reverse)
		var short = mol.size - decal.size
		val x = applyForced(decal)
		short += x.first
		decal = x.second
		if (decal.zipWithNext().any { it !in validPairs }) {
			emptyList()
		} else buildList {
			for (i in 1..maxLength) {
				for (start in 0..(decal.size - i)) {
					for (repl in reverse.getOrDefault(decal.subList(start, start + i), listOf())) {
						val element = decal.take(start) + repl + decal.drop(start + i)
						add(element to 1 + short)
						if (element.size < shortest) {
							shortest = element.size.log()
						}
					}
				}
			}
		}
	}

}



// I cheated and looked up some stuff
private fun part2() {
	var data = getLines(2015_19)
	var tt = data.dropLast(2).map{it.split(" => ")}

	var end = data.last()

	var c = 0
	o@ while (end != "e") {
		c++
		for ((f, t) in tt) {
			if (t in end) {
				end = end.replaceFirst(t, f)
				continue@o
			}
		}
		error(end)
	}
	c.log()
}

data class Test(val i:Int){

}


fun main() {
	println("Day 19: ")
	//part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }