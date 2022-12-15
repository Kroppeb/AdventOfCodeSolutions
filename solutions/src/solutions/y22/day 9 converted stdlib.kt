@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d09cstd

import me.kroppeb.aoc.helpers.getLines
import me.kroppeb.aoc.helpers.point.PointI
import kotlin.collections.scan


private fun part1() {
	getLines(2022_09)
		.flatMap { line ->
			List(line.drop(2).toInt()) {
				when (line[0]) {
					'U' -> PointI(0, -1)
					'D' -> PointI(0, 1)
					'L' -> PointI(-1, 0)
					'R' -> PointI(1, 0)
					else -> error("bad")
				}
			}
		}
		.runningFold(PointI(0, 0)) { p, d -> p + d }
		.runningFold(PointI(0, 0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.distinct().size
		.also{println(it)}
}

private fun part2() {
	getLines(2022_09)
		.flatMap { line ->
			List(line.drop(2).toInt()) {
				when (line[0]) {
					'U' -> PointI(0, -1)
					'D' -> PointI(0, 1)
					'L' -> PointI(-1, 0)
					'R' -> PointI(1, 0)
					else -> error("bad")
				}
			}
		}
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(PointI(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.distinct().size
		.also{println(it)}
}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}
