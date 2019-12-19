package solutions

import coroutines.parallelMap
import grid.entityGrid
import helpers.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.flow.asFlow
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.*

private fun part1(data: IntCode) = runBlocking {

	var c = 0L
	for (i in 0..49){
		for (j in 0..49) {
			runComputer(data).run {
				send(i.toLong())
				send(j.toLong())
				print(output.receive())
			}
		}
		println()
	}
	println(c)

}

private fun part2(data: IntCode) = runBlocking {
	var x = bsFirst() { x ->
		println("trying x=$x")
		((x/2)..(3*x)/2).find { y ->
			runComputer(data).run {
				send(x.toLong())
				send(y.toLong())
				output.receive() > 0L
			} &&
					runComputer(data).run {
						send(x.toLong())
						send(y.toLong() + 99)
						output.receive() > 0L
					}
					&&
					runComputer(data).run {
						send(x.toLong()+99)
						send(y.toLong())
						output.receive() > 0L
					}
					&&
					runComputer(data).run {
						send(x.toLong()+99)
						send(y.toLong() + 99)
						output.receive() > 0L
					}
		} != null
	}

	var y = ((x/2)..(3*x)/2).find { y ->
		runComputer(data).run {
				send(x.toLong())
				send(y.toLong())
				output.receive() > 0L
			} &&
					runComputer(data).run {
						send(x.toLong())
						send(y.toLong() + 99)
						output.receive() > 0L
					}
					&&
					runComputer(data).run {
						send(x.toLong()+99)
						send(y.toLong())
						output.receive() > 0L
					}
					&&
					runComputer(data).run {
						send(x.toLong())
						send(y.toLong() + 99)
						output.receive() > 0L
					}
	}!!
	println(x*10000 + y)
}


fun main() {
	val data: IntCode = getIntCode(19)
	part1(data)
	part2(data)
}