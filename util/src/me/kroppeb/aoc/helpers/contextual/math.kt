package me.kroppeb.aoc.helpers.contextual

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.max
import me.kroppeb.aoc.helpers.sint.*

context(CanBeZeroTrait<T>, InternalIndirectRemOp<T>)
infix fun <T, K:T> K.divBy(other: K): Boolean = (this % other).isZero()

context(InternalIndirectNegOp<T>)
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, K> K.abs() : K where K : Comparable<K>, K : T = max(this, -this)
// fun <T, K> K.abs() : K where K : T, K : Comparable<K> = max(this, -this)

context(InternalIndirectIncOp<T>)
operator fun <T, K:T> K.inc(): K = this.inc()

context(InternalIndirectDecOp<T>)
operator fun <T, K:T> K.dec(): K = this.dec()


context(CanBeZeroTrait<T>, InternalIndirectRemOp<T>)
tailrec fun <T, K:T> gcd(a: K, b: K): K = if(a.isZero()) b else gcd(b % a, a)

context(CanBeZeroTrait<T>, InternalIndirectRemOp<T>)
fun <T, K:T> Iterable<K>.gcd():K = reduce{a, b -> gcd(a, b)}

context(CanBeZeroTrait<T>, InternalIndirectRemOp<T>, InternalIndirectDivOp<T>, InternalIndirectMulOp<T>)
fun <T, K:T> lcm(a:K, b:K):K = a / gcd(a,b) * b

context(CanBeZeroTrait<T>, InternalIndirectRemOp<T>, InternalIndirectDivOp<T>, InternalIndirectMulOp<T>)
fun <T, K:T> Iterable<K>.lcm():K = reduce{a, b -> lcm(a, b)}




context(InternalIndirectMulOp<T>)
fun <T, K:T> K.pow(x: Sint): K = when {
	x == 0.s -> error("can't make 1") // could ask for 'div'
	x == 1.s -> this
	x % 2 == 0.s -> (this * this).pow(x / 2)
	else -> (this * this).pow(x / 2) * this
}

context(InternalIndirectMulOp<T>, InternalIndirectRemOp<T>, InternalIndirectIncOp<T>, CanBeZeroTrait<T>)
fun <T, K:T> K.powMod(x: Sint, y:K): K = when {
	x == 0.s -> error("can't make 1") // could ask for 'div'
	x == 1.s -> this % y
	x % 2 == 0.s -> (this * this % y).powMod(x / 2, y)
	else -> (this * this % y).powMod(x / 2, y) * this % y
}

context(InternalIndirectMulOp<T>, InternalIndirectRemOp<T>, InternalIndirectDivOp<T>, InternalIndirectIncOp<T>, CanBeZeroTrait<T>)
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, K> K.primeFactors(): List<Pair<K, Sint>>  where K : Comparable<K>, K : T {
	val factors = mutableListOf<Pair<K, Sint>>()
	var i = (this / this).inc() // == 2
	var n = this
	while (i < n) {
		var count = 0.s
		while (n.divBy(i)) {
			count++
			n /= i
		}
		if (count > 0) factors.add(i to count)
	}
	return factors
}

context(InternalIndirectMulOp<T>, InternalIndirectRemOp<T>, InternalIndirectDivOp<T>, InternalIndirectIncOp<T>, CanBeZeroTrait<T>)
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, K> K.allDivisors(): List<K>  where K : Comparable<K>, K : T {
	val factors = primeFactors()
	var divisors = listOf(this / this) // == 1

	for ((factor, count) in factors) {
		divisors = divisors.flatMap { d -> (0..count).map { d * factor.pow(it) } }
	}

	return divisors
}

infix fun Int.mod(base:Int) = Math.floorMod(this, base)
infix fun Long.mod(base:Int) = Math.floorMod(this, base)
infix fun Long.mod(base:Long) = Math.floorMod(this, base)
