@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d25

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
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


data class Action(val res: Boolean, val diff: Int, val next: Char)

private fun part1() {
	var data = getLines(2017_25).splitOnEmpty()

	var c = data.first().last().int()


	var set = mutableSetOf<Int>()

	val actions = data.drop(1).map { (a, u1, o11, o12, o13, u2, o21, o22, o23) ->
		a[a.length - 2] to (
				Action(
					o11.int() == 1, when (o12.split(" ").last()) {
						"right." -> -1
						"left." -> 1
						else -> error(o12)
					}, o13[o13.length - 2]
				) to
						Action(
							o21.int() == 1, when (o22.split(" ").last()) {
								"right." -> -1
								"left." -> 1
								else -> error(o22)
							}, o23[o23.length - 2]
						)
				)
	}.toMap()

	var state = 'A'
	var pointer = 0

	repeat(c) {
		val s = actions[state]!!
		val action = if (pointer in set) {
			s.second
		} else {
			s.first
		}

		if (action.res) {
			set += pointer
		} else {
			set -= pointer
		}

		pointer += action.diff
		state = action.next
	}

	set.size.log()

}

private fun part2() {


}


fun main() {
	println("Day 25: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }