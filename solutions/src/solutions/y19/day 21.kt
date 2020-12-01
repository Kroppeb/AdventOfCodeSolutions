@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d21

import helpers.*
import kotlinx.coroutines.*

private val code1 = """NOT C J
	|AND D J
	|NOT A T
	|OR T J
	|WALK
	|
""".trimMargin()

private val code2 = """NOT C J
	|AND D J
	|NOT H T
	|NOT T T
	|OR E T
	|AND T J
	|NOT A T
	|OR T J
	|NOT B T
	|NOT T T
	|OR E T
	|NOT T T
	|OR T J
	|RUN
	|
""".trimMargin()

private fun part1(data: IntCode) = runBlocking {
	val sp = runComputer(data, code1.map { it.toInt().toLong() }).run {
		var last = 0L
		for (i in output) {
			print(i.toChar())
			last = i
		}
		println(last)
	}
}

private fun part2(data: IntCode) = runBlocking {
	val sp = runComputer(data, code2.map { it.toInt().toLong() }).run {
		var last = 0L
		for (i in output) {
			print(i.toChar())
			last = i
		}
		println(last)
	}

}


fun main() {
	val data: IntCode = getIntCode(2019_21)
	part1(data)
	part2(data)
}