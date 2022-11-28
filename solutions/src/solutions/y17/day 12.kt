@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d12

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
	var data = getLines(2017_12).ints()

	val uf = UnionFind()

	for (d in data) {
		for (x in d.drop(1)) {
			uf.join(d.first(), x)
		}
	}

	data.count{uf.areJoined(0, it.first())}.log()

}

private fun part2() {
	var data = getLines(2017_12).ints()

	val uf = UnionFind()

	for (d in data) {
		for (x in d.drop(1)) {
			uf.join(d.first(), x)
		}
	}

	val r = mutableListOf<Int>()

	for (d in data) {
		if (r.none{uf.areJoined(it, d.first())}) {
			r += d.first()
		}
	}

	r.size.log()
	uf.parents.values.count { it.parent == null }.log()
	data.map{uf.getRoot(it.first())}.toSet().size.log()
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }