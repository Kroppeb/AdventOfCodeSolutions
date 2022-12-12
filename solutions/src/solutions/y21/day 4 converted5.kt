@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d04c5

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grids

private val xxxxx = Clock(6, 3);

private fun part1() {
	val data = getLines(2021_04).splitOnEmpty()

	data.first().single().getInts().scan(soi()) { acc, i -> acc + i }.firstNotNullOf { seen ->
		data.drop(1).ints().grids().firstOrNull { grid -> grid.rowsCols().any { it allIn seen } }
			?.allItems()?.notIn(seen)?.sum()?.times(seen.last())
	} log 1

}

private fun part2() {
	val data = getLines(2021_04).splitOnEmpty()

	data.first().single().getInts().scan(soi()) { acc, i -> acc + i }
		.map { seen -> data.drop(1).ints().grids().filter { grid -> grid.rowsCols().any { it allIn seen } } to seen }
		.zipWithNext()
		.filter { (a, b) -> b.first.size == data.size - 1 && a.first.size != data.size - 1 }
		.map { (a, b) -> b.first.notIn(a.first).single() to b.second }
		.map { (grid, seen) -> grid.allItems().notIn(seen).sum() * seen.last() }
		.first() log 2

	data.first().single().getInts().scan(soi()) { acc, i -> acc + i }
		.asSequence()
		.map { seen -> data.drop(1).ints().grids().filter { grid -> grid.rowsCols().any { it allIn seen } } to seen }
		.zipWithNext()
		.filter { (_, b) -> b.first.size == data.size - 1 } // we can drop the second check if we pull lazily
		.map { (a, b) -> b.first.notIn(a.first).single() to b.second }
		.map { (grid, seen) -> grid.allItems().notIn(seen).sum() * seen.last() }
		.first() log 2
}

fun main() {
	println("Day 4: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
private infix fun <T> T.log(_ignored: Any?): T = this.log()