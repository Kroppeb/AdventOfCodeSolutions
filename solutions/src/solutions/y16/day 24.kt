@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d24


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
import solutions.solutions.y19.d20c.p
import solutions.solutions.y19.d22.l
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_24).e().grid()

	val poi = data.bounds.filter{data[it] != '.' && data[it] != '#' && data[it] != '0'}.toSet()

	val start = data.bounds.find{data[it] == '0'}!!

	bfs(start to poi, {(_, poi) -> poi.isEmpty()}) {(p, poi) ->
		p.getQuadNeighbours().filter{data[it] != '#'}.map{
			if (it in poi) {
				it to poi.toMutableSet().also{x -> x.remove(it)}
			} else {
				it to poi
			}
		}
	}.log()

}

private fun part2() {
	var data = getLines(2016_24).e().grid()

	val poi = data.bounds.filter{data[it] != '.' && data[it] != '#' && data[it] != '0'}.toSet()

	val start = data.bounds.find{data[it] == '0'}!!

	bfs(start to poi, {(p, poi) -> poi.isEmpty() && p == start}) {(p, poi) ->
		p.getQuadNeighbours().filter{data[it] != '#'}.map{
			if (it in poi) {
				it to poi.toMutableSet().also{x -> x.remove(it)}
			} else {
				it to poi
			}
		}
	}.log()
}


fun main() {
	println("Day 24: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }