@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d06c


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
import java.security.MessageDigest
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_06).e().transpose().map{it.countEach().maxByValue()}.join().log()

}

private fun part2() {
	var data = getLines(2016_06).e().transpose().map{it.countEach().minByValue()}.join().log()
}


fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }