@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d19

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.*
import helpers.*
import itertools.*
import kotlin.math.*


val xxxxx = Clock(6,3);


private fun part1() {
	var data = getLines(2018_19)
	var ipReg = data.first().int()

	var code = data.drop(1).map { it.split(" ") }.map { it[0] to it.drop(1).map { it.int() } }

	var ip = 0

	var regs = IntArray(6)

	while (true) {
		regs[ipReg] = ip

		if (ip !in code.indices) break

		var (op, args) = code[ip]
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
			"eqrr" -> if (regs[a] == regs[b]) 1 else 0
			else -> error("")
		}



		ip = regs[ipReg] + 1
	}

	regs[0].log()
}

private fun part2() {
	decompiled().log()
}


fun main() {
	println("Day 19: ")
	part1()
	part2()
}


fun decompiled(): Int {
	val pFactors = listOf(2, 2, 2, 2, 2, 3, 131, 839)

	var factors = setOf(1)

	for (i in pFactors) {
		factors += factors.map{it * i}
	}

	return factors.sum()
}

var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }