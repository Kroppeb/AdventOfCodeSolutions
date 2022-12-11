@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d23

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.ArrayDeque
import kotlin.math.*


private val xxxxx = Clock(6, 3);


class Prog(val data: List<List<String>>, val p: Long) {
	var i = 0
	val queue = ArrayDeque<Long>()
	val regs = mutableMapOf<String, Long>("p" to p)
	lateinit var sendQueue: ArrayDeque<Long>
	var sendCount = 0L

	fun getVal(s: String): Long {
		s.getLong()?.let { return it }
		return regs.getOrDefault(s, 0L)
	}

	fun process() {
		var count = 0
		while (i in data.indices) {
			val line = data[i++]
			when (line.first()) {
				"snd" -> {
					sendQueue.addLast(getVal(line[1]))
					sendCount++
				}

				"set" -> regs[line[1]] = getVal(line[2])
				"add" -> regs[line[1]] = getVal(line[1]) + getVal(line[2])
				"sub" -> regs[line[1]] = getVal(line[1]) - getVal(line[2])
				"mul" -> regs[line[1]] = getVal(line[1]) * getVal(line[2]).also { count++ }
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

				"jnz" -> if (getVal(line[1]) != 0L) {
					i--
					i += getVal(line[2]).toInt()
				}

				else -> error(line)
			}
		}
		count.log()
	}
}

private fun part1() {
	var data = getLines(2017_23).map { it.split(" ") }

	val p = Prog(data, 0)
	p.process()

}

private fun part2() {
	clean().log()

}

fun clean() : Int {
	return (108400L..125400L step 17).count{!isPrime(it)}
}

fun isPrime(it: Long): Boolean {
	if (it % 2 == 0L) return false
	var x = 3
	while (x * x < it) {
		if (it % x == 0L) {
			return false
		}
		x+=2
	}
	return true
}

fun decompile(): Long {
	var a = 1L
	var b = 0L
	var c = 0L
	var d = 0L
	var e = 0L
	var f = 0L
	var g = 0L
	var h = 0L

	b = 108400L
	c = 125400L

	do {
		f = 1
		d = 2
		while (d != b) {
			e = 2
			while (e != b){
				if (d * e == b) {
					f = 0
				}
				e++
			}
			d++
		}

		if (f == 0L) {
			h++
		}
		if (b == c) {
			return h;
		}
		b += 17
	} while (true)
}


fun main() {
	println("Day 23: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }