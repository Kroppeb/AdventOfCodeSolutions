@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d04c2

import helpers.*
import grid.*
import solutions.solutions.y19.d22.m

private val xxxxx = Clock(3, 6);

private fun part1() {
	val data = getLines(2020_04)
		.splitOnEmpty()
		.map { it.flatMap { it.split(" ") }.map { it.split(":") }.map { (a, b) -> a to b }.toMap() }


	data.count {
		"byr" in it && "iyr" in it && "eyr" in it && "hgt" in it && "hcl" in it && "ecl" in it && "pid" in it
	}.log()
}

private fun part2() {
	val data = getLines(2020_04)
		.splitOnEmpty()
		.map { it.flatMap { it.split(" ") }.map { it.split(":") }.map { (a, b) -> a to b }.toMap() }


	data.count { u ->
		"byr" in u && "iyr" in u && "eyr" in u && "hgt" in u && "hcl" in u && "ecl" in u && "pid" in u &&
		u["byr"]!!.int() in 1920..2002 &&
				u["iyr"]!!.int() in 2010..2020 &&
				u["eyr"]!!.int() in 2020..2030 &&
				u["hgt"]!!.let {
					when {
						it.endsWith("in") -> it.int() in 59..76
						it.endsWith("cm") -> it.int() in 150..193
						else -> false
					}
				} &&
				u["hcl"]!!.let {
							it.startsWith("#") &&
							it.length == 7 &&
							it.e().drop(1).allIn ('0'..'9' or 'a'..'f')
				} &&
				u["ecl"]!! in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") &&
				u["pid"]!!.let {
					it.length == 9 && it.e().allIn('0'..'9')
				}
	}.log()
}

fun main() {
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
private infix fun <T> T.log(_ignored: Any?): T = this.log()