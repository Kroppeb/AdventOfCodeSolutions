@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d13


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*

 */


import com.beust.klaxon.Parser
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(6, 3);


fun comp(l: Any, r:Any) : Int {
	when {
		l is Int && r is Int -> return l.compareTo(r)
		l is List<*> && r is List<*> -> {
			var i = 0
			while (i < l.size && i < r.size) {
				val c = comp(l[i]!!, r[i]!!)
				if (c != 0) return c
				i++
			}

			return l.size.compareTo(r.size)
		}
		l is Int -> return comp(listOf(l), r)
		r is Int -> return comp(l, listOf(r))
		else -> error ("$l $r: ${l::class} ${r::class}")
	}
}

private fun part1() {
	var data = getLines(2022_3).splitOnEmpty()

	var i = 0
	var sum = 0

	for ((l,r) in data) {
		i++

		val lp = Parser.default().parse(StringBuilder(l))!!
		val rp = Parser.default().parse(StringBuilder(r))!!

		if (comp(lp, rp) < 0) {
			sum += i
		}
	}

	sum log 1
}


private fun part2() {
	var data = getLines(2022_13).splitOnEmpty()

	var x = (data.flatten().map{Parser.default().parse(StringBuilder(it))} + listOf<Any>(listOf(listOf(2)), listOf(listOf(6)))).sortedWith(kotlin.Comparator(::comp))


	(x.indexOfFirst { it == listOf(listOf(2)) } + 1) *  (x.indexOfFirst { it == listOf(listOf(6)) } + 1)log 2


}


fun main() {
	println("Day 13: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
//	.also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}