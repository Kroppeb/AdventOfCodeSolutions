@file:Suppress("PackageDirectoryMismatch")

package solutions.solutions.y20.d036

import helpers.*
import grid.*

private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	val d = mutableListOf<List<String>>()
	var u = mutableListOf<String>()
	for (i in data) {
		if (i.isBlank()) {
			d += u;
			u = mutableListOf()
		} else {
			u.add(i)
		}
	}
	d += u;
	var res = 0
	for (uu in d) {
		var x: Set<Char>? = null
		for (p in uu.map { it.e().toSet() }) {
			if (x == null) {
				x = p
			} else {
				x += p
			}
		}
		res += x?.size ?: 0

	}
	println(res)
}

private fun part2(data: Data) {

	val d = mutableListOf<List<String>>()
	var u = mutableListOf<String>()
	for (i in data) {
		if (i.isBlank()) {
			d += u;
			u = mutableListOf()
		} else {
			u.add(i)
		}
	}
	d += u;
	var res = 0
	for (uu in d) {
		var x: Set<Char>? = null
		for (p in uu.map { it.e().toSet() }) {
			if (x == null) {
				x = p
			} else {
				x *= p
			}
		}
		res += x?.size ?: 0

	}
	println(res)
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_06)
	part1(data)
	part2(data)
}