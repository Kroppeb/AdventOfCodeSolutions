@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d14c

import helpers.*
import kotlinx.coroutines.*

private fun part1(data: AlphaNumLines)= runBlocking {

	val recipes = data.associate{
		val r = it.reversed()
		val (a,b) = r
		val c = r.drop(2).chunked(2).map{(q,r) -> r.toLong() to q}
		a to (b.toLong() to c)
	}

	//12_039_408L
	//12_039_405L
	val needed = mutableMapOf("FUEL" to 1L, "ORE" to 0L)
	val remaining = mutableMapOf("ORE" to 0L)
	while(needed.size > 1){
		val p = needed.toList()
		needed.clear()
		for((m, c) in p){
			if(m == "ORE"){
				needed[m] = (needed[m]?:0) + c
				continue
			}
			var c1 = (remaining[m]?:0) - c
			if(c1 < 0){
				val (q, r) = recipes[m]!!
				val times = ((-c1 + q - 1) / q)
				c1 += times * q
				for((i,j) in r){
					needed[j] = (needed[j]?:0) + i * times
				}
			}
			remaining[m] = c1
		}
	}
	println(needed["ORE"])
}

private fun getOre(vl: Long, recipes: Map<String, Pair<Long, List<Pair<Long, String>>>>):Long{


	val needed = mutableMapOf("FUEL" to vl, "ORE" to 0L)
	val remaining = mutableMapOf("ORE" to 0L)
	while(needed.size > 1){
		val p = needed.toList()
		needed.clear()
		for((m, c) in p){
			if(m == "ORE"){
				needed[m] = (needed[m]?:0) + c
				continue
			}
			var c1 = (remaining[m]?:0) - c
			if(c1 < 0){
				val (q, r) = recipes[m]!!
				val times = ((-c1 + q - 1) / q)
				c1 += times * q
				for((i,j) in r){
					needed[j] = (needed[j]?:0) + i * times
				}
			}
			remaining[m] = c1
		}
	}
	return needed["ORE"]!!
}

private fun part2(data: AlphaNumLines) = runBlocking {
	// during the contest I just manually did binary search. Lost quite some time due to that i think
	val recipes = data.associate{
		val r = it.reversed()
		val (a,b) = r
		val c = r.drop(2).chunked(2).map{(q,r) -> r.toLong() to q}
		a to (b.toLong() to c)
	}
	println(bsLastL { getOre(it, recipes) <= 1_000_000_000_000 })
}


fun main() {
	val data: AlphaNumLines = getAlphaNumLines(2019_14)
	part1(data)
	part2(data)
}