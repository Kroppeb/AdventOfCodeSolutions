@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d11


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.grid.map
import me.kroppeb.aoc.helpers.grid.mapIndexedOld

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val g1 = data.e().grid()

	var g2 = g1.map { it }
	while (true) {

		var cont = false
		val g3 =
				g2.mapIndexedOld { p, c ->
					if (c == '.')
						return@mapIndexedOld c
					val cc = p.getOctNeighbours().filter { it in g1.bounds }.count { g2[it] == '#' }
					if (c == 'L') {
						if (cc == 0) {
							cont = true
							'#'
						} else 'L'
					} else {
						if (cc >= 4) {
							cont = true

							'L'
						} else
							'#'
					}
				}

		g2 = g3.grid()

		//g2.items.sumBy{it.count{it == '#'}}.log()
		//g2 .items.mapIndexed{i,a ->
		//	a.log()
		//}
		if (!cont)
			break

	}
	g2.items.sumBy { it.count { it == '#' } }.log()
}

private fun part2(data: Data) {
	val g1 = data.e().grid()

	var g2 = g1.map { it }
	while (true) {

		var cont = false
		val g3 =
				g2.mapIndexedOld { p, c ->
					if (c == '.')
						return@mapIndexedOld c
					val cc = p.getOctNeighbours().map{
						var pp = it
						var diff = it - p
						while(pp in g1.bounds){
							if(g2[pp] != '.')
								return@map g2[pp]
							pp += diff
						}
						'.'
					}.count { it == '#' }
					if (c == 'L') {
						if (cc == 0) {
							cont = true
							'#'
						} else 'L'
					} else {
						if (cc >= 5) {
							cont = true

							'L'
						} else
							'#'
					}
				}

		g2 = g3.grid()

		//g2.items.sumBy{it.count{it == '#'}}.log()
		//g2 .items.mapIndexed{i,a ->
		//	a.log()
		//}
		if (!cont)
			break

	}
	g2.items.sumBy { it.count { it == '#' } }.log()
}

private typealias Data = List<String>

fun main() {
	val data: Data = getLines(2020_11)
	//part1(data)
	part2(data)
}


fun Any?.log() = println(this)