package solutions.solutions.y19

import coroutines.parallelMap
import coroutines.pipeTo
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
			send(s)
			send(i)
			output.receive()
		} }
	}.maxOrNull()
	println(r)
}

private fun part2(data: List<Int>) = runBlocking(Dispatchers.Default) {
	println("started part 2")
	val r = permutations().map { it.map { it + 5 } }.parallelMap { p ->
		val input = Channel<DataType>(Channel.UNLIMITED)

		lateinit var last:IntComputer
		val computers = (0..4).fold<Int,ReceiveChannel<DataType>>(input) { ic, mode ->
			runComputer(data).also{last = it}.run{
				launch {
					onComplete { input.close() }
					send(p[mode])
					sendAll(ic)
				}
				output
			}
		}
		input.send(0)
		(computers pipeTo input).receive()
	}.maxOrNull()
	println(r)
}

fun main() {
	/*
	val test = get(1019_07)
	part1(test)
	part2(test)
*/
	val data = getInts(2019_7)
	//part1(data)
	part2(data)
}