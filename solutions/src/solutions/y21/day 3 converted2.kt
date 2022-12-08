@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d3c2

import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2021_03).e().transpose()

	data.map { it.countEach().maxByValue() }.join().toInt(2) *
			data.map { it.countEach().minByValue() }.join().toInt(2) log 1
}

private fun part2() {
	var data = getLines(2021_03).e()

	var ox = data
	var co = data

	repeat(data[0].size) { i ->
		val oxP = ox.transpose().map { it.countEach().allMaxByValue().sortedDescending()[0] }[i]
		val coP = co.transpose().map { it.countEach().allMinByValue().sorted()[0] }[i]

		ox = ox.filter { it[i] == oxP }
		co = co.filter { it[i] == coP }
	}

	ox.single().join().toInt(2) * co.single().join().toInt(2) log 2
}

fun main() {
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
private infix fun <T> T.log(_ignored: Any?): T = this.log()