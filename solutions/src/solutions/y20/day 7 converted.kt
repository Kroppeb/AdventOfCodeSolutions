@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d07c

import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	val mm = mutableMapOf<String, List<String>>()
	data.map {
		val (a, b) = it.split(" bags contain ")
		val c = b.split(", ").map {
			val u = it.split(" bag")[0].split(' ')
			u.subList(1, u.size).joinToString(" ")
		}
		mm[a] = c;
	}
	val mg = mutableMapOf<String, Int>("shiny gold" to 1, "other" to 0)

	fun find(i: String): Int {
		if (i in mg) {
			return mg[i]!!
		} else {
			val p = mm[i]!!.sumBy { find(it) }
			mg[i] = p
			return p;
		}
	}

	for (i in mm) {
		find(i.key)
	}

	println(mg.count { it.value > 0 } - 1)
}

private fun part2(data: Data) {
	val mm = mutableMapOf<String, List<Pair<Int, String>>>()
	data.map {
		val (a, b) = it.split(" bags contain ")
		val c = b.split(", ").map {
			val u = it.split(" bag")[0].split(' ')
			(u[0].toIntOrNull()?: 0 ) to u.subList(1, u.size).joinToString(" ")
		}
		mm[a] = c;
	}
	val mg = mutableMapOf<String, Int>("other" to 0)

	fun find(i: String): Int {
		if (i in mg) {
			return mg[i]!!
		} else {
			val p = mm[i]!!.sumBy { it.first * find(it.second) } + 1
			mg[i] = p
			return p;
		}
	}

	for (i in mm) {
		find(i.key)
	}

	println(find("shiny gold") - 1)
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_07)
	part1(data)
	part2(data)
}