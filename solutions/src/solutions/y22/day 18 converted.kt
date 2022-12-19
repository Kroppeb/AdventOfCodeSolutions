@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


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
import kotlin.math.*

 */



import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.point.*
import kotlin.collections.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_18).point3D().toSet()

	data.sumOf { it.getHexNeighbours().count { it !in data } } log 1
}


private fun part2() {
	var data = getLines(2022_18).point3D().toSet()

	var b = data.bounds().expand(3)

	val outside = floodFill(b.lower) { p -> p.getHexNeighbours() and b notIn data}

	data.sumOf { it.getHexNeighbours().count { it in outside } } log 2
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
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