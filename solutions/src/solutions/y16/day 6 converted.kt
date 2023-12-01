@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d06c


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_06).e().transpose().map{it.countEachI().maxByValue()}.join().log()

}

private fun part2() {
	var data = getLines(2016_06).e().transpose().map{it.countEachI().minByValue()}.join().log()
}


fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }