@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d13


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toPI


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_13).int().first()

	fun isWall (p: PointI): Boolean {
		val (x,y) = p
		val u = x*x + 3*x + 2*x*y + y + y*y + data
		return u.toString(2).count { it == '1' } % 2 == 1
	}

	val end = 31 toPI 39
	bfs(1 toPI 1, {it == end}) {
		it.getQuadNeighbours().filter{it.x >= 0 && it.y >=0}.filter { !isWall(it) }
	}.log()

}

private fun part2() {
	var data = getLines(2016_13).int().first()

	fun isWall (p: PointI): Boolean {
		val (x,y) = p
		val u = x*x + 3*x + 2*x*y + y + y*y + data
		return u.toString(2).count { it == '1' } % 2 == 1
	}

	val all = mutableSetOf(1 toPI 1)
	var cur = setOf(1 toPI 1)

	repeat(50) {
		cur = cur.flatMap{it.getQuadNeighbours().filter{it.x >= 0 && it.y >=0}.filter { !isWall(it) }}.toSet()
		all += cur
	}

	all.size.log()

}


fun main() {
	println("Day 13: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }