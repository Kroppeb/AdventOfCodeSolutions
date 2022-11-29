@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d16

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.bfs
import grid.*
import helpers.*
import itertools.*
import solutions.solutions.y19.d20c.p
import java.util.Comparator.comparing
import java.util.stream.Collectors.toSet
import kotlin.math.*


private val xxxxx = Clock(3, 6);


private fun part1() {
	var data = getLines(2018_16)
	

}

private fun part2() {

}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }