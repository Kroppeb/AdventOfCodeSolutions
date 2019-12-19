package solutions

import helpers.*
import kotlinx.coroutines.*
import kotlin.math.*

private fun part1(data: Digits) = runBlocking {
	var step = List(10000){data}.flatten().drop(5977269)
	repeat(100) {
		step = step.indices.reversed().scan(0) { p, n-> p + step[n]}.map{abs(it)%10}.reversed()
	}
	step.take(8).map{print(it)}
}


fun main() {
	val data: Digits = getDigits(16)
	part1(data)
}