@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d24


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
	var data = getLines(2015_24).int().reversed()
	require(data.areDistinct())
	val w = data.sum() / 3
	val (a,b,c,d,e) = data.chunked(data.size / 5 + 1)
	var dd = data.toSet()

	val ax = a.powerSet().filter{it.sum() <= w}.also{it.size.log()}
	val bx = b.powerSet().filter{it.sum() <= w}.flatMap { ax.map{x -> x + it} }.filter { it.sum() <= w }.also{it.size.log()}
	val cx = c.powerSet().filter{it.sum() <= w}.flatMap { bx.map{x -> x + it} }.filter { it.sum() <= w }.also{it.size.log()}
	val dx = d.powerSet().filter{it.sum() <= w}.flatMap { cx.map{x -> x + it} }.filter { it.sum() <= w }.also{it.size.log()}
	val ex = e.powerSet().filter{it.sum() <= w}.flatMap { dx.map{x -> x + it} }.filter { it.sum() == w }.also{it.size.log()}

	val x = ex.sortedBy { it.product() }.sortedBy { it.size }.asSequence()
		.filter {xx -> dd.toMutableSet().also{it.removeAll(xx)}.let{
			val l = it.toList()
			bfs(0 to 0, {it.first == w}){(c,i) ->
				if(i > l.size || c > w) {
					emptyList()
				} else {
					listOf(c to i+1, c+l[i] to i+1)
				}
			}.first != null
		} }.first().product().log()
}

private fun part2() {
	var data = getLines(2015_24).int().reversed()
	require(data.areDistinct())
	val w = data.sum() / 4
	val (a,b,c,d,e) = data.chunked(data.size / 5 + 1)
	var dd = data.toSet()

	val ax = a.powerSet().filter{it.sum() <= w}.also{it.size.log()}
	val bx = b.powerSet().filter{it.sum() <= w}.flatMap { ax.map{x -> x + it} }.filter { it.sum() <= w }.also{it.size.log()}
	val cx = c.powerSet().filter{it.sum() <= w}.flatMap { bx.map{x -> x + it} }.filter { it.sum() <= w }.also{it.size.log()}
	val dx = d.powerSet().filter{it.sum() <= w}.flatMap { cx.map{x -> x + it} }.filter { it.sum() <= w }.also{it.size.log()}
	val ex = e.powerSet().filter{it.sum() <= w}.flatMap { dx.map{x -> x + it} }.filter { it.sum() == w }.also{it.size.log()}

	val x = ex.sortedBy { it.product() }.sortedBy { it.size }.asSequence()
		.filter {xx -> dd.toMutableSet().also{it.removeAll(xx)}.let{
			val l = it.toList()
			bfs(listOf(0,0,0,0), {it[0] == w && it[1] == w}){(cA, cB, cC, i) ->
				if(i > l.size || cA > w || cB > w || cC > w) {
					emptyList()
				} else {
					listOf(listOf(cA, cB, cC+l[i], i+1), listOf(cA+l[i], cB, cC, i+1), listOf(cA, cB + l[i], cC, i+1))
				}
			}.first != null
		} }.first().product().log()
}

fun main() {
	println("Day 24: ")
	// part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }