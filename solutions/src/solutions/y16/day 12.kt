@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d12


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
import solutions.solutions.y19.d22.pow
import java.security.MessageDigest
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_12).map{it.split(" ")}

	val regs = mutableMapOf("a" to 0, "b" to 0, "c" to 0, "d" to 0)
	fun value(s:String): Int {
		return s.toIntOrNull() ?: regs[s]!!
	}

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when(line[0]) {
			"cpy" -> {regs[line[2]] = value(line[1]); i++}
			"inc" -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			"dec" -> {regs[line[1]] = regs[line[1]]!! -1; i++}
			"jnz" -> {if(value(line[1]) != 0) i += value(line[2]) else i++}
			else -> error(line)
		}
	}

	regs["a"].log()
}

private fun part2() {
	var data = getLines(2016_12).map{it.split(" ")}

	val regs = mutableMapOf("a" to 0, "b" to 0, "c" to 1, "d" to 0)
	fun value(s:String): Int {
		return s.toIntOrNull() ?: regs[s]!!
	}

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when(line[0]) {
			"cpy" -> {regs[line[2]] = value(line[1]); i++}
			"inc" -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			"dec" -> {regs[line[1]] = regs[line[1]]!! -1; i++}
			"jnz" -> {if(value(line[1]) != 0) i += value(line[2]) else i++}
			else -> error(line)
		}
	}

	regs["a"].log()
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }