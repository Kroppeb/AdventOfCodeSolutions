@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d10c



import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.StrictPointGrid
import me.kroppeb.aoc.helpers.grid.entityGrid
import me.kroppeb.aoc.helpers.point.PointI
import kotlin.math.*


private fun part1(data: StrictPointGrid) =
	data.points.map { a ->
		data.points.filter { it != a }.count { b ->
			val d = b - a
			val g = gcd(abs(d.x), abs(d.y))
			val s = d / g
			val q = !(1 until g).any {
				data[a + s * it]
			}
			q
		} to a
	}.also{/*it.forEach(::println)*/}.maxBy { it.first }!!.also { println("${it.second}: ${it.first}") }.second


private fun fix(i: Double) = if (i < 0) i + 100 else i

private fun part2(data: StrictPointGrid, a: PointI) {
	data.points.filter { it != a }.map { b ->
		val d = b - a
		val g = gcd(abs(d.x), abs(d.y))
		val s = d / g
		val q = (1..g).count {
			data[a + s * it]
		}

		(s.angle() to q) to b

	}.sortedBy { -it.first.first }.sortedBy { it.first.second }[199].let { println("$it: ${it.second.y*100+it.second.x}") }
}

fun main() {
	val data1 = getLines(1019_10).e().entityGrid { it != '.' }
	val a = part1(data1)
	part2(data1, a)
}