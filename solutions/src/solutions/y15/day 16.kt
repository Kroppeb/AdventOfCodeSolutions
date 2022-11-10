@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d16

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import grid.mutableGrid
import helpers.*
import itertools.*
import kotlinx.coroutines.NonCancellable.children
import kotlin.math.*


val xxxxx = Clock(6, 3);

/*

*/

val target = mapOf(
	"children" to 3,
	"cats" to 7,
	"samoyeds" to 2,
	"pomeranians" to 3,
	"akitas" to 0,
	"vizslas" to 0,
	"goldfish" to 5,
	"trees" to 3,
	"cars" to 2,
	"perfumes" to 1,
)


private fun part1() {
	val data = getLines(2015_16).map {
		it.split(" ").drop(2).chunked(2).map { (a, n) ->
			a.dropLast(1) to n.int()
		}
	}.withIndex()

	val result = data.first { it.value.all { (k, v) -> target[k] == v } }.index + 1
	result.log()
}

private fun part2() {
	val data = getLines(2015_16).map {
		it.split(" ").drop(2).chunked(2).map { (a, n) ->
			a.dropLast(1) to n.int()
		}
	}.withIndex()

	val result = data.first {
		it.value.all {

				(k, v) ->
			when (k) {
				"cats" -> v > target[k]!!
				"trees" -> v > target[k]!!
				"pomeranians" -> v < target[k]!!
				"goldfish" -> v < target[k]!!
				else -> target[k] == v
			}
		}
	}.index + 1
	result.log()

}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }