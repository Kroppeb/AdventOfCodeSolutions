package solutions

import helpers.*
import kotlinx.coroutines.*
import java.util.*

private fun part1(data: IntCode)= runBlocking {
	val map= mutableMapOf<Point,Long>()
	var pos = 0 toP 0
	val st = ArrayDeque<Point>()
	val t = listOf(1,3,2,4L)
	var found = false
	var maxd = 0
	runComputer(data).run{
		while(true){
			val d = pos.getQuadNeighbours().find{it !in map.keys}
			maxd = max(maxd, st.size)
			if(d == null){
				if(st.size == 0)
				{
					println(maxd)
					error("Done")
				}
				val q = st.pop()
				val dir = pos.getQuadNeighbours().indexOf(q)
				send(t[dir])
				val r = output.receive()
				pos = q
			} else {
				val dir = pos.getQuadNeighbours().indexOf(d)
				send(t[dir])
				val r = output.receive()
				map[d] = r
				when(r){
					0L -> {}
					1L -> {
						st.push(pos)
						pos = d
					}
					2L -> {

						println(st.size + 1)
						// clear map and keep max st
						map.clear()
						st.clear()
						pos = 0 toP 0
						map[pos] = 2
					}
				}
			}

		}


	}
}

private fun part2(data: IntCode) = runBlocking {
	runComputer(data).run {


	}
}



fun main() {
	val data: IntCode = getIntCode(15)
	part1(data)
	part2(data)
}