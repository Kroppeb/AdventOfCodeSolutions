package solutions

import helpers.*

private fun part1(data: List<List<Char>>) {
	fun getPoints(wl: List<Char>): List<Point> = wl.scan(0 toP 0) { p, c ->
		p + c.toPoint()
	}

	val w1 = getPoints(data[0])
	val w2 = getPoints(data[1])

	val keys = w1.intersect(w2)


	val q = keys.getClosestManDist()
	println(q)
}

private fun part2(data: List<List<Char>>) {
	fun getPoints(wl: List<Char>): Map<Point, Int> = wl.scan(0 toP 0 to 0) { (p, d), c ->
		p + c.toPoint() to d + 1
	}.groupBy { it.first }.mapValues { (k, v) -> v.first().second }

	val w1 = getPoints(data[0])
	val w2 = getPoints(data[1])

	val keys = w1.intersectMap(w2) { a, b -> a + b }
	println(keys)

	val q = keys.values.min()
	println(q)
}

fun main() {
	val data = getCSV(1001_03).map { l -> l.rleDecode({ it[0] }, { it.getInt()!! }) }
	part1(data)
	part2(data)
}
