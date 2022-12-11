@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d16


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
	var data = getLines(2016_16).first().e().map{it == '1'}

	while(data.size < 272) {
		data = data + listOf(false) + data.reversed().map{!it}
	}

	data = data.take(272)

	while (data.size % 2 == 0) {
		data = data.chunked(2){(a,b) -> a == b}
	}

	data.map{if(it) '1' else '0'}.join().log()
}

private fun part2() {
	var data = getLines(2016_16).first().e().map{it == '1'}

	while(data.size < 35651584) {
		data = data + listOf(false) + data.reversed().map{!it}
	}

	data = data.take(35651584)

	while (data.size % 2 == 0) {
		data = data.chunked(2){(a,b) -> a == b}
	}

	data.map{if(it) '1' else '0'}.join().log()
}


fun main() {
	println("Day 16: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }