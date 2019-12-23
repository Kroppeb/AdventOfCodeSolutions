package solutions

import helpers.*
import itertools.count
import kotlinx.coroutines.*

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
		(0..x*2).find { y ->
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

	var y = count().first() { y ->
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