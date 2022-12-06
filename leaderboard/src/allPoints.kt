

fun main() {
	val days = (1..25).mapNotNull {get2(it) ?: return@mapNotNull null }
	days.map{it.filterValues { it.size==2 }.keys}.reduce(Iterable<String>::intersect).forEach(::println)

}