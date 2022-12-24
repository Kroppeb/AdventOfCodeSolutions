@file:Suppress("PackageDirectoryMismatch", "UnusedImport", "DuplicatedCode")

package solutions.y22.d24c


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
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import kotlin.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.sequences.*
import kotlin.text.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_24).e().grid()

	var blizzardsN = data.filter{it.v == '^'}.map{it.p}.toSet()
	var blizzardsE = data.filter{it.v == '>'}.map{it.p}.toSet()
	var blizzardsS = data.filter{it.v == 'v'}.map{it.p}.toSet()
	var blizzardsW = data.filter{it.v == '<'}.map{it.p}.toSet()

	val b = data.bounds
	val bb = b.retract(1)

	val widthB = bb.ys.count()
	val heightB = bb.xs.count()

	val blizzardsNS = (blizzardsN.map{it.northsInc().take(heightB).toList()} + blizzardsS.map{it.southsInc().take(heightB).toList()})
		.transpose().map{it.map{it % bb}.toSet()}
	val blizzardsEW = (blizzardsE.map{it.eastsInc().take(widthB).toList()} + blizzardsW.map{it.westsInc().take(widthB).toList()})
		.transpose().map{it.map{it % bb}.toSet()}


	bfsDist(data.getBp(0 toP 1), {bp,_ -> bp.p == b.lowerRight.left}) { p, t ->
		p.getQuadNeighbourHood().filter{it.v != '#' && it.p !in blizzardsNS[(t+1) % heightB] && it.p !in blizzardsEW[(t+1) % widthB]}
	} log 1
}


private fun part2() {
	var data = getLines(2022_24).e().grid()

	var blizzardsN = data.filter{it.v == '^'}.map{it.p}.toSet()
	var blizzardsE = data.filter{it.v == '>'}.map{it.p}.toSet()
	var blizzardsS = data.filter{it.v == 'v'}.map{it.p}.toSet()
	var blizzardsW = data.filter{it.v == '<'}.map{it.p}.toSet()

	val b = data.bounds
	val bb = b.retract(1)

	val widthB = bb.ys.count()
	val heightB = bb.xs.count()

	val blizzardsNS = (blizzardsN.map{it.northsInc().take(heightB).toList()} + blizzardsS.map{it.southsInc().take(heightB).toList()})
		.transpose().map{it.map{it % bb}.toSet()}
	val blizzardsEW = (blizzardsE.map{it.eastsInc().take(widthB).toList()} + blizzardsW.map{it.westsInc().take(widthB).toList()})
		.transpose().map{it.map{it % bb}.toSet()}


	val p1 = bfsDist(data.getBp(0 toP 1), {bp,_ -> bp.p == b.lowerRight.left}) { p, t ->
		p.getQuadNeighbourHood().filter{it.v != '#' && it.p !in blizzardsNS[(t+1) % heightB] && it.p !in blizzardsEW[(t+1) % widthB]}
	} log 1
	val s1 = p1.dist

	val p2 = bfsDist(data.getBp(heightB + 1 toP widthB), {bp,_ -> bp.p == 0 toP 1}) { p, t ->
		p.getQuadNeighbourHood().filter{it.v != '#' && it.p !in blizzardsNS[(t+s1+1) % heightB] && it.p !in blizzardsEW[(t+s1+1) % widthB]}
	} log 0
	val s2 = p2.dist + s1

	val p3 = bfsDist(data.getBp(0 toP 1), {bp,_ -> bp.p == b.lowerRight.left}) { p, t ->
		p.getQuadNeighbourHood().filter{it.v != '#' && it.p !in blizzardsNS[(t+s2+1) % heightB] && it.p !in blizzardsEW[(t+s2+1) % widthB]}
	} log 0
	val s3 = p3.dist + s2
	s3 log 2
}

fun main() {
	println("Day 24: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
//		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(data: Any?) {
	val s:String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}