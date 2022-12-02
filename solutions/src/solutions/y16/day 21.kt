@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d21


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
import solutions.solutions.y19.d22.l
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_21)

	var pass = "abcdefgh".e().toMutableList()

	for (l in data) {
		val line = l.split(" ")
		val c = l.ints()
		when (line[0] to line[1]) {
			"swap" to "position" -> {
				val a = pass[c[0]]
				val b = pass[c[1]]
				pass[c[0]] = b
				pass[c[1]] = a
			}
			"swap" to "letter" -> {
				for (index in pass.indices) {
					when(pass[index]) {
						line[2][0] -> pass[index] = line[5][0]
						line[5][0] -> pass[index] = line[2][0]
					}
				}
			}
			"rotate" to "left" -> {
				pass = (pass.drop(c[0]) + pass.take(c[0])).toMutableList()
			}
			"rotate" to "right" -> {
				pass = (pass.takeLast(c[0]) + pass.dropLast(c[0])).toMutableList()
			}
			"rotate" to "based" -> {
				var i = pass.indexOf(line.last()[0])
				if (i >= 4) i++
				i++
				i %= pass.size
				pass = (pass.takeLast(i) + pass.dropLast(i)).toMutableList()
			}
			"reverse" to "positions" -> {
				pass.subList(c[0], c[1] + 1).reverse()
			}
			"move" to "position" -> {
				pass.add(c[1], pass.removeAt(c[0]))
			}
			else -> error(l)
		}
//		pass.join().log()
	}

	pass.join().log()
}

private fun part2() {

	var data = getLines(2016_21)

	for (origin in "fbgdceah".e().permutations()) {
		var pass = origin.toMutableList()

		for (l in data) {
			val line = l.split(" ")
			val c = l.ints()
			when (line[0] to line[1]) {
				"swap" to "position" -> {
					val a = pass[c[0]]
					val b = pass[c[1]]
					pass[c[0]] = b
					pass[c[1]] = a
				}

				"swap" to "letter" -> {
					for (index in pass.indices) {
						when (pass[index]) {
							line[2][0] -> pass[index] = line[5][0]
							line[5][0] -> pass[index] = line[2][0]
						}
					}
				}

				"rotate" to "left" -> {
					pass = (pass.drop(c[0]) + pass.take(c[0])).toMutableList()
				}

				"rotate" to "right" -> {
					pass = (pass.takeLast(c[0]) + pass.dropLast(c[0])).toMutableList()
				}

				"rotate" to "based" -> {
					var i = pass.indexOf(line.last()[0])
					if (i >= 4) i++
					i++
					i %= pass.size
					pass = (pass.takeLast(i) + pass.dropLast(i)).toMutableList()
				}

				"reverse" to "positions" -> {
					pass.subList(c[0], c[1] + 1).reverse()
				}

				"move" to "position" -> {
					pass.add(c[1], pass.removeAt(c[0]))
				}

				else -> error(l)
			}
		}
		if (pass.join() == "fbgdceah") {
			origin.join().log()
			return
		}
	}
}


fun main() {
	println("Day 21: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }