@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d23


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
	var data = getLines(2015_23).map { it.split(" ") }

	var reg = mutableMapOf("a" to 0L, "b" to 0L)

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when (line.first()) {
			"hlf" -> {
				reg[line[1]] = reg[line[1]]!! / 2; i++
			}

			"tpl" -> {
				reg[line[1]] = reg[line[1]]!! * 3; i++
			}

			"inc" -> {
				reg[line[1]] = reg[line[1]]!! + 1; i++
			}

			"jmp" -> i += line[1].int()
			"jie" -> if (reg[line[1].dropLast(1)]!! % 2 == 0L) i += line[2].int() else i++
			"jio" -> if (reg[line[1].dropLast(1)]!! == 1L) i += line[2].int() else i++
		}
	}
	reg["b"].log()

}

private fun part2() {
	var data = getLines(2015_23).map { it.split(" ") }

	var reg = mutableMapOf("a" to 1L, "b" to 0L)

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when (line.first()) {
			"hlf" -> {
				reg[line[1]] = reg[line[1]]!! / 2; i++
			}

			"tpl" -> {
				reg[line[1]] = reg[line[1]]!! * 3; i++
			}

			"inc" -> {
				reg[line[1]] = reg[line[1]]!! + 1; i++
			}

			"jmp" -> i += line[1].int()
			"jie" -> if (reg[line[1].dropLast(1)]!! % 2 == 0L) i += line[2].int() else i++
			"jio" -> if (reg[line[1].dropLast(1)]!! == 1L) i += line[2].int() else i++
		}
	}
	reg["b"].log()

}

fun main() {
	println("Day 23: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }