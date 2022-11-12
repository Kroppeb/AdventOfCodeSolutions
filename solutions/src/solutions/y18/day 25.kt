@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d25

/*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import collections.UnionFind
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_25).ints().map{(a,b,c,d) -> a toP b to (c toP d)}

	val union = UnionFind()


	for ((a,b) in data.pairWise()){
		if(a.first.manDistTo(b.first) + a.second.manDistTo(b.second) <= 3) {
			union.join(a,b)
		}
	}

	val s = msoa()

	for(d in data) {
		if (s.none{union.areJoined(d,it)}) {
			s.add(d)
		}
	}

	s.size.log()

}

private fun part2() {

}


fun main() {
	println("Day 25: ")
	part1()
	part2()
}


var _logIndex = 0
fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }