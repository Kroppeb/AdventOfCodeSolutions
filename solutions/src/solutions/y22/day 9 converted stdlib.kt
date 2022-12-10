@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d09cstd

import helpers.getLines
import helpers.Point
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import kotlin.collections.scan


private fun part1() {
	getLines(2022_09)
		.flatMap { line ->
			List(line.drop(2).toInt()) {
				when (line[0]) {
					'U' -> Point(0, -1)
					'D' -> Point(0, 1)
					'L' -> Point(-1, 0)
					'R' -> Point(1, 0)
					else -> error("bad")
				}
			}
		}
		.runningFold(Point(0, 0)) { p, d -> p + d }
		.runningFold(Point(0, 0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.distinct().size
		.also{println(it)}
}

private fun part2() {
	getLines(2022_09)
		.flatMap { line ->
			List(line.drop(2).toInt()) {
				when (line[0]) {
					'U' -> Point(0, -1)
					'D' -> Point(0, 1)
					'L' -> Point(-1, 0)
					'R' -> Point(1, 0)
					else -> error("bad")
				}
			}
		}
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.scan(Point(0,0)) { t, h -> if (t.chebyshevDistTo(h) > 1) t + (h - t).sign() else t }
		.distinct().size
		.also{println(it)}
}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}
