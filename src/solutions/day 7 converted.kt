package solutions

import helpers.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.math.*

fun permutations() = (0..4).cartesianProduct((0..4)).flatMap { (a, b) ->
	(0..4).cartesianProduct((0..4))
			.flatMap { (x, y) -> (0..4).map { listOf(a, b, x, y, it) } }
}.filter { it.areDistinct() }


private fun part1(data: List<Int>) = runBlocking {
	val r = permutations().map { p ->
		p.fold(0) { i, s -> IntComputer(data, listOf(s, i)).let { it.runBlocked().receive(); it.last } }
	}.max()
	println(r)
}

private fun part2(data: List<Int>) = runBlocking {

	val r = permutations().map { it.map { it + 5 } }.map { p ->
		val input = Channel<Int>()
		input.send(0)

		val computers = (0..4).fold<Int,ReceiveChannel<Int>>(input) { ic, mode ->
			IntComputer(data, listOf(mode) + ic).run()
		}

		computers pipeTo input
		input.receive()
	}.max()
	println(r)
}

fun main() {
	/*
	val test = get(1019_07)
	part1(test)
	part2(test)
*/
	val data = getInts(7)
	part1(data)
	part2(data)
}