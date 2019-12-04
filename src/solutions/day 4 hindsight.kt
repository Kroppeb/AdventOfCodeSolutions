package solutions

import Collections.Counter
import Collections.counter
import helpers.*
import kotlin.math.*

private fun part1(){
	val paswords = 145852..616942
	println(paswords.map{it.toString().toList()}.filter{
		it.groupBy { it }.any{(k,v)->v.size>1}
				&&
				it.sorted() == it
	}.count())
	// or
	println(paswords.map{it.toString().toList()}.filter{
		it.counter().counts.any{(k,v)->v>1}
				&&
				it.sorted() == it
	}.count())
}

private fun part2(){
	val paswords = 145852..616942
	println(paswords.map{it.toString().toList()}.filter{
		it.groupBy { it }.any{(k,v)->v.size==2}
				&&
				it.sorted() == it
	}.count())

}


fun main(){
	part1()
	part2()
}