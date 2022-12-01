@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


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


val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2022_02)
}


fun main() {
	println("Day 2: ")
	part1()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }