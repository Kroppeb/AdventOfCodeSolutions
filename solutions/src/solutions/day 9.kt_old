package solutions

import helpers.*
import java.math.BigInteger
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.math.*

private fun part1(data: List<BigInteger>) {
	BigIntegerComputer(data, listOf(BigInteger.ONE)).run()
}

private fun part2(data: List<BigInteger>) {
	BigIntegerComputer(data, listOf(BigInteger("2"))).run()
}

fun main() {
	val data = getCSV(9)[0].map{BigInteger(it)}
	part1(data)
	part2(data)
}