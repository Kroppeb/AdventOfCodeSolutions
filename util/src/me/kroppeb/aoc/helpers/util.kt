package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.sint.*
import kotlin.math.abs

inline fun loop(block: (Int) -> Unit): Nothing {
	var i = 0
	while (true) {
		block(i++)
	}
}

fun block(b: () -> Unit) = b()

fun hammingDistance(a: Collection<*>, b: Collection<*>): Int =
	a.zip(b) { a1, b1 -> (a1 == b1) }.count { it } + abs(a.size - b.size)

fun editDistance(a: Iterable<*>, b: Iterable<*>): Int {
	val al = (a as? List<*>) ?: a.toList()
	val bl = (b as? List<*>) ?: b.toList()
	val n = al.size
	val m = bl.size
	val dp = List(n + 1) { arrayOfNulls<Int>(m + 1) }
	dp[n][m] = 0




	fun aux(i: Int, j: Int): Int {

		dp[i][j]?.let { return it }

		when {
			i == n -> dp[i][j] = 1 + aux(i, j + 1)
			j == m -> dp[i][j] = 1 + aux(i + 1, j)
			else -> {
				dp[i][j] = min((al[i] != bl[j]).toInt() + aux(i + 1, j + 1), 1 + aux(i + 1, j), 1 + aux(i, j + 1))
			}
		}
		return dp[i][j]!!
	}

	return aux(0, 0)
}

fun Boolean.toInt() = if (this) 1 else 0

fun <C : Comparable<C>> min(first: C, vararg elements: C) = elements.minOrNull()?.let { if (it < first) it else first }
	?: first

fun <C : Comparable<C>> max(first: C, vararg elements: C) = elements.maxOrNull()?.let { if (it > first) it else first }
	?: first

fun len(lst: Collection<*>) = lst.size

// region binary search
inline fun <T> List<T>.bsLast(lower: Int = 0, upper: Int = this.size, predicate: (T) -> Boolean): T {
	if (!predicate(this[lower]))
		error("predicate fails on first element")

	var low = lower
	var high = upper
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(this[mid]))
			low = mid
		else
			high = mid
	}
	return this[low]
}

inline fun <T> List<T>.bsFirst(lower: Int = 0, upper: Int = this.size, predicate: (T) -> Boolean): T {
	if (!predicate(this[upper]))
		error("predicate fails on last element")

	var low = lower
	var high = upper
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(this[mid]))
			high = mid
		else
			low = mid
	}
	return this[high]
}

inline fun bsLast(lower: Int? = null, upper: Int? = null, predicate: (Int) -> Boolean): Int {
	var low: Int = lower ?: 0
	if (lower == null)
		if (!predicate(0)) {
			low = -1
			while (!predicate(low)) {
				low = (low shl 2)
				// overflow
				if (low == 0)
					if (predicate(Int.MIN_VALUE))
						low = Int.MIN_VALUE
					else
						error("predicate isn't true for min int")
			}
		} else if (!predicate(low))
			error("predicate fails on low")

	var high: Int = upper ?: 1
	if (upper == null)
		while (predicate(high)) {
			high = (high shl 2)
			// overflow
			if (high == 0)
				if (!predicate(Int.MAX_VALUE))
					high = Int.MAX_VALUE
				else
					error("predicate is even true for max int")
		}
	else if (predicate(high))
		error("predicate succeeds on high")
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(mid))
			high = mid
		else
			low = mid
	}
	return high
}

