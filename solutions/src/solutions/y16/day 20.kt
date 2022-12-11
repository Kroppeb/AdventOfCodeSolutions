@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d20


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
	var data = getLines(2016_20).posLongs().map{(a,b) -> a..b}

	data.map{l->l.last + 1}.sorted().forEach{
		if (data.none{l -> it in l}){
			it.log()
			return
		}
	}


}

private fun part2() {
	var data = getLines(2016_20).posLongs().map{(a,b) -> a..b}

	data = data.sortedBy { it.first }.log()

	var cur = data[0]
	var red = mutableListOf<LongProgression>()

	for (l in data){
		if (l.first <= cur.last + 1) {
			cur = cur.first..max(cur.last, l.last)
		} else {
			red += cur
			cur = l
		}
	}

	red += cur
	red.log()

	(0x1_00_00_00_00 - red.sumOf { it.last - it.first + 1 }).log()
}


fun main() {
	println("Day 20: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }