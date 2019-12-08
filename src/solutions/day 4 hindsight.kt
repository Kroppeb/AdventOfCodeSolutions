package solutions

import collections.counter
import helpers.*

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
	// or
	println(paswords.map{it.toString().toList()}.count{
		it.rleEncode { c, i -> i >= 2 }.any{it} &&
				it.zipWithNext { a, b -> a<=b }.all{it}
	})
}

private fun part2(){
	val paswords = 145852..616942
	println(paswords.map{it.toString().toList()}.filter{
		it.groupBy { it }.any{(k,v)->v.size==2}
				&&
				it.sorted() == it
	}.count())
	// or
	println(paswords.map{it.toString().toList()}.count{
		it.rleEncode { c, i -> i == 2 }.any{it} &&
				it.zipWithNext { a, b -> a<=b }.all{it}
	})

}


fun main(){
	part1()
	part2()
}