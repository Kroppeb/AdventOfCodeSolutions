package solutions.y17.d07c


import me.kroppeb.aoc.helpers.*

private fun part1(data: List<List<String>>){
	val ps = data.map{it[0]}.toSet()
	val pps = data.flatMap { it.drop(2) }.toSet()
	println(ps - pps)
}

private fun part2(data:List<List<String>>){
	val w = data.map { it[0] to it[1].getInt()!! }.toMap()
	val c = data.map { it[0] to it.drop(2) }.toMap()

	fun check(cur:String) : Int{
		if (c[cur]!!.isEmpty())
			return w[cur]!!
		val childs = c[cur]!!.groupBy{check(it)}
		if(childs.size == 1) {
			//println(childs.toList()[0].let { (f, s) -> s.size * f } + w[cur]!!)
			return childs.toList()[0].let { (f, s) -> s.size * f } + w[cur]!!
		}else{
			var (a,b) = childs.toList()
			if (a.second.size > 1){
				val x = a
				a = b
				b = x
			}
			error("${a.second[0]}: ${b.first - a.first + w[a.second[0]]!!}")
		}
	}

	check("dtacyn")
}


fun main(){
	val data = getAlphaNumLines(2017_07)
	part1(data)
	part2(data)





















}











