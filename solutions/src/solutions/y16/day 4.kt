@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d04


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
	var data = getLines(2016_04)
		.map { it.split('-') }
		.map { it.dropLast(1).e().flatten() to it.last().split('[').let { (a, b) -> a.int() to b.dropLast(1).e() } }

	data.filter{(a, x) ->
		val p = a.groupBy { it }.toList().sortedBy { it.first }.sortedBy { -it.second.size }.map{it.first}.take(5)
		p == x.second
	} .sumBy{it.second.first}.log()


}

private fun part2() {
	var data = getLines(2016_04)
		.map { it.split('-') }
		.map { it.dropLast(1).e().flatten() to it.last().split('[').let { (a, b) -> a.int() to b.dropLast(1).e() } }

	data.filter{(a, x) ->
		val p = a.groupBy { it }.toList().sortedBy { it.first }.sortedBy { -it.second.size }.map{it.first}.take(5)
		p == x.second
	}.map{it.first to it.second.first}
		.map{(name, id) -> name.map{ ((it.toLowerCase() - 'a' + id) % 26 + 'a'.toInt()).toChar()} to id}
		.also{it.map{(name, id) -> println("${name.joinToString(separator = "")}: $id")}}
		.find{(name, _) -> name.joinToString(separator = "").contains("northpoleobjects")}.log()


}

fun main() {
	println("Day 4: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }