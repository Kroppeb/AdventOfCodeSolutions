@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d13


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
	var data = getLines(2016_13).int().first()

	fun isWall (p: Point): Boolean {
		val (x,y) = p
		val u = x*x + 3*x + 2*x*y + y + y*y + data
		return u.toString(2).count { it == '1' } % 2 == 1
	}

	val end = 31 toP 39
	bfs(1 toP 1, {it == end}) {
		it.getQuadNeighbours().filter{it.x >= 0 && it.y >=0}.filter { !isWall(it) }
	}.log()

}

private fun part2() {
	var data = getLines(2016_13).int().first()

	fun isWall (p: Point): Boolean {
		val (x,y) = p
		val u = x*x + 3*x + 2*x*y + y + y*y + data
		return u.toString(2).count { it == '1' } % 2 == 1
	}

	val all = mutableSetOf(1 toP 1)
	var cur = setOf(1 toP 1)

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