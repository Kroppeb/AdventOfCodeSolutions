package solutions

import helpers.*
import itertools.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.*
import kotlin.math.*
import grid.entityGrid
import kotlinx.coroutines.channels.Channel


private fun part1(data: IntCode) = runBlocking(Dispatchers.Default) {
	val inB = mutableMapOf<Long, Channel<Msg>>()
	val out = Channel<Msg>(Channel.CONFLATED)
	inB[255] = out


	val pcs = (0 until 50).map {
		val c = Channel<Msg>(Channel.UNLIMITED)
		inB[it.toLong()] = c
		runComputer(data, c).run {
			prev = it.toLong()
			launch {
				for (address in output) {
					val x = output.receive()
					val y = output.receive()
					inB[address]?.send(Msg(x, y)) ?: error("Tried sending to $address")
				}
			}
			this
		}

	}

	pcs.forEach{

		launch {
			delay(1000)
			println("$it started")
			it.join()
		}
	}

	launch {
		val seen = mutableSetOf<Long>()
		delay(2000)
		println("NAT started")
		while (true) {
			while(!pcs.all { it.idle > 10 }){
				yield()
			}
			println("idle detected")

			val i = out.receive()
			println("got $i")
			if (i.y in seen) {
				println(i.y)
				error("stop")
			}
			seen.add(i.y)
			pcs[0].idle = -10
			pcs[0].send(i)
			delay(10)
		}
	}
}

private fun part2(data: IntCode) = runBlocking {

}


fun main() {
	val data: IntCode = getIntCode(23)
	part1(data)
	part2(data)
}