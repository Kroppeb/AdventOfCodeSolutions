@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d24


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
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.context.IterableOpps.size
import java.lang.ProcessBuilder.Redirect.to
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_24).drop(1).dropLast(1).e().map{it.drop(1).dropLast(1)}.grid()

	var blizzardsN = data.filter{it.v == '^'}.map{it.p}.toSet()
	var blizzardsE = data.filter{it.v == '>'}.map{it.p}.toSet()
	var blizzardsS = data.filter{it.v == 'v'}.map{it.p}.toSet()
	var blizzardsW = data.filter{it.v == '<'}.map{it.p}.toSet()

	val b = data.bounds

	val width = b.ys.count()
	val height = b.xs.count()

	val blizzardsNS = (blizzardsN.map{it.northsInc().take(height).toList()} + blizzardsS.map{it.southsInc().take(height).toList()})
		.transpose().map{it.map{it % b}.toSet()}
	val blizzardsEW = (blizzardsE.map{it.eastsInc().take(width).toList()} + blizzardsW.map{it.westsInc().take(width).toList()})
		.transpose().map{it.map{it % b}.toSet()}

//	blizzardsEW.map{2 toP 0 in it} log -1

	val p = bfsPath(-1 toP 0 to 1, {(p) -> p.x + 1 == height.s && p.y + 1 == width.s}) {(p, t) ->
		(p.getQuadNeighbours().filter{it in b} + listOf(p)).filter{it !in blizzardsNS[t % height] or blizzardsEW[t % width]}.map{it to t + 1}
	} log 0

	p!!.path.size log 1
}


private fun part2() {
	var data = getLines(2022_24).drop(1).dropLast(1).e().map{it.drop(1).dropLast(1)}.grid()

	var blizzardsN = data.filter{it.v == '^'}.map{it.p}.toSet()
	var blizzardsE = data.filter{it.v == '>'}.map{it.p}.toSet()
	var blizzardsS = data.filter{it.v == 'v'}.map{it.p}.toSet()
	var blizzardsW = data.filter{it.v == '<'}.map{it.p}.toSet()

	val b = data.bounds

	val width = b.ys.count()
	val height = b.xs.count()

	val blizzardsNS = (blizzardsN.map{it.northsInc().take(height).toList()} + blizzardsS.map{it.southsInc().take(height).toList()})
		.transpose().map{it.map{it % b}.toSet()}
	val blizzardsEW = (blizzardsE.map{it.eastsInc().take(width).toList()} + blizzardsW.map{it.westsInc().take(width).toList()})
		.transpose().map{it.map{it % b}.toSet()}

//	blizzardsEW.map{2 toP 0 in it} log -1

	val p = bfsPath(-1 toP 0 to 1, {(p) -> p.x + 1 == height.s && p.y + 1 == width.s}) {(p, t) ->
		(p.getQuadNeighbours().filter{it in b} + listOf(p)).filter{it !in blizzardsNS[t % height] or blizzardsEW[t % width]}.map{it to t + 1}
	} log 0

	val s1 = p!!.path.size log 1

	val p2 = bfsPath(height toP width-1 to s1, {(p) -> p.x == 0.s && p.y == 0.s}) {(p, t) ->
		(p.getQuadNeighbours().filter{it in b} + listOf(p)).filter{it !in blizzardsNS[t % height] or blizzardsEW[t % width]}.map{it to t + 1}
	} log 0

	val d2 = p2!!.path.size - 1 log 0
	val s2 = d2 + s1

	val p3 = bfsPath(-1 toP 0 to s2, {(p) -> p.x + 1 == height.s && p.y + 1 == width.s}) {(p, t) ->
		(p.getQuadNeighbours().filter{it in b} + listOf(p)).filter{it !in blizzardsNS[t % height] or blizzardsEW[t % width]}.map{it to t + 1}
	} log 0

	val s3 = p3!!.path.size + s2 - 1 log 2


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
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}