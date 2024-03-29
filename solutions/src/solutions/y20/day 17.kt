@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d17

import me.kroppeb.aoc.helpers.Clock

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.entityGrid
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.Point3DI
import me.kroppeb.aoc.helpers.point.toBI
import me.kroppeb.aoc.helpers.point.toPI

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val st = data.e().entityGrid { it == '#' }
	var current = st.points.map { it toPI 0 }.toSet()
	var bounds = st.boundsI
	var zs = 0
	var ze = 0
	repeat(6) {
		var next = mutableSetOf<Point3DI>()
		for (x in (bounds.xs.start - 10)..(bounds.xs.endInclusive + 10))
			for (y in (bounds.xs.start - 10)..(bounds.xs.endInclusive + 10)) {
				var base = x toPI y
				var b = false
				for (z in (zs - 10)..(ze + 10)) {
					var c = base toPI z
					val count = c.getIcosiHeptaNeighbours().count { it in current }
					if (c in current) {
						if (count in 2..3) {
							next.add(c)
							b = true
							zs = min(zs, z)
							ze = max(ze, z)
						}
					} else {
						if (count == 3) {
							next.add(c)
							b = true
							zs = min(zs, z)
							ze = max(ze, z)
						}
					}
				}
				if (b) {
					bounds = bounds.merge(base toBI base)
				}

			}
		current = next
		current.size.log()
		println(bounds)
		println(zs..ze)
		/*for(z in zs..ze){
			println(z)
			for(x in bounds.xs){
				for(y in bounds.ys)
					print(if(x toP y toP z in current) '#' else '.')
				println()
			}

		}*/
	}
	current.size.log()
}


private fun part2(data: Data) {
	val st = data.e().entityGrid { it == '#' }
	var current = st.points.map { it to (0 toPI 0) }.toSet()
	var boundsa = st.boundsI
	var boundsb = (0 toPI 0) toBI (0 toPI 0)
	repeat(6) {
		var next = mutableSetOf<Pair<PointI, PointI>>()
		for (x in (boundsa.xs.start - 10)..(boundsa.xs.endInclusive + 10))
			for (y in (boundsa.ys.start - 10)..(boundsa.ys.endInclusive + 10)) {
				var base = x toPI y
				var b = false
				for (z in (boundsa.xs.start - 10)..(boundsa.xs.endInclusive + 10))
					for (w in (boundsa.ys.start - 10)..(boundsa.ys.endInclusive + 10)) {
						var sec = z toPI w
						var c = base to sec
						val count = base.getOctNeighbours().cartesianProduct(sec.getOctNeighbours()).count { it in current }
						if (c in current) {
							if (count in 2..3) {
								next.add(c)
								b = true
								boundsb = boundsb.merge(sec toBI sec)
							}
						} else {
							if (count == 3) {
								next.add(c)
								b = true
								boundsb = boundsb.merge(sec toBI sec)
							}
						}
					}
				if (b) {
					boundsa = boundsa.merge(base toBI base)
				}
			}
		current = next
		current.size.log()
		//println(boundsa)
		//println(zs..ze)
	}

	/*for(z in zs..ze){
		println(z)
		for(x in bounds.xs){
			for(y in bounds.ys)
				print(if(x toP y toP z in current) '#' else '.')
			println()
		}

	}*/

current.size.log()
}


private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_17)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)