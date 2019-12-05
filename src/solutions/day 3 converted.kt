package solutions

import helpers.*
import kotlin.math.abs

private fun part1(data:List<List<Pair<Char, Int>>>){
	fun getPoints(wl:List<Pair<Char, Int>>): Set<Point> {
		val w = mutableSetOf<Point>()
		var cur = 0 toP 0
		for ((c, d) in wl){
			val dcur = c.toPoint()

			repeat(d){
				cur += dcur
				w.add(cur)
			}
		}
		return w
	}

	val w1 = getPoints(data[0])
	val w2 = getPoints(data[1])

	println((w1*w2).getClosestManDist())
}

private fun part2(data:List<List<Pair<Char, Int>>>){
	fun getPoints(wl:List<Pair<Char, Int>>): Map<Point, Int> {
		val w = mutableMapOf<Point, Int>()
		var cur = 0 toP 0
		var dist = 0
		for ((c, d) in wl){
			val dcur = c.toPoint()

			repeat(d){
				dist += 1
				cur += dcur
				w.putIfAbsent(cur, dist)
			}
		}
		return w
	}

	val w1 = getPoints(data[0])
	val w2 = getPoints(data[1])

	println(w1.intersectMap(w2){a,b -> a+b}.values.min())
}

fun main(){
	val ints = getCSV(3).map2{it[0] to it.getInt()!!}
	part1(ints)
	part2(ints)

}