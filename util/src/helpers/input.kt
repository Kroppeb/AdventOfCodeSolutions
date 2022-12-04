package helpers

import grid.SimpleGrid
import grid.grid

private val regexSusInt = Regex("""-?\d+-\d+""")
private val regexInt = Regex("""-?\d+""")
private val regexPosInt = Regex("""\d+""")
private val regexDigit = Regex("""\d""")
private val regexFloat = Regex("""-?\d+(?:\.\d+)?""")
private val regexPosFloat = Regex("""\d+(?:\.\d+)?""")
private val regexWord = Regex("""[a-zA-Z]+""")
private val regexAlphaNum = Regex("""[a-zA-Z0-9]+""")


fun getInt(day: Int): Int {
	return regexInt.find(getData(day))!!.value.toInt()
}

fun getPosInt(day: Int): PosInt {
	return regexPosInt.find(getData(day))!!.value.toInt()
}

fun getLong(day: Int): Long {
	return regexInt.find(getData(day))!!.value.toLong()
}

fun getPosLong(day: Int): PosLong {
	return regexPosInt.find(getData(day))!!.value.toLong()
}


fun getDigit(day: Int): Digit {
	return regexDigit.find(getData(day))!!.value.toInt()
}

fun getDouble(day: Int): Double {
	return regexFloat.find(getData(day))!!.value.toDouble()
}

fun getPosDouble(day: Int): PosDouble {
	return regexPosFloat.find(getData(day))!!.value.toDouble()
}

fun getWord(day: Int): Word {
	return regexWord.find(getData(day))!!.value
}

fun getAlphaNum(day: Int): AlphaNum {
	return regexAlphaNum.find(getData(day))!!.value
}

class Ugh;

fun getData(day: Int): String =
		if (day > 100) {
			val y = day / 100
			val d = day % 100
			(Ugh::class.java.getResource("/$y/$d")
					?:Ugh::class.java.getResource("/$y/$d.txt"))
					.readText()
		} else {
			(Ugh::class.java.getResource("/$day")
					?:Ugh::class.java.getResource("/$day.txt"))
					.readText()
		}

var hasWarnedAboutSusInt = false
fun getInts(day: Int): Ints {
	val input = getData(day)
	if (!hasWarnedAboutSusInt && regexSusInt.containsMatchIn(input)){
		System.err.println("Warning: detected sus int separation pattern. Maybe you need PosInts instead?")
		hasWarnedAboutSusInt = true
	}
	return regexInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getPosInts(day: Int): PosInts {
	return regexPosInt.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getLongs(day: Int): Longs {
	return regexInt.findAll(getData(day)).map { it.value.toLong() }.toList()
}

fun getPosLongs(day: Int): PosLongs {
	return regexPosInt.findAll(getData(day)).map { it.value.toLong() }.toList()
}

fun getDigits(day: Int): Digits {
	return regexDigit.findAll(getData(day)).map { it.value.toInt() }.toList()
}

fun getDoubles(day: Int): Doubles {
	return regexFloat.findAll(getData(day)).map { it.value.toDouble() }.toList()
}

fun getPosDoubles(day: Int): PosDoubles {
	return regexPosFloat.findAll(getData(day)).map { it.value.toDouble() }.toList()
}

fun getWords(day: Int): Words {
	return regexWord.findAll(getData(day)).map { it.value }.toList()
}

fun getAlphaNums(day: Int): AlphaNums {
	return regexAlphaNum.findAll(getData(day)).map { it.value }.toList()
}

fun getLines(day: Int): List<String> {
	val lines = getData(day).lines()
	if(lines.last().isEmpty())
		return lines.subList(0,lines.lastIndex)
	return lines
}

fun getCGrid(day: Int): CGrid = getLines(day).e().grid()

/**
 * Read in Comma (',') Separated Values
 */
fun getCSV(day: Int): CSV {
	return getLines(day).map { it.split(',') }
}
typealias CSV = List<List<String>>

/**
 * Read in Semicolon (';') Separated Values
 */
fun getSSV(day: Int): SSV {
	return getLines(day).map { it.split(';') }
}
typealias SSV = List<List<String>>

/**
 * Read in Whitespace (' ' or '\t') Separated Values
 */
fun getWSV(day: Int): WSV {
	return getLines(day).map { it.split(' ', '\t') }
}
typealias WSV = List<List<String>>

fun getIntCode(day: Int): IntCode{
	return getLongs(day).withIndex().associate { (i,v) -> i.toLong() to v }
}

fun Iterable<Int>.getPoint(): Point? {
	val iterator = this.iterator()
	if (!iterator.hasNext()) return null
	val a = iterator.next()
	if (!iterator.hasNext()) return null
	return a toP iterator.next()
}

fun Iterable<Int>.getPoint3D(): Point3D? {
	val iterator = this.iterator()
	if (!iterator.hasNext()) return null
	val a = iterator.next()
	if (!iterator.hasNext()) return null
	val b = iterator.next()
	if (!iterator.hasNext()) return null
	return a toP b toP iterator.next()
}

fun Iterable<Int>.point() = this.getPoint()!!
fun Iterable<Int>.point3D() = this.getPoint3D()!!


fun String.getInt() = regexInt.find(this)?.value?.toInt()
fun String.getPosInt() = regexPosInt.find(this)?.value?.toInt()
fun String.getLong() = regexInt.find(this)?.value?.toLong()
fun String.getPosLong() = regexPosInt.find(this)?.value?.toLong()
fun String.getDigit() = regexDigit.find(this)?.value?.toInt()
fun String.getDouble() = regexFloat.find(this)?.value?.toDouble()
fun String.getPosDouble() = regexPosFloat.find(this)?.value?.toDouble()
fun String.getWord() = regexWord.find(this)?.value
fun String.getAlphaNum() = regexAlphaNum.find(this)?.value
fun String.getPoint() = getInts().getPoint()
fun String.getPoint3D() = getInts().getPoint3D()

fun String.getInts(): List<Int> {
	if (!hasWarnedAboutSusInt && regexSusInt.containsMatchIn(this)){
		System.err.println("Warning: detected sus int separation pattern. Maybe you need PosInts instead?")
		hasWarnedAboutSusInt = true
	}
	return regexInt.findAll(this).map { it.value.toInt() }.toList()
}
fun String.getPosInts() = regexPosInt.findAll(this).map { it.value.toInt() }.toList()
fun String.getLongs() = regexInt.findAll(this).map { it.value.toLong() }.toList()
fun String.getPosLongs() = regexPosInt.findAll(this).map { it.value.toLong() }.toList()
fun String.getDigits() = regexDigit.findAll(this).map { it.value.toInt() }.toList()
fun String.getDoubles() = regexFloat.findAll(this).map { it.value.toDouble() }.toList()
fun String.getPosDoubles() = regexPosFloat.findAll(this).map { it.value.toDouble() }.toList()
fun String.getWords() = regexWord.findAll(this).map { it.value }.toList()
fun String.getAlphaNums() = regexAlphaNum.findAll(this).map { it.value }.toList()
fun String.getPoints() = getInts().chunked(2).filter{it.size == 2}.map{it.point()}
fun String.getPoints3D() = getInts().chunked(3).filter{it.size == 3}.map{it.point3D()}

fun String.int(): Int = getInt()!!
fun String.posInt(): Int = getPosInt()!!
fun String.long(): Long = getLong()!!
fun String.posLong(): Long = getPosLong()!!
fun String.digit(): Int = getDigit()!!
fun String.double(): Double = getDouble()!!
fun String.posDouble(): Double = getPosDouble()!!
fun String.word(): String = getWord()!!
fun String.alphaNum(): String = getAlphaNum()!!
fun String.point(): Point = getPoint()!!
fun String.point3D(): Point3D = getPoint3D()!!

fun String.ints(): List<Int> = getInts()
fun String.posInts(): List<Int> = getPosInts()
fun String.longs(): List<Long> = getLongs()
fun String.posLongs(): List<Long> = getPosLongs()
fun String.digits(): List<Int> = getDigits()
fun String.doubles(): List<Double> = getDoubles()
fun String.posDoubles(): List<Double> = getPosDoubles()
fun String.words(): List<String> = getWords()
fun String.alphaNums(): List<String> = getAlphaNums()
fun String.points(): List<Point> = getPoints()
fun String.points3D(): List<Point3D> = getPoints3D()

fun Iterable<String>.int():List<Int> = map{it.int()}
fun Iterable<String>.posInt():List<Int> = map{it.posInt()}
fun Iterable<String>.long():List<Long> = map{it.long()}
fun Iterable<String>.posLong():List<Long> = map{it.posLong()}
fun Iterable<String>.digit():List<Int> = map{it.digit()}
fun Iterable<String>.double():List<Double> = map{it.double()}
fun Iterable<String>.posDouble():List<Double> = map{it.posDouble()}
fun Iterable<String>.word():List<String> = map{it.word()}
fun Iterable<String>.alphaNum():List<String> = map{it.alphaNum()}
fun Iterable<String>.point():List<Point> = map{it.point()}
fun Iterable<String>.point3D():List<Point3D> = map{it.point3D()}

fun Iterable<String>.ints(): List<List<Int>> = map{it.ints()}
fun Iterable<String>.posInts(): List<List<Int>> = map{it.posInts()}
fun Iterable<String>.longs(): List<List<Long>> = map{it.longs()}
fun Iterable<String>.posLongs(): List<List<Long>> = map{it.posLongs()}
fun Iterable<String>.digits(): List<List<Int>> = map{it.digits()}
fun Iterable<String>.doubles(): List<List<Double>> = map{it.doubles()}
fun Iterable<String>.posDoubles(): List<List<Double>> = map{it.posDoubles()}
fun Iterable<String>.words(): List<List<String>> = map{it.words()}
fun Iterable<String>.alphaNums(): List<List<String>> = map{it.alphaNums()}
fun Iterable<String>.points(): List<List<Point>> = map{it.points()}
fun Iterable<String>.points3D(): List<List<Point3D>> = map{it.points3D()}


@JvmName("int2") fun Iterable<Iterable<String>>.int():List<List<Int>> = map{it.int()}
@JvmName("posInt2") fun Iterable<Iterable<String>>.posInt():List<List<Int>> = map{it.posInt()}
@JvmName("long2") fun Iterable<Iterable<String>>.long():List<List<Long>> = map{it.long()}
@JvmName("posLong2") fun Iterable<Iterable<String>>.posLong():List<List<Long>> = map{it.posLong()}
@JvmName("digit2") fun Iterable<Iterable<String>>.digit():List<List<Int>> = map{it.digit()}
@JvmName("double2") fun Iterable<Iterable<String>>.double():List<List<Double>> = map{it.double()}
@JvmName("posDouble2") fun Iterable<Iterable<String>>.posDouble():List<List<Double>> = map{it.posDouble()}
@JvmName("word2") fun Iterable<Iterable<String>>.word():List<List<String>> = map{it.word()}
@JvmName("alphaNum2") fun Iterable<Iterable<String>>.alphaNum():List<List<String>> = map{it.alphaNum()}
@JvmName("point2") fun Iterable<Iterable<String>>.point():List<List<Point>> = map{it.point()}
@JvmName("point3D2") fun Iterable<Iterable<String>>.point3D():List<List<Point3D>> = map{it.point3D()}

@JvmName("ints2") fun Iterable<Iterable<String>>.ints():List<List<List<Int>>> = map{it.ints()}
@JvmName("posInts2") fun Iterable<Iterable<String>>.posInts():List<List<List<Int>>> = map{it.posInts()}
@JvmName("longs2") fun Iterable<Iterable<String>>.longs():List<List<List<Long>>> = map{it.longs()}
@JvmName("posLongs2") fun Iterable<Iterable<String>>.posLongs():List<List<List<Long>>> = map{it.posLongs()}
@JvmName("digits2") fun Iterable<Iterable<String>>.digits():List<List<List<Int>>> = map{it.digits()}
@JvmName("doubles2") fun Iterable<Iterable<String>>.doubles():List<List<List<Double>>> = map{it.doubles()}
@JvmName("posDoubles2") fun Iterable<Iterable<String>>.posDoubles():List<List<List<Double>>> = map{it.posDoubles()}
@JvmName("words2") fun Iterable<Iterable<String>>.words():List<List<List<String>>> = map{it.words()}
@JvmName("alphaNums2") fun Iterable<Iterable<String>>.alphaNums():List<List<List<String>>> = map{it.alphaNums()}
@JvmName("points2") fun Iterable<Iterable<String>>.points():List<List<List<Point>>> = map{it.points()}
@JvmName("points3D2") fun Iterable<Iterable<String>>.points3D():List<List<List<Point3D>>> = map{it.points3D()}

fun getIntLines(day:Int) = getLines(day).map{it.getInts()}
fun getPosIntLines(day:Int) = getLines(day).map{it.getPosInts()}
fun getLongLines(day:Int) = getLines(day).map{it.getLongs()}
fun getPosLongLines(day:Int) = getLines(day).map{it.getPosLongs()}
fun getDigitLines(day:Int) = getLines(day).map{it.getDigits()}
fun getFloatLines(day:Int) = getLines(day).map{it.getDoubles()}
fun getPosFloatLines(day:Int) = getLines(day).map{it.getPosDoubles()}
fun getWordLines(day:Int) = getLines(day).map{it.getWords()}
fun getAlphaNumLines(day:Int) = getLines(day).map{it.getAlphaNums()}

typealias PosInt = Int
typealias PosLong = Long
typealias Digit = Int
typealias PosDouble = Double
typealias Word = String
typealias AlphaNum = String

typealias Ints = List<Int>
typealias PosInts = List<Int>
typealias Longs = List<Long>
typealias PosLongs = List<Long>
typealias Digits = List<Int>
typealias Doubles = List<Double>
typealias PosDoubles = List<Double>
typealias Words = List<String>
typealias AlphaNums = List<String>

typealias Lines = List<String>
typealias CGrid = SimpleGrid<Char>

typealias IntLines = List<List<Int>>
typealias PosIntLines = List<List<Int>>
typealias LongLines = List<List<Long>>
typealias PosLongLines = List<List<Long>>
typealias DigitLines = List<List<Int>>
typealias FloatLines = List<List<Double>>
typealias PosFloatLines = List<List<Double>>
typealias WordLines = List<List<String>>
typealias AlphaNumLines = List<List<String>>
