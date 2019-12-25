package solutions

import helpers.*
import kotlinx.coroutines.*
import kotlin.collections.*
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

data class Op(val q:Long, val a:Long, val b:Long, val c:Long, val d:Long) : Comparable<Op> {
	/**
	 * Compares this object with the specified object for order. Returns zero if this object is equal
	 * to the specified [other] object, a negative number if it's less than [other], or a positive number
	 * if it's greater than [other].
	 */
	override fun compareTo(other: Op): Int = when(val va = a-other.a){
		0L -> when(val vb = b-other.b){
			0L -> when(val vc = c-other.c){
				0L -> when(val vd = d-other.d){
					0L -> 0
					else -> vd.toInt()
				}
				else -> vc.toInt()
			}
			else -> vb.toInt()
		}
		else -> va.toInt()
	}
}

private fun test(data: IntCode) = runBlocking {
	data.entries
			.map{a->a.value to (0..3).map{data[a.value + it.toLong()]}}
			.mapNotNull {(a,it)-> if (it.all{it!=null} && (it[0]!! > 0) && (it[0]!!%100L == 99L || it[0]!!%100L in 1L..9L)) Op(a, it[0]!!, it[1]!!, it[2]!!, it[3]!!) else null }
			.also{println(it.size)}
			.toSortedSet()
			.sortedBy { it.q }
			.forEach(::println)
}

fun main() {
	val data: IntCode = getIntCode(23)
	//part1(data)
	//part2(data)
	test(data)
}