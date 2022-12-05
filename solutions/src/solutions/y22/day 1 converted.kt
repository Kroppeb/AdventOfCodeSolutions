@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d01c


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
	getLines(2022_01).splitOnEmpty().maxOf{it.int().sum()}.log()
	getLines(2022_01).splitOnEmpty().int().maxOf{it.sum()}.log()
}

private fun part2() {
	getLines(2022_01).splitOnEmpty().maxOf(3){it.int().sum()}.sum().log()
	getLines(2022_01).splitOnEmpty().int().maxOf(3){it.sum()}.sum().log()
}


fun main() {
	println("Day 1: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }