@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d04c

import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	val m = mutableListOf<Map<String, String>>()
	var p = mutableMapOf<String, String>()
	for (i in data) {
		if (i.isEmpty() || i[0].isBlank()) {
			m += p
			p = mutableMapOf()
		} else {

			for (j in i) {
				val (x, y) = j.split(':')
				p[x] = y
			}
		}
	}
	m += p

	m.count {
		"byr" in it && "iyr" in it && "eyr" in it && "hgt" in it && "hcl" in it && "ecl" in it && "pid" in it
	}.let { println(it) }
}

private fun part2(data: Data) {
	val m = mutableListOf<Map<String, String>>()
	var p = mutableMapOf<String, String>()
	for (i in data) {
		if (i.isEmpty() || i[0].isBlank()) {
			m += p
			p = mutableMapOf()
		} else {
			for (j in i) {
				val (x, y) = j.split(':')
				p[x] = y
			}
		}
	}
	m += p

	m.count { u ->
		u["byr"].let {
			it != null && it.toInt() in 1920..2002
		} &&
				u["iyr"].let {
					it?.toInt() in 2010..2020
				} &&
				u["eyr"].let {
					it?.toInt() in 2020..2030
				} &&
				u["hgt"].let {
					when {
						it == null -> false
						it.endsWith("in") -> it.getInt() in 59..76
						it.endsWith("cm") -> it.getInt() in 150..193
						else -> false
					}
				} &&
				u["hcl"].let {
					it != null &&
							it.startsWith("#") &&
							it.length == 7 &&
							it.substring(1).all { it in '0'..'9' || it in 'a'..'f' }
				} &&
				u["ecl"].let {
					it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
				} &&
				u["pid"].let {
					it != null && it.length == 9 && it.all { it in '0'..'9' }
				}
	}.let { println(it) }
}

private typealias Data = WSV

fun main() {
	val data: Data = getWSV(2020_04)
	part1(data)
	part2(data)
}