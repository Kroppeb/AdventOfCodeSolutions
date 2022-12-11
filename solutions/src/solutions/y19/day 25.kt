@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d25

import me.kroppeb.aoc.helpers.*
import kotlinx.coroutines.*

private fun part1(data: IntCode) = runBlocking(Dispatchers.Default) {
	runComputer(data).run{
		launch {
			var last = ""
			for (i in output) {
				print(i.toChar())
			}
		}

		launch{
			while (true){
				delay(1000)
				val p = (readLine()?:error(""))+"\n"
				//print(p)
				p.forEach { send(it.toLong()) }
			}
		}
	}

}

private fun part2(data: IntCode) = runBlocking {
}


fun main() {
	val data: IntCode = getIntCode(2019_25)
	part1(data)
	part2(data)
}