@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d08


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


private val xxxxx = Clock(3, 6);

private fun part1() {
	var data = getLines(2016_08).map{it.split(" ") to it.ints()}

	var grid = (0..5).map{(0..49).map{false}}.grid()

	for ((line, va) in data){
		when{
			line[0] == "rect" -> {
				val (x,y) = va
				grid = grid.mapIndexed { point, b -> b || point.x < x && point.y < y }.grid()
			}
			line[1] == "column" -> {
				val (id, amount) = va
				grid = grid.mapIndexed { point, b -> if (point.x == id) grid[(point + Clock.down * 5 * amount) % grid.bounds] else b }.grid()
			}
			line[1] == "row" -> {
				val (id, amount) = va
				grid = grid.mapIndexed { point, b -> if (point.y == id) grid[(point + Clock.right * 49 * amount) % grid.bounds] else b }.grid()
			}
		}
	}

	grid.allItems().count { it }.log()

}

private fun part2() {
	var data = getLines(2016_08).map{it.split(" ") to it.ints()}

	var grid = (0..5).map{(0..49).map{false}}.grid()

	for ((line, va) in data){
		when{
			line[0] == "rect" -> {
				val (x,y) = va
				grid = grid.mapIndexed { point, b -> b || point.x < x && point.y < y }.grid()
			}
			line[1] == "column" -> {
				val (id, amount) = va
				grid = grid.mapIndexed { point, b -> if (point.x == id) grid[(point + Clock.down * 5 * amount) % grid.bounds] else b }.grid()
			}
			line[1] == "row" -> {
				val (id, amount) = va
				grid = grid.mapIndexed { point, b -> if (point.y == id) grid[(point + Clock.right * 49 * amount) % grid.bounds] else b }.grid()
			}
		}
	}

	println(grid.map{if(it) '#' else '.'})
}


fun main() {
	println("Day 8: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }