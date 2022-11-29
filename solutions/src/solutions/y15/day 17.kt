@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d17

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
import solutions.y15.d16.target
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	val data = getLines(2015_17).int()

	var ways = moii(150 to 1)

	for (d in data){
		val n = ways.toMutableMap()
		for ((k, v) in ways){
			if (k >= d)
				n[k - d] = n.getOrDefault(k - d, 0) + v
		}
		ways = n
	}

	ways[0].log()
}

private fun part2() {
	val data = getLines(2015_17).int()

	var ways = mutableMapOf(150 to mutableMapOf(0 to 1))

	for (d in data){
		val n = ways.mapValues { (k,v) -> v.toMutableMap() }.toMutableMap()
		for ((k, v) in ways){
			if (k >= d) {
				for ((i, j) in v){
					val m = n.getOrPut(k - d, ::mutableMapOf)
					m[i + 1] = m.getOrDefault(i + 1, 0) + j
				}
			}
		}
		ways = n
	}

	ways[0]!!.entries.log().minBy { it.key }!!.log()

}


fun main() {
	println("Day 17: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }