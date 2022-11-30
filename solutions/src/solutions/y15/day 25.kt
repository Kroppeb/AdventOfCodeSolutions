@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d25


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
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var (row, col) = getLines(2015_25).ints().first()

	row--
	col--
	row += col

	row *= row + 1
	row /=2

	val id = row + col
	println(id + 1)

	val res = 20151125L * (252533L).powMod(id, 33554393L) % 33554393L
	res.log()

}

private fun part2() {

}

fun main() {
	println("Day 25: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }