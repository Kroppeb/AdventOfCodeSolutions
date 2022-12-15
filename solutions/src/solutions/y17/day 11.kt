@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d11

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toPI
import kotlin.math.*


private val xxxxx = Clock(6, 3);

// I solved these without considering cases where the child is further east-west than north-south
// I'm kinda annoyed it didn't cause my answer to be wrong
private fun part1() {
	var data = getLines(2017_11).first().split(',')

	var pos = 0 toPI 0

	for (m in data) {
		when(m) {
			"n" -> pos = pos.north.north
			"s" -> pos = pos.south.south
			"ne" -> pos = pos.north.east
			"nw" -> pos = pos.north.west
			"sw" -> pos = pos.south.west
			"se" -> pos = pos.south.east
			else -> error(m)
		}
	}

	((abs(pos.x) + abs(pos.y)) / 2) .log()
	((max(abs(pos.y), abs(pos.x)) + abs(pos.y)) / 2) .log()

}

private fun part2() {
	var data = getLines(2017_11).first().split(',')

	var pos = 0 toPI 0

	var ma = 0
	var ma2 = 0
	for (m in data) {
		when(m) {
			"n" -> pos = pos.north.north
			"s" -> pos = pos.south.south
			"ne" -> pos = pos.north.east
			"nw" -> pos = pos.north.west
			"sw" -> pos = pos.south.west
			"se" -> pos = pos.south.east
			else -> error(m)
		}
		ma = max(ma, ((abs(pos.x) + abs(pos.y)) / 2) )
		ma2 = max(ma2, ((max(abs(pos.y), abs(pos.x)) + abs(pos.y)) / 2)  )
	}
	ma.log()
	ma2.log()

}


fun main() {
	println("Day 11: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }