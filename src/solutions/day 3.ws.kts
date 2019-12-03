import helpers.*
import java.lang.Math.abs

val ints = getData(3).split("\r\n").map{a->a.split(",").map{it[0] to it.substring(1).toInt()}}

// part1
val w1 = mutableSetOf<Pair<Int,Int>>()
var x = 0
var y = 0
for ((c, d) in ints[0]){
	val (dx, dy) = when(c){
		'R' -> 1 to 0
		'U' -> 0 to 1
		'D' -> 0 to -1
		'L' -> -1 to 0
		else -> error("")
	}

	for(dd in 1 .. d){
		w1.add(x+dd*dx to y+dd*dy)
	}
	x += d*dx
	y += d*dy
}

x=0
y=0
val w2 = mutableSetOf<Pair<Int,Int>>()
for ((c, d) in ints[1]){
	val (dx, dy) = when(c){
		'R' -> 1 to 0
		'U' -> 0 to 1
		'D' -> 0 to -1
		'L' -> -1 to 0
		else -> error("")
	}

	for(dd in 1 .. d){
		w2.add(x+dd*dx to y+dd*dy)
	}
	x += d*dx
	y += d*dy
}

val q = w1.toList().map { it}
q