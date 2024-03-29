@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d11c
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toPI
import kotlinx.coroutines.runBlocking

private fun part1(data: IntCode)= runBlocking {
	var loc = 0 toPI 0
	var dir = 0
	val paints = mutableSetOf<PointI>()
	val colored = mutableSetOf<PointI>()
	val computer = runComputer(data).run{
		while(!isHalted()){
			if(loc in colored){
				send(1)
			} else {
				send (0)
			}
			paints.add(loc)
			if(output.receive() == 1L){
				colored.add(loc)
			} else {
				colored.remove(loc)
			}
			if(output.receive() == 1L){
				dir+=1
				dir%=4
			} else {
				dir+=3
				dir%=4
			}
			loc = loc.getQuadNeighbours()[dir]
		}
	}
	println(paints.size)
}
private fun part2(data: IntCode) = runBlocking {
	var loc = 0 toPI 0
	var dir = 0
	val colored = mutableSetOf<PointI>(loc)
	val computer = runComputer(data).run{
		while(!isHalted()){
			if(loc in colored){
				send(1)
			} else {
				send (0)
			}
			if(output.receive() == 1L){
				colored.add(loc)
			} else {
				colored.remove(loc)
			}
			if(output.receive() == 1L){
				dir+=1
				dir%=4
			} else {
				dir+=3
				dir%=4
			}
			loc = loc.getQuadNeighbours()[dir]
		}
	}
	val ll = colored.reduce{a,b->a.min(b)}
	val ur = colored.reduce{a,b->a.max(b)}
	for(i in (ll.x..ur.x).reversed() ){
		for (j in (ll.y..ur.y)){
			if(i toPI j in colored)
				print("██")
			else
				print("  ")
		}
		println()
	}
}

fun main() {
	val data: IntCode = getIntCode(2019_11)
	part1(data)
	part2(data)
}