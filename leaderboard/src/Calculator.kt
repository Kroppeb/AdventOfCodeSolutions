val year = "2022"
val user = "Kroppeb"
val target = 50


val reg = Regex("""^\s*(\d+)\)\sDec\s\d+\s+[0-9:]+\s+(.+?)(\s*\(AoC\+\+\))?(\s*\(Sponsor\))?\s*$""", RegexOption.MULTILINE)

fun get(day: Int): Map<String, Int>? {
    val resource = object {}.javaClass.getResource("$year/$day")
            ?: object {}.javaClass.getResource("$year/$day.txt")
            ?: return null;
    val p = reg.findAll(resource.readText()).toList().map { it.groupValues[2] to 101 - it.groupValues[1].toInt() }
    //check(p.size == 200) { "day $day contained ${p.size} entries"}
    return p.groupBy(Pair<String, Int>::first) { it.second }.mapValues { it.value.sum() }
}

fun get2(day: Int): Map<String, List<Int>>? {
	val resource = object {}.javaClass.getResource("$year/$day")
		?: object {}.javaClass.getResource("$year/$day.txt")
		?: return null;
	val p = reg.findAll(resource.readText()).toList().map { it.groupValues[2] to 101 - it.groupValues[1].toInt() }
	//check(p.size == 200) { "day $day contained ${p.size} entries"}
	return p.groupBy(Pair<String, Int>::first) { it.second }.mapValues { it.value }
}

fun main() {
    val days = (1..25).mapNotNull { it to (get(it) ?: return@mapNotNull null) }
    val inter = days.fold(mutableListOf<Pair<Int, Map<String, Int>>>()) { o, (i, n) ->
        o.add(i to when (val l = o.lastOrNull()) {
            null -> n
            else -> l.second.toMutableMap().also {
                for ((k, v) in n.entries)
                    it.merge(k, v, Int::plus)
            }
        })
        o
    }

    for ((i, map) in inter) {
        println("${if (i <= 9) " " else ""}${i}: ${
            when (val you = map[user]) {
                null -> "NA"
                else -> "${map.count { it.value > you } + 1}"
            }
        } / ${map.size} (${map[user]?:0} / ${map.values.sortedDescending().take(target).last()})")
    }
}