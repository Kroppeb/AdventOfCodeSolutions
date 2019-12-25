val reg = Regex("""^\s*(\d+)\)\sDec\s\d+\s+[0-9:]+\s+(.+?)(\s*\(AoC\+\+\))?(\s*\(Sponsor\))?\s*$""",RegexOption.MULTILINE)

fun get(day: Int): Map<String, Int> {
	val p = reg.findAll(object{}.javaClass.getResource("$day").readText()).toList().map { it.groupValues[2] to 101 - it.groupValues[1].toInt() }
	//check(p.size == 200) { "day $day contained ${p.size} entries"}
	return p.groupBy(Pair<String, Int>::first) { it.second }.mapValues { it.value.sum() }
}

fun main() {
	val days = (1..24).map(::get)
	val inter = days.fold(mutableListOf<Map<String,Int>>()){o,n->
		o.add(when(val l = o.lastOrNull()){
			null -> n
			else -> l.toMutableMap().also{
				for((k,v) in n.entries)
					it.merge(k,v,Int::plus)
			}
		}
		)
		o
	}

	for((i,map) in inter.withIndex()){
		println("${if (i < 9)" " else ""}${i+1}: ${when(val you = map["Kroppeb"]){
			null -> "NA"
			else -> "${map.count{it.value > you} + 1}"
		}} / ${map.size}")
	}
}