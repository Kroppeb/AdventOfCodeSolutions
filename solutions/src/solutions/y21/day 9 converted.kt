@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d9c

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import grid.grid
import helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
	var data = getLines(2021_09).digits().grid()//.log()

	data.bounds
		.filter { it.getQuadNeighbours().filter { it in data.bounds }.all { x -> data[x] > data[it] } }
		.map { data[it] + 1 }
		.sum()
		.log()
}


private fun part2() {
	var data = getLines(2021_09).digits().grid()//.log()

	val b = data.bounds
		.filter { it.getQuadNeighbours().filter { it in data.bounds }.all { x -> data[x] > data[it] } }

	val seen = msop()

	fun find(point: Point): Int {
		if (point in seen) return 0
		seen += point
		if (data[point] == 9) return 0
		return 1 + point.getQuadNeighbours()
			.filter { it in data.bounds }
			.filter { x -> data[x] > data[point] }
			.map { find(it) }
			.sum()
	}

	b.map { find(it) }.sortedDescending().take(3).product().log()
}


fun main() {
	println("Day  9: ")
	part1()
	part2()
}


private fun <T> T.log(): T = also { println(this) }