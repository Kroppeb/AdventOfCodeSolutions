package fast

import helpers.*
import kotlin.math.*
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

lateinit var ndd :Array<IntArray>
lateinit var asd :Array<IntArray>


private fun part1(range : IntRange): Int{
	// for each location of the first doubles
	for(i in 0..4)
		TODO()
	TODO()
}

private fun part2(range : IntRange): Int {
	TODO()
}


fun main() {
	println(List(100) {
		measureTimeMillis {
			prepareLookup()
			println(ndd)


			part1(100000..999999)
			part2(100000..999999)

		}

	}.average())
}

fun prepareLookup() {
	ndd = Array(5){ IntArray(10) }
	repeat(5){
		ndd[it][0] = 0 // can't make a number without digits (unless length = 0, see next)
	}
	repeat(10){
		ndd[0][it] = 1 // there is 1 way to make a number of length 0
	}
	for(l in (1..4))
		for (d in (1..9))
			ndd[l][d] = ndd[l-1][d]+ndd[l][d-1]
}

fun calcOrLook(l:Int, d:Int): Int{
	if(l == 0 || d == 0)
		return 1

	val v = ndd[l][d]
	if(v != 0)
		return v
	// v = (l-1, d) + (l,d-1)
	val vn = calcOrLook(l-1, d) + calcOrLook(l,d-1)
	ndd[l-1][d-1] = vn
	return vn
}
