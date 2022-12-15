@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d09c4


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.context.start
import me.kroppeb.aoc.helpers.contextual.cumSum
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*


private val xxxxx = Clock(6, 3);


private fun part1() = start{
	getLines(2022_09)
		.rleDecode({ it[0].toPoint() }, { it.int() })
		.cumSum(0 toP 0)
		.scan(0 toP 0){ t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.distinct().size log 1
}

private fun part2() = start{
	getLines(2022_09)
		.rleDecode({ it[0].toPointI() }, { it.int() })
		.cumSum(0 toPI 0)
		.applyNTimes(9){
			it.scan { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		}
		.distinct().size log 2
}


fun main() {
	println("Day 9: ")
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