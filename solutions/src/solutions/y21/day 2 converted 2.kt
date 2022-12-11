@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d2c2

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2021_02).map{it.split(" ")}
    var d = 0
    var x = 0

    for ((a, b) in data) {
        when (a) {
            "forward" -> x += b.int()
            "up" -> d -= b.int()
            "down" -> d += b.int()
        }
    }

    println(d * x)
}

private fun part2() {
	var data = getLines(2021_02).map{it.split(" ")}
    var d = 0
    var x = 0
    var y = 0

    for ((a, b) in data) {
        when (a) {
            "forward" -> {
                x += b.toInt()
                y += b.toInt() * d
            }
            "up" -> d -= b.toInt()
            "down" -> d += b.toInt()
        }
    }

    println(y * x)
}

fun main() {
    part1()
    part2()
}




private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
	.also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}