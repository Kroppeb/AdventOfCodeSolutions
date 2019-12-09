package solutions

import helpers.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.math.*

fun permutations() = (0..4).cartesianProduct((0..4)).flatMap { (a,b) -> (0..4).cartesianProduct((0..4))
		.flatMap{(x,y) -> (0..4).map{listOf(a,b,x,y,it)}} }.filter { it.areDistinct() }


private fun part1(data: List<Int>) {
	val r = permutations().map {p ->
		p.fold(0) {i, s ->  IntComputer(data, listOf(s, i)).let{it.run(); it.last} }
	}.max()
	println(r)
}

private fun part2(data: List<Int>) {

	val r = permutations().map{it.map{it+5}}.map {p ->
		val inputs = (0..4).map{i-> ArrayBlockingQueue<Int>(10).also{it.add(p[i])} }
		var inp = 0

		val computers = inputs.map{IntComputer(data.toMutableList(), generateSequence {it.poll()?:-1337  }.iterator() ).also{it.run()} }
		while(!computers.last().finished){
			inp = inputs.zip(computers).fold(inp){i, q -> q.first.add(i)
				q.second.run()
			q.second.last
			}
		}
		inp
	}.max()
	println(r)
}

fun main() {
	/*
	val test = get(1019_07)
	part1(test)
	part2(test)
*/
	val data = getInts(7)
	part1(data)
	part2(data)
}