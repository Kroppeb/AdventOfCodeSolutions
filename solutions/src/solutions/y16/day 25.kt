@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d25


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
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_25).map{it.split(" ")}

	loop{
		val x = it
		if (x % 1000 == 0){
			x.log()
		}
		if(extracted(x, data)) {
			x.log()
			return
		}
	}


}

private fun extracted(
	inp: Int,
	data: List<List<String>>
):Boolean {
	val regs = mutableMapOf("a" to inp, "b" to 0, "c" to 0, "d" to 0)
	fun value(s: String): Int {
		return s.toIntOrNull() ?: regs[s]!!
	}

	var outs = 0
	var start = 0

	var i = 0
	while (i in data.indices) {
		val line = data[i]
		when (line[0]) {
			"cpy" -> {
				regs[line[2]] = value(line[1]); i++
			}

			"inc" -> {
				regs[line[1]] = regs[line[1]]!! + 1; i++
			}

			"dec" -> {
				regs[line[1]] = regs[line[1]]!! - 1; i++
			}

			"jnz" -> {
				if (value(line[1]) != 0) i += value(line[2]) else i++
			}

			"out" -> {
				val v = value((line[1]))
				if (v != 0 && v != 1) return false
				if (outs == 0) {
					start = v
				} else if ((outs + start) % 2 != v) {
					return false
				}
				outs ++
				if (outs > 10000) {
					return true
				}
				i++; // <--- I had forgotten this (see decompiled code at the bottom)
			}
			else -> error(line)
		}
	}

	return false
}

private fun part2() {

}

// I had issues and decompiled the code instead
// static void x(int x) {
//		int a = x;
//		int b = 0;
//		int c = 0;
//		int d = 0;
//
//
//		d = a + 0b100111101101;
//		// target x
//		x = 0b101010101010 - 0b100111101101 == 189;
//		while (true) {
//			a = d;
//			while (a != 0) {
//				System.out.println(a % 2);
//
//				a /= 2;
//			}
//		}
//	}


fun main() {
	println("Day 25: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }