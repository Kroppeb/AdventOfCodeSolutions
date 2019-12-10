package solutions

import coroutines.pipeTo
import coroutines.plus
import helpers.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

fun permutations() = (0..4).cartesianProduct((0..4)).flatMap { (a, b) ->
	(0..4).cartesianProduct((0..4))
			.flatMap { (x, y) -> (0..4).map { listOf(a, b, x, y, it) } }
}.filter { it.areDistinct() }.map2{it.toDataType()}


private fun part1(data: List<Int>) = runBlocking {

	val r = permutations().map { p ->
		p.fold(0L) { i, s -> runComputer(data).run {
			input.send(s)
			input.send(i)
			output.receive()
		} }
	}.max()
	println(r)
}

private fun part2(data: List<Int>) = runBlocking(Dispatchers.Default) {

	val r = permutations().map { it.map { it + 5 } }.map { p ->
		val input = Channel<DataType>()
		input.send(0)

		lateinit var last:IntComputer
		val computers = (0L..4L).fold<Long,ReceiveChannel<DataType>>(input) { ic, mode ->
			runComputer(data, listOf(mode) + ic).also{last = it}.output
		}

		computers pipeTo input
		last.join()
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