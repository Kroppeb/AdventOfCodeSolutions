@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d13

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toPI
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException

private fun part1(data: IntCode)= runBlocking(Dispatchers.Default) {
	val d = data.toMutableMap()
	d[0] = 2L
	var score = 0L
	val tiles = mutableMapOf<PointI, Long>()
	var ball = 0
	var paddle = 0
	runComputer(d).run{
		launch {
			try {
				while (true) {
					val y = output.receive()
					val x = output.receive()
					val v = output.receive()

					val p = x.toInt() toPI y.toInt()
					if (p == 0 toPI -1)
						score = v
					else
						tiles[p] = v


				}
			}catch (e: ClosedReceiveChannelException){
				input!!.close()
			}
		}

		launch{
			try {
				while (true) {
					/*
				if(ball < paddle)
					send(-1)
				else if(ball > paddle)
					send(1)
				else
					send(0)

				 */
					delay(1000)
					println()
					println(score)
					println()
					val ll = tiles.keys.reduce { a, b -> a.min(b) }
					val ur = tiles.keys.reduce { a, b -> a.max(b) }
					var u = 0
					for (i in (ll.x..ur.x)) {
						for (j in (ll.y..ur.y)) {
							print(when (tiles[i toPI j] ?: 0) {
								1L -> '#'
								2L -> {
									u += 1
									'X'
								}
								3L -> {
									paddle = j
									'='
								}
								4L -> {
									ball = j
									'O'
								}
								else -> ' '
							})
						}
						println()
					}

					if(u == 0)
						error("")

					val q = readLine()?.getInt() ?: 0
						send(q.toLong())
				}
			}catch (e:Exception){

			}
		}


	}
	println(tiles.values.count{it == 2L})

}

private fun part2(data: IntCode) = runBlocking {

}


fun main() {
	val data: IntCode = getIntCode(2019_13)
	part1(data)
	part2(data)
}