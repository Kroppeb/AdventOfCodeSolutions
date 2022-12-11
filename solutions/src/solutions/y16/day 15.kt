@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d15


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
	var data = getLines(2016_15).ints()

	loop{
		if (data.all{(a,b,_,c) -> (a + it + c) % b == 0}){
			it.log()
			return
		}
	}
}

private fun part2() {
	var data = getLines(2016_15).ints()
	data += listOf(listOf(data.size + 1,11,0,0))

	loop{
		if (data.all{(a,b,_,c) -> (a + it + c) % b == 0}){
			it.log()
			return
		}
	}
}

fun main() {
	println("Day 15: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }