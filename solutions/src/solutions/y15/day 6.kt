@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d06

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toB
import me.kroppeb.aoc.helpers.point.toP


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	val data = getLines(2015_06)

	val lights = (0..999).map{(0..999).map{ false }}.mutableGrid()

	for (line in data){
		val (a,b,c,d) = line.ints()
		val bounds = (a toP b) toB (c toP d)

		if ("on" in line) {
			for (p in bounds) {
				lights[p] = true
			}
		} else if ("off" in line) {
			for (p in bounds) {
				lights[p] = false
			}
		} else if ("toggle" in line) {
			for (p in bounds) {
				lights[p] = !lights[p]
			}
		} else error(line)
	}

	lights.allItems().count{it}.log()

}

private fun part2() {
	val data = getLines(2015_06)

	val lights = (0..999).map{(0..999).map{ 0 }}.mutableGrid()

	for (line in data){
		val (a,b,c,d) = line.ints()
		val bounds = (a toP b) toB (c toP d)

		if ("on" in line) {
			for (p in bounds) {
				lights[p]++
			}
		} else if ("off" in line) {
			for (p in bounds) {
				if (lights[p] > 0) lights[p]--
			}
		} else if ("toggle" in line) {
			for (p in bounds) {
				lights[p] = lights[p] + 2
			}
		} else error(line)
	}

	lights.allItems().sum().log()

}


fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }