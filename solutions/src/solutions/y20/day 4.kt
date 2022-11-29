@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d04

import helpers.*
import grid.*

private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	val m = mutableListOf<Map<String, String>>()
	var p = mutableMapOf<String, String>()
	for (i in data) {
		if (i.isEmpty() || i[0].isBlank()) {
			m += p
			p = mutableMapOf<String, String>()
		} else {

			for (j in i) {
				val (x, y) = j.split(':')
				p[x] = y
			}
		}
	}
	m += p

	m.count {
		"byr" in it &&
				"iyr" in it &&
				"eyr" in it &&
				"hgt" in it &&
				"hcl" in it &&
				"ecl" in it &&
				"pid" in it
	}.let { println(it) }
}

private fun part2(data: Data) {
	val m = mutableListOf<Map<String, String>>()
	var p = mutableMapOf<String, String>()
	for (i in data) {
		if (i.isEmpty() || i[0].isBlank()) {
			m += p
			p = mutableMapOf<String, String>()
		} else {

			for (j in i) {
				val (x, y) = j.split(':')
				p[x] = y
			}
		}
	}
	m += p

	m.count {
		"byr" in it &&
				"iyr" in it &&
				"eyr" in it &&
				"hgt" in it &&
				"hcl" in it &&
				"ecl" in it &&
				"pid" in it &&
				it["byr"].let {
					it!!.length == 4 && it.toInt() in 1920..2002
				} &&
				it["iyr"].let {
					it!!.length == 4 && it.toInt() in 2010..2020
				} &&
				it["eyr"].let {
					it!!.length == 4 && it.toInt() in 2020..2030
				} &&
				it["hgt"].let {
					val p = it!!
					if (p.endsWith("in")) {
						p.getInt() in 59..76
					} else if (p.endsWith("cm")) {
						p.getInt() in 150..193
					} else {
						false
					}
				} &&

				it["hcl"]!!.let {
					it.startsWith("#") &&
							it.length == 7 &&
							it.substring(1).all { it in '0'..'9' || it in 'a'..'f' }
				} && it["ecl"]!!.let {
			it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

		} &&
				it["pid"]!!.let {
					it.length == 9 && it.all { it in '0'..'9' }
				}
	}.let { println(it) }
}

private typealias Data = WSV

fun main() {
	val data: Data = getWSV(2020_04)
	part1(data)
	part2(data)
}