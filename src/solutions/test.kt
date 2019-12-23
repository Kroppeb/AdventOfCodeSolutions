/*package solutions

import helpers.runComputer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Adder(val scope: CoroutineScope) {
	var result = 0L
	suspend fun send(i: Long) {
		result += i
	}


	init {
	}
}

fun CoroutineScope.b(data: List<Long>): Adder {
	return Adder(this)
}

inline fun search(predicate: (Int) -> Boolean): Int {
	// if's / while loops increases compile time exponentially

	if(predicate(0))
		if(predicate(1))
			if(predicate(1))
				if(predicate(1))
					error("test")

	if(predicate(0))
		if(predicate(1))
			if(predicate(1))
				if(predicate(1))
					error("test")




	return 0
}

fun main() = runBlocking {

	val data = listOf(3, 0, 3, 1, 1, 0, 1, 0, 4, 0, 99L)

	var x = search{ x ->
		(0..x*2).any { y ->
			b(data).run {
				send(x.toLong())
				send(y.toLong())
				result > 0L
			} &&
					b(data).run {
						send(x.toLong())
						send(y.toLong() + 99)
						result > 0L
					}
					&&
					b(data).run {
						send(x.toLong()+99)
						send(y.toLong())
						result > 0L
					}
					&&
					b(data).run {
						send(x.toLong()+99)
						send(y.toLong() + 99)
						result > 0L
					}
		}
	}


	val y = search { y ->

		b(data).run {
			send(x.toLong())
			send(y.toLong())
			result > 500L
		} &&
				b(data).run {
					send(x.toLong())
					send(y.toLong() + 99)
					result > 500L
				} &&
				b(data).run {
					send(x.toLong())
					send(y.toLong() + 99)
					result > 500L
				}
		// adding more "and cases" seems to increase compile time linearly
		// but results less quickly in a MethodTooLargeException. allowing for even slower compile times
	}
	println("$x,$0")
}
*/