package solutions

import helpers.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.math.*

private fun part1(data: List<Int>) {
	val p = data.chunked(25*6).minBy{it.count{it==0}}!!
	println(p.count{it == 1} * p.count{it == 2})

}

private fun part2(data: List<Int>) {
	val p = println(data.chunked(25*6).fold<List<Int>, List<Int>?>(null){ acc, n ->
		if(acc == null)
			return@fold n
		acc.zip(n){a,b->
			if(a == 2)
				b
			else
				a
		}
	}!!.chunked(25).joinToString(separator = "\n"))
}

fun main() {
	val data = getDigits(8)
	part1(data)
	part2(data)
}