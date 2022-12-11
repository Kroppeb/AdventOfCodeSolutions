@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d07


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
	var data = getLines(2016_07).filter{
		it.e().windowed(4).any { (a,b,c,d) -> a== d && b == c&& a !=b } &&
				!Regex("\\[[^\\]]+]").findAll(it).any {x -> x.value.e().windowed(4).any { (a,b,c,d) -> a== d && b == c&& a !=b } }
	}.count().log()

}

private fun part2() {
	var data = getLines(2016_07).filter{
		it.replace(Regex("\\[[^\\]]+]"), "_**&@").e().windowed(3).any { (a,b,c) -> a== c && a !=b &&
				Regex("\\[[^\\]]+]").findAll(it)
					.any {x -> x.value.e().windowed(3).any { (x,y,z) -> b == x && b == z && a == y } }}
	}.count().log()
}


fun main() {
	println("Day 7: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }