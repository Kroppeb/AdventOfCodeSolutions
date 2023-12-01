@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y23


/*

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
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
import me.kroppeb.aoc.helpers.collections.list.*
import me.kroppeb.aoc.helpers.collections.extensions.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
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
	var data = getLines(1).sumOf{it.digits().let{it.first() * 10 + it.last()}} log 1

}

val d = mapOf(
	"one" to 1,
	"two" to 2,
	"three" to 3,
	"four" to 4,
	"five" to 5,
	"six" to 6,
	"seven" to 7,
	"eight" to 8,
	"nine" to 9,
	"zero" to 0,
)
private fun part2() {
	var data = getLines(2023_01).map { U ->
		val start_l = d.keys.map { U.windowed(it.length).indexOf(it).let { o -> if(o == -1) null else d[it]!! to o } }.filterNotNull() +
			d.values.map{U.indexOf(it.toString()).let{o -> if(o == -1) null else it to o} }.filterNotNull()
		val x = start_l.minByOrNull { (a, b) -> b }!!.first

		val end_l = d.keys.map { U.windowed(it.length).lastIndexOf(it).let { o -> if(o == -1) null else d[it]!! to o } }.filterNotNull() +
			d.values.map{U.lastIndexOf(it.toString()).let{o -> if(o == -1) null else it to o} }.filterNotNull()
		val y = end_l.maxByOrNull { (a, b) -> b }!!.first

		x * 10 + y
	}.sum() log 1

}

fun main() {
	println("Day 01: ")
	part1()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(data: Any?) {
	val s: String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}