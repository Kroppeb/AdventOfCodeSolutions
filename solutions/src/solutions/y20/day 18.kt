@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d18

import me.kroppeb.aoc.helpers.*

import java.util.ArrayDeque

private val xxxxx = Clock(6, 3);

fun solve1(l:String){

}

private fun part1(data: Data) {
	data.map{line ->
		val l =line.split(" ").e().flatten().map{it.toString()}
		val m = ArrayDeque<String>()
		for(ii in l){
			var i = ii
			loop@ while(true) {
				//println(m)
				when (i) {
					"+" -> m.push("+")
					"*" -> m.push("*")
					"(" -> m.push("(")
					")" -> {
						i = m.pop()
						m.pop()
						continue@loop
					}
					else -> {
						val p = m.peek()
						when (p) {
							"*" -> {
								m.pop()
								val a = m.pop()
								m.push((a.toLong() * i.toLong()).toString())
							}
							"+" -> {
								m.pop()
								val a = m.pop()
								m.push((a.toLong() + i.toLong()).toString())
							}
							"(" -> m.push(i)
							else -> m.push(i)
						}
					}
				}
				break
			}
		}
		//println(m)

		val u = m.pop()
		//println(u)
		u.toLong()
	}.sum().log()
}


private fun part2(data: Data) {

	data.map{line ->
		val l =line.split(" ").e().flatten().map{it.toString()}
		val m = ArrayDeque<String>()
		for(ii in l){
			var i = ii
			loop@ while(true) {
				//println(m)
				when (i) {
					"+" -> m.push("+")
					"*" -> m.push("*")
					"(" -> m.push("(")
					")" -> {
						var p = m.pop().toLong()
						while(m.pop() == "*"){
							p *= m.pop().toLong()
						}
						//println(p)
						i = p.toString()
						continue@loop
					}
					else -> {
						val p = m.peek()
						when (p) {
							"*" -> {
								m.push(i)
							}
							"+" -> {
								m.pop()
								val a = m.pop()
								m.push((a.toLong() + i.toLong()).toString())
							}
							"(" -> m.push(i)
							else -> m.push(i)
						}
					}
				}
				break
			}
		}
		//println(m)

		val u = m.map { if(it == "*")1L else it.toLong()  }.reduce{a,b->a*b}
		//println(u)
		u
	}.sum().log()
}


private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_18)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)