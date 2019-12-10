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
}.filter { it.areDistinct() }.map2{it.toLong()}


private fun part1(data: Map<Long,Long>) = runBlocking {

	val r = permutations().map { p ->
		p.fold(0L) { i, s -> IntComputer(data.toMutableMap()).run {
			input.send(s)
			input.send(i)
			runProgram()
			output.receive()
		} }
	}.max()
	println(r)
}

private fun part2(data:  Map<Long,Long>):Unit = runBlocking(Dispatchers.Default) {
TODO()
}

fun main() {
	/*
	val test = get(1019_07)
	part1(test)
	part2(test)
*/
	val data = getInts(7).withIndex().associate { (i,v)->i.toLong() to v.toLong()}
	part1(data)
	part2(data)
}