inline fun bsFirst(lower: Int? = null, upper: Int? = null, predicate: (Int) -> Boolean): Int {
	var low: Int = lower ?: 0
	if (lower == null)
		if (predicate(0)) {
			low = -1
			while (predicate(low)) {
				low = (low shl 2)
				// overflow
				if (low == 0)
					if (!predicate(Int.MIN_VALUE))
						low = Int.MIN_VALUE
					else
						error("predicate is even true for min int")
			}
		} else if (predicate(low))
			error("predicate succeeds on low")

	var high: Int = upper ?: 1
	if (upper == null)
		while (!predicate(high)) {
			high = (high shl 2)
			// overflow
			if (high == 0)
				if (predicate(Int.MAX_VALUE))
					high = Int.MAX_VALUE
				else
					error("predicate isn't even true for max int")
		}
	else if (!predicate(high))
		error("predicate fails on high")
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(mid))
			high = mid
		else
			low = mid
	}
	return high
}

inline fun bsLastL(lower: Long? = null, upper: Long? = null, predicate: (Long) -> Boolean): Long {
	var low: Long = lower ?: 0
	if (lower == null)
		if (!predicate(0)) {
			low = -1
			while (!predicate(low)) {
				low = (low shl 2)
				// overflow
				if (low == 0L)
					if (predicate(Long.MIN_VALUE))
						low = Long.MIN_VALUE
					else
						error("predicate isn't true for min long")
			}
		} else if (!predicate(low))
			error("predicate fails on low")

	var high: Long = upper ?: 1
	if (upper == null)
		while (predicate(high)) {
			high = (high shl 2)
			// overflow
			if (high == 0L)
				if (!predicate(Long.MAX_VALUE))
					high = Long.MAX_VALUE
				else
					error("predicate is even true for max long")
		}
	else if (predicate(high))
		error("predicate succeeds on high")
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(mid))
			low = mid
		else
			high = mid
	}
	return low
}

inline fun bsFirstL(lower: Long? = null, upper: Long? = null, predicate: (Long) -> Boolean): Long {
	var low: Long = lower ?: 0
	if (lower == null)
		if (predicate(0)) {
			low = -1
			while (predicate(low)) {
				low = (low shl 2)
				// overflow
				if (low == 0L)
					if (!predicate(Long.MIN_VALUE))
						low = Long.MIN_VALUE
					else
						error("predicate is even true for min long")
			}
		} else if (predicate(low))
			error("predicate succeeds on low")

	var high: Long = upper ?: 1
	if (upper == null)
		while (!predicate(high)) {
			high = (high shl 2)
			// overflow
			if (high == 0L)
				if (predicate(Long.MAX_VALUE))
					high = Long.MAX_VALUE
				else
					error("predicate isn't even true for max long")
		}
	else if (!predicate(high))
		error("predicate fails on high")
	while (high > low + 1) {
		val mid = (low + high) / 2
		if (predicate(mid))
			high = mid
		else
			low = mid
	}
	return high
}
// endregion

operator fun Int.rem(range: IntRange) = range.first + (this - range.first mod range.last - range.first + 1)
infix fun Int.mod(base: IntRange) = this % base

operator fun Long.rem(range: LongRange) = range.first + (this - range.first mod range.last - range.first + 1)
infix fun Long.mod(base: LongRange) = this % base

operator fun Long.rem(range: IntRange): Int = range.first + (this - range.first mod range.last - range.first + 1)
infix fun Long.mod(base: IntRange) = this % base


inline fun <reified T> Map<Int, T>.toArray(): Array<T?> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	return Array(max + 1) { get(it) }
}

fun <T> Map<Int, T>.xList(): List<T?> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	return List(max + 1) { get(it) }
}

inline fun <reified T> Map<Int, T>.xArray(size: Int): Array<T?> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	if (max >= size) throw IllegalArgumentException("map contains value larger then target size")
	return Array(size) { get(it) }
}

fun <T> Map<Int, T>.xList(size: Int): List<T?> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	if (max >= size) throw IllegalArgumentException("map contains value larger then target size")
	return List(size) { get(it) }
}


inline fun <reified T> Map<Int, T>.xArray(default: T): Array<T> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	return Array(max + 1) { getOrDefault(it, default) }
}

fun <T> Map<Int, T>.xList(default: T): List<T> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	return List(max + 1) { getOrDefault(it, default) }
}

