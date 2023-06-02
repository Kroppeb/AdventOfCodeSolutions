@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d25


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
	var data = getLines(2022_25)

	val x = data.sumOf{ l -> l.reversed().mapIndexed{ i, c -> 5L.pow(i).s * when(c) {
		'2' -> 2
		'1' -> 1
		'0' -> 0
		'-' -> -1
		'=' -> -2
		else -> throw Exception("Invalid char $c")
	} }.sum() } log 0

	var res = ""
	var uu= x

	while (uu != 0.s) {
		var dif = uu mod 5
		if (dif <= 2) {
			res = dif.i.toString() + res
		} else if (dif == 4.s){
			res = '-' + res
			dif = -1.s
		} else {
			res = '=' + res
			dif = -2.s
		}

		uu -= dif
		uu /= 5
	}

	res log 1
}

fun main() {
	println("Day 25: ")
	part1()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(data: Any?) {
	val s:String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}