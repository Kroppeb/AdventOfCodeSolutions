@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d21

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.*
import grid.*
import helpers.*
import itertools.*
import solutions.y18.d19.log
import kotlin.math.*


val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_21)
	var ipReg = data.first().int()

	var code = data.drop(1).map { it.split(" ") }.map { it[0] to it.drop(1).map { it.int() } }

	var ip = 0

	var regs = mutableListOf(0, 0, 0, 0, 0, 0)
	regs[0] = 3

	while (true) {
		regs[ipReg] = ip

		if (ip !in code.indices) break
		regs.log()
		var (op, args) = code[ip].log()
		val (a, b, c) = args
		regs[c] = when (op) {
			"addr" -> regs[a] + regs[b]
			"addi" -> regs[a] + b
			"mulr" -> regs[a] * regs[b]
			"muli" -> regs[a] * b
			"banr" -> regs[a] and regs[b]
			"bani" -> regs[a] and b
			"borr" -> regs[a] or regs[b]
			"bori" -> regs[a] or b
			"setr" -> regs[a]
			"seti" -> a
			"gtir" -> if (a > regs[b]) 1 else 0
			"gtri" -> if (regs[a] > b) 1 else 0
			"gtrr" -> if (regs[a] > regs[b]) 1 else 0
			"eqir" -> if (a == regs[b]) 1 else 0
			"eqri" -> if (regs[a] == b) 1 else 0
			"eqrr" -> {
				regs[a].log()
				return
			}

			else -> error("")
		}



		ip = regs[ipReg] + 1
	}
}

private fun part2() {
	val seen = mloi()
	decompile {
		if (it in seen) {
			seen.last().log()
			return
		} else {
			seen.add(it)
		}
	}

}


fun main() {
	println("Day 21: ")
	part1()
	part2()
}

inline fun decompile(call: (Int) -> Unit) {
	var x3 = 0
	var x4: Int

	l6@ while (true) {
		x4 = x3 or 0x01_00_00
		x3 = 2176960
		while (true) {
			x3 += x4 and 0xFF
			x3 = x3 and 0xFF_FFFF
			x3 *= 65899
			x3 = x3 and 0xFF_FFFF
			if (256 > x4) {
				call(x3)

				continue@l6
			}

			x4 /= 0x1_00
		}
	}
}

var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }