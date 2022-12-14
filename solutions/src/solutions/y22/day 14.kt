@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


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

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.sint.*
import me.kroppeb.aoc.helpers.*
import itertools.*
import me.kroppeb.aoc.helpers.point.bounds
import me.kroppeb.aoc.helpers.point.toL
import me.kroppeb.aoc.helpers.point.toP
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(3, 6);


private fun part1() {
	var data = getLines(2022_14).points()

	val walls = msop()

	for (line in data) {
		for ((s, e) in line.zipWithNext()) {
			walls += s toL e
		}
	}

	val sand = msop()

	o@ while (500 toP 0 !in sand) {

		var s = 500 toP 0
		while (true) {

			if (s.y > 5000) {
				break@o
			}
			if (s.down !in walls && s.down !in sand) {
				s = s.down
				continue
			}

			if (s.left.down !in walls && s.left.down !in sand) {
				s = s.left.down
				continue
			}

			if (s.right.down !in walls && s.right.down !in sand) {
				s = s.right.down
				continue
			}

			break
		}



		sand += s
	}

//	val p = walls.bounds()
//	p.ys.forEach { y ->
//		p.xs.forEach { x ->
//			if (x toP y in walls) {
//				print('#')
//			} else if (x toP y in sand) {
//				print('o')
//			} else {
//				print('.')
//			}
//		}
//		println()
//	}
	sand.size log 1

}


private fun part2() {
	var data = getLines(2022_14).points()

	val walls = msop()

	for (line in data) {
		for ((s, e) in line.zipWithNext()) {
			walls += s toL e
		}
	}

	val yy = walls.maxOf{it.y} + 2

	walls += -1000 toP yy toL (2000 toP yy)

	val sand = msop()

	o@ while (500 toP 0 !in sand) {

		var s = 500 toP 0
		while (true) {

			if (s.y > 5000) {
				break@o
			}
			if (s.down !in walls && s.down !in sand) {
				s = s.down
				continue
			}

			if (s.left.down !in walls && s.left.down !in sand) {
				s = s.left.down
				continue
			}

			if (s.right.down !in walls && s.right.down !in sand) {
				s = s.right.down
				continue
			}

			break
		}



		sand += s
	}

//	val p = walls.bounds()
//	p.ys.forEach { y ->
//		p.xs.forEach { x ->
//			if (x toP y in walls) {
//				print('#')
//			} else if (x toP y in sand) {
//				print('o')
//			} else {
//				print('.')
//			}
//		}
//		println()
//	}
	sand.size log 1

}


fun main() {
	println("Day 14: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
	// .also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}