@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d15

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
	var (genA, genB) = getLines(2017_15).long()
	var count = 0
	repeat(40_000_000) {
		genA *= 16807L
		genB *= 48271L

		genA %= 2147483647
		genB %= 2147483647

		if ((genA and 0xff_ff) == (genB and 0xff_ff)) {
			count++
		}
	}

	count.log()

}

private fun part2() {
	var (genA, genB) = getLines(2017_15).long()
	var count = 0
	repeat(5_000_000) {
		do {

			genA *= 16807L
			genA %= 2147483647
		} while (genA % 4 != 0L)
		do {
			genB *= 48271L

			genB %= 2147483647
		} while (genB % 8 != 0L)

		if ((genA and 0xff_ff) == (genB and 0xff_ff)) {
			count++
		}
	}

	count.log()
}


fun main() {
	println("Day 15: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }