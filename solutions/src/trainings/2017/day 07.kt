package trainings.`2017`


import helpers.*

private fun part1(data: List<List<Any?>>){
	val ps = data.map{it[0]}.toSet()
	val pps = data.flatMap { it.drop(2) }.toSet()
	println(ps - pps)
}

private fun part2(data:List<List<Any?>>){
	val w = data.map { it[0].toString() to it[1] as Int }.toMap()
	val c = data.map { it[0].toString() to it.drop(3).map{it.toString()} }.toMap()

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
	val data = getWSV(2017_07).map2{it.getWord()?:it.getInt()}
	part1(data)





















}











