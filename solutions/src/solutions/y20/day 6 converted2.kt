@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d06c2

import helpers.*
import grid.*

private val xxxxx = Clock(3, 6);

private fun part1() {
	getLines(2020_06).splitOnEmpty().e().sumOf {it.union().size}.log()
}

private fun part2() {
	getLines(2020_06).splitOnEmpty().e().sumOf {it.intersect().size}.log()
}

fun main() {
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
private infix fun <T> T.log(_ignored: Any?): T = this.log()