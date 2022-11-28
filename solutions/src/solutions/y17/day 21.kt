@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d21

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
	var data = getLines(2017_21).map{it.split(" => ").map{it.split("/").e()}.let{(a,b) -> a to b}}.toMap()

	var board = ".#.\n..#\n###".lines().e()

	fun find(x: List<List<Char>>): List<List<Char>> {
		data[x]?.let{return it}
		data[x.reversed()]?.let{return it}
		data[x.map{it.reversed() }]?.let{return it}
		data[x.reversed().map{it.reversed() }]?.let{return it}
		data[x.transpose()]?.let{return it}
		data[x.transpose().reversed()]?.let{return it}
		data[x.transpose().map{it.reversed() }]?.let{return it}
		data[x.transpose().reversed().map{it.reversed() }]?.let{return it}
		error(x)
	}

	repeat(5) {
		if (board.size % 2 == 0) {
			val p = board.chunked(2).map{it.transpose().chunked(2).map{find(it)}}.map{it.transpose().map{it.flatten()}}.flatten()
			// p.log()
			board = p
		} else if(board.size % 3 == 0) {
			val p = board.chunked(3).map{it.transpose().chunked(3).map{find(it)}}.map{it.transpose().map{it.flatten()}}.flatten()
			// p.log()
			board = p
		}
	}
	board.sumBy { it.count { it == '#' } }.log()
}

private fun part2() {
	var data = getLines(2017_21).map{it.split(" => ").map{it.split("/").e()}.let{(a,b) -> a to b}}.toMap()

	var board = ".#.\n..#\n###".lines().e()

	fun find(x: List<List<Char>>): List<List<Char>> {
		data[x]?.let{return it}
		data[x.reversed()]?.let{return it}
		data[x.map{it.reversed() }]?.let{return it}
		data[x.reversed().map{it.reversed() }]?.let{return it}
		data[x.transpose()]?.let{return it}
		data[x.transpose().reversed()]?.let{return it}
		data[x.transpose().map{it.reversed() }]?.let{return it}
		data[x.transpose().reversed().map{it.reversed() }]?.let{return it}
		error(x)
	}

	repeat(18) {
		if (board.size % 2 == 0) {
			val p = board.chunked(2).map{it.transpose().chunked(2).map{find(it)}}.map{it.transpose().map{it.flatten()}}.flatten()
			// p.log()
			board = p
		} else if(board.size % 3 == 0) {
			val p = board.chunked(3).map{it.transpose().chunked(3).map{find(it)}}.map{it.transpose().map{it.flatten()}}.flatten()
			// p.log()
			board = p
		}
	}
	board.sumBy { it.count { it == '#' } }.log()

}


fun main() {
	println("Day 21: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }