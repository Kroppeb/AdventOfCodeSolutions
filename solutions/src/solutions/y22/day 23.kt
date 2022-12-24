@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d23


/*

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

 */



import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_23).e().grid()

	var elves = data.filter{it.v == '#'}.map{it.pi.sint}.toSet()

	var moves:List<(Point) -> Point?> = listOf({
		if (it.n !in elves && it.nw !in elves && it.ne !in elves) it.n else null
	},{
		if (it.s !in elves && it.sw !in elves && it.se !in elves) it.s else null
	},
		{
			if (it.w !in elves && it.nw !in elves && it.sw !in elves) it.w else null
		},
		{
			if (it.e !in elves && it.ne !in elves && it.se !in elves) it.e else null
		},
		)

	repeat(10) {
		var m = elves.filter{it.getOctNeighbours() anyIn elves}

		val tm = m.map{ it to (moves.mapNotNull { mv -> mv(it) }.firstOrNull() ?: it) }
		val x = tm.groupBy{it.second}.values

		var nElves = elves.toMutableSet()
		for (l in x) {
			if (l.size == 1){
				val (f,t) = l.first()
				nElves.remove(f)
				nElves.add(t)
			}
		}

		elves = nElves

//		println()
//		elves.bounds().print{if(it in elves) '#' else '.'}

		moves = moves.drop(1) + moves.take(1)
	}

	elves.bounds().size - elves.size log 1
}


private fun part2() {
	var data = getLines(2022_23).e().grid()

	var elves = data.filter{it.v == '#'}.map{it.pi.sint}.toSet()

	var moves:List<(Point) -> Point?> = listOf({
		if (it.n !in elves && it.nw !in elves && it.ne !in elves) it.n else null
	},{
		if (it.s !in elves && it.sw !in elves && it.se !in elves) it.s else null
	},
		{
			if (it.w !in elves && it.nw !in elves && it.sw !in elves) it.w else null
		},
		{
			if (it.e !in elves && it.ne !in elves && it.se !in elves) it.e else null
		},
	)

	loop() {
		var m = elves.filter{it.getOctNeighbours() anyIn elves}

		if (it % 10 == 0) {
			"$it : ${m.size}" log 0
		}
		if (m.isEmpty()) {
			it + 1 log 2
			return
		}

		val tm = m.map{ it to (moves.mapNotNull { mv -> mv(it) }.firstOrNull() ?: it) }
		val x = tm.groupBy{it.second}.values

		var nElves = elves.toMutableSet()
		for (l in x) {
			if (l.size == 1){
				val (f,t) = l.first()
				nElves.remove(f)
				nElves.add(t)
			}
		}

		elves = nElves

//		println()
//		elves.bounds().print{if(it in elves) '#' else '.'}

		moves = moves.drop(1) + moves.take(1)
	}
}

fun main() {
	println("Day 23: ")
	part1()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}