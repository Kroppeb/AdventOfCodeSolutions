@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d18

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
import java.util.ArrayDeque
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2017_18).map { it.split(' ') }

	var regs = mutableMapOf<String, Long>()
	var freq = 0L

	fun getVal(s: String): Long {
		s.getLong()?.let { return it }
		return regs.getOrDefault(s, 0L)
	}

	var i = 0
	while (i in data.indices) {
		val line = data[i++]
		when (line.first()) {
			"snd" -> freq = getVal(line[1])
			"set" -> regs[line[1]] = getVal(line[2])
			"add" -> regs[line[1]] = getVal(line[1]) + getVal(line[2])
			"mul" -> regs[line[1]] = getVal(line[1]) * getVal(line[2])
			"mod" -> regs[line[1]] = getVal(line[1]) % getVal(line[2])
			"rcv" -> if (getVal(line[1]) != 0L) {
				freq.log()
				return
			}

			"jgz" -> if (getVal(line[1]) > 0L) {
				i--
				i += getVal(line[2]).toInt()
			}
			else -> error(line)
		}
	}
}

class Prog(val data: List<List<String>>, val p: Long){
	var i = 0
	val queue = ArrayDeque<Long>()
	val regs = mutableMapOf<String, Long>("p" to p)
	lateinit var sendQueue : ArrayDeque<Long>
	var sendCount = 0L

	fun getVal(s: String): Long {
		s.getLong()?.let { return it }
		return regs.getOrDefault(s, 0L)
	}
	fun process() {
		while (i in data.indices) {
			val line = data[i++]
			when (line.first()) {
				"snd" -> {
					sendQueue.addLast(getVal(line[1]))
					sendCount++
				}
				"set" -> regs[line[1]] = getVal(line[2])
				"add" -> regs[line[1]] = getVal(line[1]) + getVal(line[2])
				"mul" -> regs[line[1]] = getVal(line[1]) * getVal(line[2])
				"mod" -> regs[line[1]] = getVal(line[1]) % getVal(line[2])
				"rcv" -> if (queue.isEmpty()) {
					i--
					return
				} else {
					regs[line[1]] = queue.removeFirst()
				}

				"jgz" -> if (getVal(line[1]) > 0L) {
					i--
					i += getVal(line[2]).toInt()
				}
				else -> error(line)
			}
		}
	}
}

private fun part2() {
	var data = getLines(2017_18).map { it.split(' ') }

	val p0 = Prog(data, 0L)
	val p1 = Prog(data, 1L)

	p0.sendQueue = p1.queue
	p1.sendQueue = p0.queue

	while(true) {
		p0.process()
		p1.process()

		if (p0.queue.isEmpty() && p1.queue.isEmpty()) {
			p1.sendCount.log()
			return
		}
	}
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }