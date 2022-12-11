@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d22

import me.kroppeb.aoc.helpers.*
import kotlin.collections.*
import java.math.BigInteger

private fun part1(data: Data) {
	var pos = 2020L
	val l = 119315717514047
	repeat(50030000) {
		for (i in data) {
			val p = i.split(' ')
			when (p[0]) {
				"deal" -> pos = when (val q = p.last().toIntOrNull()) {
					null -> l - pos - 1
					else -> (q * pos) % l
				}
				"cut" -> {
					val q = p.last().toInt()
					pos = (l + pos - q) % l
				}
			}
		}
		if (pos == 2020L) {
			println(it + 1)
		}
	}
	print("no")
}

private tailrec fun getAtPos(data: Iterator<Pair<Int, Int>>, pos: Long, l: Long): Long =
		if (data.hasNext()) {
			val (a, q) = data.next()
			when (a) {
				0 -> getAtPos(data, l - pos - 1, l)
				1 -> getAtPos(data, (q * pos) % l, l)
				2 -> getAtPos(data, (l + pos - q) % l, l)
				else -> error("")
			}
		} else {
			pos
		}

private fun getOff(a: BigInteger): BigInteger {
	return a.modPow(l2, ll)!!
}

val l = 119315717514047L
val repeat = 101741582076661L
val ll = BigInteger.valueOf(l)

val m = BigInteger.valueOf(-1)
val l1 = BigInteger.valueOf(l - 1)
val l2 = BigInteger.valueOf(l - 2)
val mm = m to l1

fun comb(p1: Pair<BigInteger, BigInteger>, p2: Pair<BigInteger, BigInteger>): Pair<BigInteger, BigInteger> {
	val (off, st) = p1
	val (nOff, nst) = p2
	return (off + st * nOff) % ll to (st * nst) % ll
}

fun pow(p1: Pair<BigInteger, BigInteger>, t : Long): Pair<BigInteger, BigInteger> {
	if(t == 1L){
		return p1
	}else
	if(t % 2 == 0L){
		val q = pow(p1, t / 2)
		return comb(q, q)
	}else{
		val q = pow(p1, t / 2)
		return comb(p1, comb(q, q))
	}

}

private fun part2(data: Data) {

	var card = BigInteger.valueOf(2020)

	val pb = data.map {
		val p = it.split(' ')
		when (p[0]) {
			"deal" -> when (val q = p.last().toIntOrNull()) {
				null -> mm
				else -> BigInteger.ZERO to getOff(BigInteger.valueOf(q.toLong()))
			}
			"cut" -> {
				val q = p.last().toInt()
				BigInteger.valueOf(q.toLong()) to BigInteger.ONE
			}
			else -> error("")
		}
	}.fold(BigInteger.ZERO to BigInteger.ONE) { p1, p2 ->
		comb(p1, p2)
	}


	val(off, st) = pow(pb, repeat)


	println((( ll + (off + st * card)) % ll) % ll)
	//println((0 until l).map{(( ll + (off + st * BigInteger.valueOf(it))) % ll) % ll}.indexOf(BigInteger.valueOf(2019)))
}

typealias Data = List<String>

fun main() {
	val data: Data = getLines(2019_22)
	part2(data)
}