@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d02


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2018_02).e().map { it.countEachI().values }

	(data.count { 2 in it } * data.count { 3 in it }).log()


}


private fun part2() {
	var (a, b) = getLines(2018_02).e().pairWise()
		.single { (a, b) -> a.zip(b).count { (x, y) -> x != y } == 1 }
	a.zip(b).filter { (a, b) -> a == b }.firsts.join().log()

	getLines(2018_02).e()
		.pairWise()
		.single { (a, b) -> a.zip(b).count { (x, y) -> x != y } == 1 }
		.zipped()
		.filter { (a, b) -> a == b }.firsts.join().log()

}


fun main() {
	println("Day 2: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }