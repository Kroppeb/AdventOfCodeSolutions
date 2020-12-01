package helpers

import kotlin.system.exitProcess

private val regexInt = Regex("""-?\d+""")
private val regexPosInt = Regex(""""\d+""")
private val regexDigit = Regex("""\d""")
private val regexFloat = Regex("""-?\d+(?:\.\d+)?""")
private val regexPosFloat = Regex("""\d+(?:\.\d+)?""")
private val regexWord = Regex("""[a-zA-Z]+""")
private val regexAlphaNum = Regex("""[a-zA-Z0-9]+""")


fun getInt(day: Int): Int {
	return regexInt.find(getData(day))!!.value.toInt()
}

fun getPosInt(day: Int): Int {
	return regexPosInt.find(getData(day))!!.value.toInt()
}

fun getLong(day: Int): Long {
	return regexInt.find(getData(day))!!.value.toLong()
}

fun getPosLong(day: Int): Long {
	return regexPosInt.find(getData(day))!!.value.toLong()
}


fun getDigit(day: Int): Int {
	return regexDigit.find(getData(day))!!.value.toInt()
}

fun getFloat(day: Int): Double {
	return regexFloat.find(getData(day))!!.value.toDouble()
}

fun getPosFloat(day: Int): Double {
	return regexPosFloat.find(getData(day))!!.value.toDouble()
}

fun getWord(day: Int): String {
	return regexWord.find(getData(day))!!.value
}

fun getAlphaNum(day: Int): String {
	return regexAlphaNum.find(getData(day))!!.value
}

fun getData(day: Int): String =
		if (day > 100) {
			val y = day / 100
			val d = day % 100
			day::class.java.getResource("/$y/$d")
					.readText()
		} else {
			day::class.java.getResource("/$day")
					.readText()
		}

fun getInts(day: Int): List<Int> {
	return regexInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getPosInts(day: Int): List<Int> {
	return regexPosInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getLongs(day: Int): List<Long> {
	return regexInt.findAll(getData(day)).map { it.value.toLong() }.toList()
}

fun getPosLongs(day: Int): List<Long> {
	return regexPosInt.findAll(getData(day)).map { it.value.toLong() }.toList()
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
	return getData(day).lines().map{it}
}

/**
 * Read in Comma (',') Separated Values
 */
fun getCSV(day: Int): List<List<String>> {
	return getLines(day).map { it.split(',') }
}
typealias CSV = List<List<String>>

/**
 * Read in Semicolon (';') Separated Values
 */
fun getSSV(day: Int): List<List<String>> {
	return getLines(day).map { it.split(';') }
}
typealias SSV = List<List<String>>

/**
 * Read in Whitespace (' ' or '\t') Separated Values
 */
fun getWSV(day: Int): List<List<String>> {
	return getLines(day).map { it.split(' ', '\t') }
}
typealias WSV = List<List<String>>

fun getIntCode(day: Int): IntCode{
	return getLongs(day).withIndex().associate { (i,v) -> i.toLong() to v }
}


fun String.getInt() = regexInt.find(this)?.value?.toInt()
fun String.getPosInt() = regexPosInt.find(this)?.value?.toInt()
fun String.getLong() = regexInt.find(this)?.value?.toLong()
fun String.getPosLong() = regexPosInt.find(this)?.value?.toLong()
fun String.getDigit() = regexDigit.find(this)?.value?.toInt()
fun String.getFloat() = regexFloat.find(this)?.value?.toDouble()
fun String.getPosFloat() = regexPosFloat.find(this)?.value?.toDouble()
fun String.getWord() = regexWord.find(this)?.value
fun String.getAlphaNum() = regexAlphaNum.find(this)?.value

fun String.getInts() = regexInt.findAll(this).map { it.value.toInt() }.toList()
fun String.getPosInts() = regexPosInt.findAll(this).map { it.value.toInt() }.toList()
fun String.getLongs() = regexInt.findAll(this).map { it.value.toLong() }.toList()
fun String.getPosLongs() = regexPosInt.findAll(this).map { it.value.toLong() }.toList()
fun String.getDigits() = regexDigit.findAll(this).map { it.value.toInt() }.toList()
fun String.getFloats() = regexFloat.findAll(this).map { it.value.toDouble() }.toList()
fun String.getPosFloats() = regexPosFloat.findAll(this).map { it.value.toDouble() }.toList()
fun String.getWords() = regexWord.findAll(this).map { it.value }.toList()
fun String.getAlphaNums() = regexAlphaNum.findAll(this).map { it.value }.toList()

fun getIntLines(day:Int) = getLines(day).map{it.getInts()}
fun getPosIntLines(day:Int) = getLines(day).map{it.getPosInts()}
fun getLongLines(day:Int) = getLines(day).map{it.getLongs()}
fun getPosLongLines(day:Int) = getLines(day).map{it.getPosLongs()}
fun getDigitLines(day:Int) = getLines(day).map{it.getDigits()}
fun getFloatLines(day:Int) = getLines(day).map{it.getFloats()}
fun getPosFloatLines(day:Int) = getLines(day).map{it.getPosFloats()}
fun getWordLines(day:Int) = getLines(day).map{it.getWords()}
fun getAlphaNumLines(day:Int) = getLines(day).map{it.getAlphaNums()}

typealias PosInt = Int
typealias PosLong = Long
typealias Digit = Int
typealias Float = Double
typealias PosFloat = Double
typealias Word = String
typealias AlphaNum = String

typealias Ints = List<Int>
typealias PosInts = List<Int>
typealias Longs = List<Long>
typealias PosLongs = List<Long>
typealias Digits = List<Int>
typealias Floats = List<Double>
typealias PosFloats = List<Double>
typealias Words = List<String>
typealias AlphaNums = List<String>


typealias IntLines = List<List<Int>>
typealias PosIntLines = List<List<Int>>
typealias LongLines = List<List<Long>>
typealias PosLongLines = List<List<Long>>
typealias DigitLines = List<List<Int>>
typealias FloatLines = List<List<Double>>
typealias PosFloatLines = List<List<Double>>
typealias WordLines = List<List<String>>
typealias AlphaNumLines = List<List<String>>
