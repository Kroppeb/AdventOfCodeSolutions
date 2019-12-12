package solutions

import Grid.Grid
import Grid.StrictPointGrid
import Grid.entityGrid
import helpers.*
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

private fun part2(data: StrictPointGrid, a:Point) {
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
	val a1 = part1(data1)
	part2(data1, a1)

	val data = getLines(10).e().entityGrid { it != '.' }
	val a2 = part1(data)
	part2(data, a2)
}