package solutions

import helpers.*

fun getPoints(A:List<String>): Map<Point, Int> {
	val ans = mutableMapOf<Point, Int>()
	var cur = 0 toP 0
	var length = 0
	for (cmd in A){
		val d = cmd[0]
		val n = cmd.getInt()!!
		val dcur = d.toPoint()

		repeat(n){
			length += 1
			cur += dcur
			ans.putIfAbsent(cur, length)
		}
	}
	return ans
}

fun main(){
	val (A,B) = getCSV(3)
	val PA = getPoints(A)
	val PB = getPoints(B)
	val both = PA.intersect(PB)
	val part1 = both.keys.getClosestManDist()!!
	val part2 = both.values.map{(a,b) -> a+b}.min()!!
	println("$part1, $part2")


}