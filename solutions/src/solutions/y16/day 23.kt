@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d23


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
	var data = getLines(2016_23).map{it.split(" ")}

	val regs = mutableMapOf("a" to 7, "b" to 0, "c" to 0, "d" to 0)
	fun value(s:String): Int {
		return s.toIntOrNull() ?: regs[s]!!
	}

	var state = data.indices.map{it to false}.toMap().toMutableMap()

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when(line[0] to state[i]) {
			"cpy" to false -> {regs[line[2]] = value(line[1]); i++}
			"cpy" to true -> {if(value(line[1]) != 0) i += value(line[2]) else i++}
			"inc" to false -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			"inc" to true -> {regs[line[1]] = regs[line[1]]!! -1; i++}
			"dec" to false -> {regs[line[1]] = regs[line[1]]!! -1; i++}
			"dec" to true -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			"jnz" to false -> {if(value(line[1]) != 0) i += value(line[2]) else i++}
			"jnz" to true -> {regs[line[2]] = value(line[1]); i++}
			"tgl" to false -> {state[value(line[1]) + i] = !(state[value(line[1]) + i]?:false); i++}
			"tgl" to true -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			else -> error(line to state[i] to regs)
		}
	}

	regs["a"].log()
}

private fun part2() {
	var data = getLines(2016_23).map{it.split(" ")}

	val regs = mutableMapOf("a" to 12, "b" to 0, "c" to 0, "d" to 0)
	fun value(s:String): Int {
		return s.toIntOrNull() ?: regs[s]!!
	}

	var state = data.indices.map{it to false}.toMap().toMutableMap()

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when(line[0] to state[i]) {
			"cpy" to false -> {regs[line[2]] = value(line[1]); i++}
			"cpy" to true -> {if(value(line[1]) != 0) i += value(line[2]) else i++}
			"inc" to false -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			"inc" to true -> {regs[line[1]] = regs[line[1]]!! -1; i++}
			"dec" to false -> {regs[line[1]] = regs[line[1]]!! -1; i++}
			"dec" to true -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			"jnz" to false -> {if(value(line[1]) != 0) i += value(line[2]) else i++}
			"jnz" to true -> {regs[line[2]] = value(line[1]); i++}
			"tgl" to false -> {state[value(line[1]) + i] = !(state[value(line[1]) + i]?:false); i++}
			"tgl" to true -> {regs[line[1]] = regs[line[1]]!! +1; i++}
			else -> error(line to state[i] to regs)
		}
	}

	regs["a"].log()
}


fun main() {
	println("Day 23: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }