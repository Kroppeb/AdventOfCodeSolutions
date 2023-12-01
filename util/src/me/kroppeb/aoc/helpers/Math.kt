package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.context.IterableOpps.isEmpty
import me.kroppeb.aoc.helpers.context.SintOpps.isZero
import me.kroppeb.aoc.helpers.sint.*
import java.lang.IllegalArgumentException


@Deprecated("Use Sint and/or traits instead")
fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)

@Deprecated("Use Sint and/or traits instead")
fun gcd(a: Long, b: Long): Long = if (a == 0L) b else gcd(b % a, a)

fun gcd(a: Sint, b: Sint): Sint = if (a.isZero()) b else gcd(b % a, a)

fun egcd(a: Int, b: Int): Triple<Int, Int, Int> {
	if (a == 0) return Triple(b, 0, 1)
	val (g, x, y) = egcd(b % a, a)
	return Triple(g, y - (b / a) * x, x)
}

fun egcd(a: Long, b: Long): Triple<Long, Long, Long> {
	if (a == 0L) return Triple(b, 0, 1)
	val (g, x, y) = egcd(b % a, a)
	return Triple(g, y - (b / a) * x, x)
}

fun egcd(a: Sint, b: Sint): Triple<Sint, Sint, Sint> {
	if (a.isZero()) return Triple(b, 0.s, 1.s)
	val (g, x, y) = egcd(b mod a, a)
	return Triple(g, y - (b.floorDiv(a)) * x, x)
}

@Suppress("RemoveExplicitTypeArguments")
@Deprecated("Use Sint and/or traits instead")
fun Iterable<Int>.gcd(): Int = reduce<Int, Int>(::gcd)

@JvmName("longGcd")
@Suppress("RemoveExplicitTypeArguments")
@Deprecated("Use Sint and/or traits instead")
fun Iterable<Long>.gcd(): Long = reduce<Long, Long>(::gcd)

//fun gcd(vararg e:Int)

fun lcm(a: Int, b: Int): Int = a / gcd(a, b) * b
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

@Suppress("RemoveExplicitTypeArguments")
fun Iterable<Int>.lcm(): Int = reduce<Int, Int>(::lcm)

@JvmName("longLcm")
@Suppress("RemoveExplicitTypeArguments")
fun Iterable<Long>.lcm(): Long = reduce<Long, Long>(::lcm)

fun <T : Comparable<T>> Iterable<T>.min(): T = minOrNull()!!
fun <T : Comparable<T>> Iterable<T>.max(): T = maxOrNull()!!

fun <T : Comparable<T>> Array<T>.min(): T = minOrNull()!!
fun <T : Comparable<T>> Array<T>.max(): T = maxOrNull()!!

fun IntArray.min(): Int = minOrNull()!!
fun IntArray.max(): Int = maxOrNull()!!

fun LongArray.min(): Long = minOrNull()!!
fun LongArray.max(): Long = maxOrNull()!!

fun DoubleArray.min(): Double = minOrNull()!!
fun DoubleArray.max(): Double = maxOrNull()!!

inline fun <C, T : Comparable<T>> Iterable<C>.minBy(b: (C) -> T): C = minByOrNull(b)!!
inline fun <C, T : Comparable<T>> Iterable<C>.maxBy(b: (C) -> T): C = maxByOrNull(b)!!

inline fun <C, T : Comparable<T>> Iterable<C>.allMinBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C, T : Comparable<T>> Iterable<C>.allMaxBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C, T : Comparable<T>> Array<C>.minBy(b: (C) -> T): C = minByOrNull(b)!!
inline fun <C, T : Comparable<T>> Array<C>.maxBy(b: (C) -> T): C = maxByOrNull(b)!!

inline fun <C, T : Comparable<T>> Array<C>.allMinBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C, T : Comparable<T>> Array<C>.allMaxBy(b: (C) -> T): List<C> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C : Comparable<C>> IntArray.minBy(b: (Int) -> C): Int = minByOrNull(b)!!
inline fun <C : Comparable<C>> IntArray.maxBy(b: (Int) -> C): Int = maxByOrNull(b)!!

inline fun <C : Comparable<C>> IntArray.allMinBy(b: (Int) -> C): List<Int> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C : Comparable<C>> IntArray.allMaxBy(b: (Int) -> C): List<Int> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C : Comparable<C>> LongArray.minBy(b: (Long) -> C): Long = minByOrNull(b)!!
inline fun <C : Comparable<C>> LongArray.maxBy(b: (Long) -> C): Long = maxByOrNull(b)!!

inline fun <C : Comparable<C>> LongArray.allMinBy(b: (Long) -> C): List<Long> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C : Comparable<C>> LongArray.allMaxBy(b: (Long) -> C): List<Long> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <C : Comparable<C>> DoubleArray.minBy(b: (Double) -> C): Double = minByOrNull(b)!!
inline fun <C : Comparable<C>> DoubleArray.maxBy(b: (Double) -> C): Double = maxByOrNull(b)!!

inline fun <C : Comparable<C>> DoubleArray.allMinBy(b: (Double) -> C): List<Double> {
	if (isEmpty()) return emptyList()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <C : Comparable<C>> DoubleArray.allMaxBy(b: (Double) -> C): List<Double> {
	if (isEmpty()) return emptyList()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

inline fun <K, V, T : Comparable<T>> Map<K, V>.minBy(b: (Map.Entry<K, V>) -> T): Map.Entry<K, V> = minByOrNull(b)!!
inline fun <K, V, T : Comparable<T>> Map<K, V>.maxBy(b: (Map.Entry<K, V>) -> T): Map.Entry<K, V> = maxByOrNull(b)!!

inline fun <K, V, T : Comparable<T>> Map<K, V>.allMinBy(b: (Map.Entry<K, V>) -> T): Map<K, V> {
	if (isEmpty()) return emptyMap()
	val min = minBy(b)
	return filter { b(it) == b(min) }
}

inline fun <K, V, T : Comparable<T>> Map<K, V>.allMaxBy(b: (Map.Entry<K, V>) -> T): Map<K, V> {
	if (isEmpty()) return emptyMap()
	val max = maxBy(b)
	return filter { b(it) == b(max) }
}

@JvmName("minMaxVararg")
fun <T : Comparable<T>> minMax(vararg e: T): Pair<T, T> = e.min() to e.max()
fun <T : Comparable<T>> Iterable<T>.minMax(): Pair<T, T> = min() to max()
fun <T : Comparable<T>> Array<T>.minMax(): Pair<T, T> = min() to max()
fun IntArray.minMax(): Pair<Int, Int> = min() to max()
fun LongArray.minMax(): Pair<Long, Long> = min() to max()
fun DoubleArray.minMax(): Pair<Double, Double> = min() to max()

@JvmName("minMaxIRIntsVararg")
fun minMaxIR(vararg e: Int): IntRange = e.min()..e.max()

@JvmName("minMaxIRInts")
fun Iterable<Int>.minMaxIR(): IntRange = min()..max()

@JvmName("minMaxIRInts")
fun Array<Int>.minMaxIR(): IntRange = min()..max()

@JvmName("minMaxIRInts")
fun IntArray.minMaxIR(): IntRange = min()..max()

@JvmName("minMaxIRLongsVararg")
fun minMaxIR(vararg e: Long): LongRange = e.min()..e.max()

@JvmName("minMaxIRLongs")
fun Iterable<Long>.minMaxIR(): LongRange = min()..max()

@JvmName("minMaxIRLongs")
fun Array<Long>.minMaxIR(): LongRange = min()..max()

@JvmName("minMaxIRLongs")
fun LongArray.minMaxIR(): LongRange = min()..max()


@Suppress("FINAL_UPPER_BOUND")
@JvmName("minMaxIRSintsVararg")
fun <TSint : Sint> minMaxIR(vararg e: TSint): SintRange = e.min()..e.max()

@JvmName("minMaxIRSints")
fun Iterable<Sint>.minMaxIR(): SintRange = min()..max()

@JvmName("minMaxIRSints")
fun Array<Sint>.minMaxIR(): SintRange = min()..max()

fun Int.pow(x: Int): Int = when {
	x == 0 -> 1
	x == 1 -> this
	x % 2 == 0 -> (this * this).pow(x / 2)
	else -> (this * this).pow(x / 2) * this
}

fun Long.pow(x: Int): Long = when {
	x == 0 -> 1
	x == 1 -> this
	x % 2 == 0 -> (this * this).pow(x / 2)
	else -> (this * this).pow(x / 2) * this
}

fun Long.powMod(x: Int, y: Long): Long = when {
	x < 0 -> if (this == 0L) throw IllegalArgumentException("0^$x mod $y") else this.modInv(y).powMod(-x, y)
	x == 0 -> 1
	x == 1 -> this mod y
	x % 2 == 0 -> (this * this % y).powMod(x / 2, y)
	else -> (this * this % y).powMod(x / 2, y) * this % y
}

fun Int.primeFactors(): List<Pair<Int, Int>> {
	val factors = mutableListOf<Pair<Int, Int>>()
	var n = this
	for (i in 2..n) {
		var count = 0
		while (n % i == 0) {
			count++
			n /= i
		}
		if (count > 0) factors.add(i to count)
	}
	return factors
}

fun Int.allDivisors(): List<Int> {
	val factors = primeFactors()
	var divisors = listOf(1)

	for ((factor, count) in factors) {
		divisors = divisors.flatMap { d -> (0..count).map { d * factor.pow(it) } }
	}

	return divisors
}

infix fun Int.mod(base: Int) = Math.floorMod(this, base)
infix fun Long.mod(base: Int) = Math.floorMod(this, base)
infix fun Long.mod(base: Long) = Math.floorMod(this, base)


fun Int.modInv(base: Int): Int {
	val (g, x, _) = egcd(this, base)
	if (g != 1) throw IllegalArgumentException("No inverse")
	return x mod base
}

fun Long.modInv(base: Long): Long {
	val (g, x, _) = egcd(this, base)
	if (g != 1L) throw IllegalArgumentException("No inverse")
	return x mod base
}

