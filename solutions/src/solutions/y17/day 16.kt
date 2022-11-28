@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d16

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


private fun part1() {
	var data = getLines(2017_16).first().split(',')

	var q = ('a'..'p').toList()

	for (d in data) {
		when (d.first()) {
			's' -> {
				val s = d.int()
				q = q.takeLast(s) + q.dropLast(s)
			}

			'x' -> {
				val (a, b) = d.ints()
				q = q.toMutableList().also {
					it[a].also { x ->
						it[a] = it[b]
						it[b] = x
					}
				}
			}

			'p' -> {
				val (_, a, _, b) = d.e()

				q = q.map {
					when (it) {
						a -> b
						b -> a
						else -> it
					}
				}
			}
		}
	}

	q.joinToString(separator = "").log()
}

private fun part2() {
	var data = getLines(2017_16).first().split(',')

	var q = ('a'..'p').toList()

	var id = 0
	val seen = mutableMapOf<String, Int>()
	while (true) {
		for (d in data) {
			when (d.first()) {
				's' -> {
					val s = d.int()
					q = q.takeLast(s) + q.dropLast(s)
				}

				'x' -> {
					val (a, b) = d.ints()
					q = q.toMutableList().also {
						it[a].also { x ->
							it[a] = it[b]
							it[b] = x
						}
					}
				}

				'p' -> {
					val (_, a, _, b) = d.e()

					q = q.map {
						when (it) {
							a -> b
							b -> a
							else -> it
						}
					}
				}
			}
		}

		val ss = q.joinToString(separator = "")
		id++

		if (ss in seen) {
			val diff = id - seen[ss]!!
			val oo = (1_000_000_000 - seen[ss]!!) % diff
			seen.entries.find{it.value == seen[ss]!! + oo}!!.key.log()
			return
		}

		seen[ss] = id

	}

}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }