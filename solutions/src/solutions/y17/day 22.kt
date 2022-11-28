@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d22

/*
import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import solutions.solutions.y19.d20c.p
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2017_22).e().grid()

	var inf = data.mapIndexed { point, c -> point to c }.flatten().filter{(p,c) -> c == '#'}.map{it.first}.toMutableSet()

	var pos = (data.bounds.lower + data.bounds.higher) / 2
	var dir = -1 toP 0

	var count = 0
	repeat(10000) {
		if (pos in inf) {
			dir = dir.rotateClock()
			inf.remove(pos)
		} else {
			dir = dir.rotateAntiClock()
			count++
			inf.add(pos)
		}

		pos += dir
	}

	count.log()


}

private fun part2() {
	var data = getLines(2017_22).e().grid()

	var inf = data.mapIndexed { point, c -> point to c }.flatten().filter{(p,c) -> c == '#'}.map{it.first to 2}.toMap().toMutableMap() // TODO: fixme

	var pos = (data.bounds.lower + data.bounds.higher) / 2
	var dir = -1 toP 0

	var count = 0
	repeat(10000000) {
		when(inf[pos] ?: 0) {
			0 -> {
				dir = dir.rotateAntiClock()
				inf[pos] = 1
			}
			1 -> {
				inf[pos] = 2
				count++
			}
			2 -> {
				dir = dir.rotateClock()
				inf[pos] = 3
			}
			3 -> {
				dir = -dir
				inf[pos] = 0
			}
		}

		pos += dir
	}

	count.log()

}


fun main() {
	println("Day 22: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }