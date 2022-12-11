@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d10


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
	var data = getLines(2016_10).map{it.split(" ") to it.ints()}

	val bots = mutableMapOf<Int, MutableList<Int>>()

	val actions = mutableListOf<List<Int>>()

	for ((x,l) in data){
		if (l.size == 2) {
			bots.getOrPut(l[1]){ mutableListOf()} += l[0]
		} else {
			var (id, a,b) = l
			if (x[5] == "output") {
				a = -a - 1
			}
			if (x[10] == "output") {
				b = -b - 1
			}
			actions += l
		}
	}

	while (true) {
		for ((id, a,b) in actions) {
			if (bots.getOrPut(id){ mutableListOf()}.size == 2) {
				val l = bots[id]!!
				l.sort()
				val (ax, bx) = l
				l.clear()
				if (ax == 17 && bx == 61) {
					id.log()
					return
				}
				bots.getOrPut(a){ mutableListOf()} += ax
				bots.getOrPut(b){ mutableListOf()} += bx
			}
		}
	}


}

private fun part2() {
	var data = getLines(2016_10).map{it.split(" ") to it.ints()}

	val bots = mutableMapOf<Int, MutableList<Int>>()

	val actions = mutableListOf<List<Int>>()

	for ((x,l) in data){
		if (l.size == 2) {
			bots.getOrPut(l[1]){ mutableListOf()} += l[0]
		} else {
			var (id, a,b) = l
			if (x[5] == "output") {
				a = -a - 1
			}
			if (x[10] == "output") {
				b = -b - 1
			}
			actions += listOf(id, a,b,)
		}
	}

	var changes = true
	while (changes) {
		changes = false
		for ((id, a,b) in actions) {
			if (bots.getOrPut(id){ mutableListOf()}.size == 2) {
				changes = true
				val l = bots[id]!!
				l.sort()
				val (ax, bx) = l
				l.clear()

				bots.getOrPut(a){ mutableListOf()} += ax
				bots.getOrPut(b){ mutableListOf()} += bx
			}
		}

		if ((bots[-1]?.size ?: 0) >= 1 && bots[-2]?.size?:0 >= 1 && bots[-3]?.size?:0 >= 1) {
			(bots[-1]!![0] * bots[-2]!![0] * bots[-3]!![0]).log()
			return
		}
	}

	bots[-1].log()
	bots[-2].log()
	bots[-3].log()


}


fun main() {
	println("Day 10: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }