package helpers

import kotlin.system.exitProcess

private val int = Regex("""-?\d+""")
private val posInt = Regex(""""\d+""")
private val digit = Regex("""\d""")
private val float = Regex("""-?\d+(?:\.\d+)?""")
private val posFloat = Regex("""\d+(?:\.\d+)?""")
private val word = Regex("""[a-zA-Z]+""")


fun getData(day:Int) =
		(day::class.java.getResource("$day")
				?:day::class.java.getResource("../$day")
				)
				.readText()

fun getInts(day:Int): List<Int> {
	return int.findAll(getData(day)).map{it.value.toInt()}.toList()
}

fun getPosInts(day:Int): List<Int> {
	return posInt.findAll(getData(day)).map{it.value.toInt()}.toList()
}

fun getDigits(day:Int): List<Int> {
	return digit.findAll(getData(day)).map{it.value.toInt()}.toList()
}

fun getFloats(day:Int): List<Int> {
	return float.findAll(getData(day)).map{it.value.toInt()}.toList()
}

fun getPosFloats(day:Int): List<Int> {
	return posFloat.findAll(getData(day)).map{it.value.toInt()}.toList()
}

fun getWords(day:Int): List<Int> {
	return word.findAll(getData(day)).map{it.value.toInt()}.toList()
}
