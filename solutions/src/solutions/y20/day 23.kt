@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d23

import grid.Clock
import helpers.*
import java.util.ArrayDeque

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	var s = data
	repeat(100){
		//println(s)
		var a = s[0]
		var b = s.subList(1,4)
		var rem = s.drop(4)
		var next = a
		while(a !in rem){
			if(a < 1) {
				a = 9
				continue
			}
			a--
		}
		//println(rem)
		var i = rem.indexOf(a) + 1
		var res = rem.take(i) + b + rem.drop(i) + listOf(next)
		s = res
	}
	println(s)
}

class Node(val value:Int){
	lateinit var next:Node
	lateinit var prev:Node
}

private fun part2(data: Data) {

	//var s = data
	val u = (data + (10..1_000_000)).map{Node(it)}
	val nodes = u.fold<Node, Node>(u.last()){acc:Node, v ->
		acc.next = v
		v.prev = acc
		v
	}

	val m = u.associateBy { it.value }

	val one = u.find{it.value == 1}!!

	var current = u.first()

	repeat(10_000_000){

		//var ppp = current
		//repeat(9){print(ppp.value);ppp = ppp.next}
		//println()
//		println(current == ppp)


		var a = current
		var b = current.next
		var rem = b.next.next.next
		var next = a.value
		l@ while(true){
			next--
			if(next < 1) {
				next = 1_000_001
				continue
			}
			var p = b
			for(ii in 1..3){
				//println("p:${p.value}")
				if(p.value == next)
					continue@l
				p = p.next
			}
			break
		}
		//println(next)

		var uu = m[next]!!;


		val uuNext = uu.next
		uu.next = b
		b.prev = uu

		rem.prev.next = uuNext
		uuNext.prev = rem.prev

		a.next = rem
		rem.prev = a

		var res = uu // rem.take(i) + b + rem.drop(i) + listOf(next)
		current = a.next
	}

	var ppp = one
	repeat(3){println(ppp.value);ppp = ppp.next}
	println()

	println(one.next.value.toLong() * one.next.next.value)
}

private typealias Data = Digits

fun main() {
	val data: Data = getDigits(2020_23)
	part1(data)
	part2(data)
}


fun <T> T.log(): T = also { println(this) }