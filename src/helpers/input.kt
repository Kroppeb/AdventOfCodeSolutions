package helpers

import kotlin.system.exitProcess

private val regexInt = Regex("""-?\d+""")
private val regexPosInt = Regex(""""\d+""")
private val regexDigit = Regex("""\d""")
private val regexFloat = Regex("""-?\d+(?:\.\d+)?""")
private val regexPosFloat = Regex("""\d+(?:\.\d+)?""")
private val regexWord = Regex("""[a-zA-Z]+""")
private val regexAlphaNum = Regex("""[a-zA-Z0-9]+""")


fun getData(day: Int): String =
		if (day > 100) {
			val y = day / 100
			val d = day % 100
			day::class.java.getResource("$y/$d")
					.readText()
		} else {
			day::class.java.getResource("$day")
					.readText()
		}

fun getInts(day: Int): List<Int> {
	return regexInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getPosInts(day: Int): List<Int> {
	return regexPosInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getDigits(day: Int): List<Int> {
	return regexDigit.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getFloats(day: Int): List<Double> {
	return regexFloat.findAll(getData(day)).map { it.value.toDouble() }.toList()
}

fun getPosFloats(day: Int): List<Double> {
	return regexPosFloat.findAll(getData(day)).map { it.value.toDouble() }.toList()
}

fun getWords(day: Int): List<String> {
	return regexWord.findAll(getData(day)).map { it.value }.toList()
}

fun getAlphaNums(day: Int): List<String> {
	return regexAlphaNum.findAll(getData(day)).map { it.value }.toList()
}

fun getLines(day: Int): List<String> {
	return getData(day).lines()
}

/**
 * Read in Comma (',') Separated Values
 */
fun getCSV(day: Int): List<List<String>> {
	return getLines(day).map { it.split(',') }
}

/**
 * Read in Semicolon (';') Separated Values
 */
fun getSSV(day: Int): List<List<String>> {
	return getLines(day).map { it.split(';') }
}

/**
 * Read in Whitespace (' ' or '\t') Separated Values
 */
fun getWSV(day: Int): List<List<String>> {
	return getLines(day).map { it.split(' ', '\t') }
}

fun String.getInt() = regexInt.find(this)?.value?.toInt()
fun String.getPosInt() = regexPosInt.find(this)?.value?.toInt()
fun String.getDigit() = regexDigit.find(this)?.value?.toInt()
fun String.getFloat() = regexFloat.find(this)?.value?.toDouble()
fun String.getPosFloat() = regexPosFloat.find(this)?.value?.toDouble()
fun String.getWord() = regexWord.find(this)?.value
fun String.getAlphaNum() = regexAlphaNum.find(this)?.value

fun String.getInts() = regexInt.findAll(this).map { it.value.toInt() }.toList()
fun String.getPosInts() = regexPosInt.findAll(this).map { it.value.toInt() }.toList()
fun String.getDigits() = regexDigit.findAll(this).map { it.value.toInt() }.toList()
fun String.getFloats() = regexFloat.findAll(this).map { it.value.toDouble() }.toList()
fun String.getPosFloats() = regexPosFloat.findAll(this).map { it.value.toDouble() }.toList()
fun String.getWords() = regexWord.findAll(this).map { it.value }.toList()
fun String.getAlphaNums() = regexAlphaNum.findAll(this).map { it.value }.toList()

fun getIntLines(day:Int) = getLines(day).map{it.getInts()}
fun getPosIntLines(day:Int) = getLines(day).map{it.getPosInts()}
fun getDigitLines(day:Int) = getLines(day).map{it.getDigits()}
fun getFloatLines(day:Int) = getLines(day).map{it.getFloats()}
fun getPosFloatLines(day:Int) = getLines(day).map{it.getPosFloats()}
fun getWordLines(day:Int) = getLines(day).map{it.getWords()}
fun getAlphaNumLines(day:Int) = getLines(day).map{it.getAlphaNums()}