inline fun <reified T> Map<Int, T>.xArray(size: Int, default: T): Array<T> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	if (max >= size) throw IllegalArgumentException("map contains value larger then target size")
	return Array(size) { getOrDefault(it, default) }
}

fun <T> Map<Int, T>.xList(size: Int, default: T): List<T> {
	val min = keys.min()
	val max = keys.max()

	if (min < 0) throw IllegalArgumentException("map contains negative keys")
	if (max >= size) throw IllegalArgumentException("map contains value larger then target size")
	return List(size) { getOrDefault(it, default) }
}


fun IntArray.toLongArray(): LongArray {
	return LongArray(size) { this[it].toLong() }
}

fun Array<Int>.toLongArray(): LongArray {
	return LongArray(size) { this[it].toLong() }
}

fun List<Int>.toLongArray(): LongArray {
	return LongArray(size) { this[it].toLong() }
}

fun <T> List<T>.mut() = this.toMutableList()
fun <T> List<List<T>>.mut2() = this.map { it.mut() }.mut()
fun <T> List<List<List<T>>>.mut3() = this.map { it.mut2() }.mut()

inline fun <T> pureStateLoop(start: T, steps: Int, f: (T) -> T): T = pureStateLoop(start, steps, 0, f)

inline fun <T> pureStateLoop(start: T, steps: Int, skipSteps: Int, f: (T) -> T): T {
	var id = 0
	var state = start
	val seen = mutableMapOf<T, Int>()
	val reverse = mutableListOf<T>(start)
	while (id < steps) {
		state = f(state)

		id++

		val prev = seen[state]
		if (prev != null) {
			val oo = steps mod (prev until id)

			return reverse[oo]
//			val diff = id - seen[state]!!
//			val oo = (1_000_000_000 - seen[state]!!) % diff
//			return seen.entries.find{it.value == seen[state]!! + oo}!!.key
		}

		seen[state] = id
		reverse.add(state)
	}
	return state
}

inline fun <T> pureStateLoopScore(start: T, steps: Sint, f: (T) -> Pair<T, Sint>): Sint = pureStateLoopScore(start, steps, 0.s, f)

inline fun <T> pureStateLoopScore(start: T, steps: Sint, skipSteps: Sint, f: (T) -> Pair<T, Sint>): Sint {
	var id = 0.s
	var state = start
	val seen = mutableMapOf<T, Sint>()
	val reverse = mutableListOf<T>(start)
	val scores = mutableListOf(0.s)
	while (id < steps) {
		val (s, score) = f(state)
		state = s

		id++

		if (id > skipSteps) {
			val prev = seen[state]
			if (prev != null) {
				val oo = steps mod (prev until id)
				val loops = (steps - oo) / (id - prev)
				val scoreDiff = score - scores[prev]

				return scores[oo] + scoreDiff * loops.s
//			val diff = id - seen[state]!!
//			val oo = (1_000_000_000 - seen[state]!!) % diff
//			return seen.entries.find{it.value == seen[state]!! + oo}!!.key
			}

			seen[state] = id
		}

		reverse.add(state)
		scores.add(score)
	}
	return scores.last()
}

class UPair<T>(val first: T, val second: T) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is UPair<*>) return false

		if (first != other.first && first != other.second) return false
		if (second != other.second && second != other.first) return false

		return true
	}

	override fun hashCode(): Int {
		val ha = first.hashCode()
		val hb = second.hashCode()
		// just xoring means that all pairs of the same elements will have the same hash, which is bad
		val mix = ha.toLong() * hb.toLong() shl 16
		return (ha xor hb xor mix.toInt())
	}

	override fun toString(): String {
		return "{$first, $second}"
	}

	operator fun component1() = first
	operator fun component2() = second
}

infix fun <T> T.toU(other: T) = UPair(this, other)


operator fun Boolean.inc() = true
operator fun Boolean.dec() = false