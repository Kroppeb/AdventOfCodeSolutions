package me.kroppeb.aoc.helpers.sint

import me.kroppeb.aoc.helpers.context.InternalIndirectAddOp
import me.kroppeb.aoc.helpers.context.SintOpps
import me.kroppeb.aoc.helpers.contextual.cumSum
import me.kroppeb.aoc.helpers.mod
import me.kroppeb.aoc.helpers.scan
import kotlin.contracts.contract
import me.kroppeb.aoc.helpers.contextual.divBy as divByTrait

val Int.s: Sint get() = Sint(this.toLong())
val Long.s: Sint get() = Sint(this)
@Deprecated("UwU")
val Sint.s: Sint get() = this


//region overloads for Sint opp Int or Long and vice versa
operator fun Sint.plus(other: Int) = this + other.s
operator fun Sint.plus(other: Long) = this + other.s
operator fun Int.plus(other: Sint) = this.s + other
operator fun Long.plus(other: Sint) = this.s + other

operator fun Sint.minus(other: Int) = this - other.s
operator fun Sint.minus(other: Long) = this - other.s
operator fun Int.minus(other: Sint) = this.s - other
operator fun Long.minus(other: Sint) = this.s - other

operator fun Sint.times(other: Int) = this * other.s
operator fun Sint.times(other: Long) = this * other.s
operator fun Int.times(other: Sint) = this.s * other
operator fun Long.times(other: Sint) = this.s * other

operator fun Sint.div(other: Int) = this / other.s
operator fun Sint.div(other: Long) = this / other.s
operator fun Int.div(other: Sint) = this.s / other
operator fun Long.div(other: Sint) = this.s / other

operator fun Sint.rem(other: Int) = this % other.s
operator fun Sint.rem(other: Long) = this % other.s
operator fun Int.rem(other: Sint) = this.s % other
operator fun Long.rem(other: Sint) = this.s % other

operator fun Sint.compareTo(other: Int) = this.compareTo(other.s)
operator fun Sint.compareTo(other: Long) = this.compareTo(other.s)
operator fun Int.compareTo(other: Sint) = this.s.compareTo(other)
operator fun Long.compareTo(other: Sint) = this.s.compareTo(other)

operator fun Sint.rangeTo(other: Int) = this..other.s
operator fun Sint.rangeTo(other: Long) = this..other.s
operator fun Int.rangeTo(other: Sint) = this.s..other
operator fun Long.rangeTo(other: Sint) = this.s..other

operator fun Sint.rangeUntil(other: Int) = this..<other.s
operator fun Sint.rangeUntil(other: Long) = this..<other.s
operator fun Int.rangeUntil(other: Sint) = this.s..<other
operator fun Long.rangeUntil(other: Sint) = this.s..<other

infix fun Sint.until(other: Int) = this until other.s
infix fun Sint.until(other: Long) = this until other.s
infix fun Int.until(other: Sint) = this.s until other
infix fun Long.until(other: Sint) = this.s until other

infix fun Sint.downTo(other: Int) = this downTo other.s
infix fun Sint.downTo(other: Long) = this downTo other.s
infix fun Int.downTo(other: Sint) = this.s downTo other
infix fun Long.downTo(other: Sint) = this.s downTo other

fun Sint.coerceIn(min: Int, max: Int) = this.coerceIn(min.s, max.s)
fun Sint.coerceIn(min: Sint, max: Int) = this.coerceIn(min, max.s)
fun Sint.coerceIn(min: Int, max: Sint) = this.coerceIn(min.s, max)
fun Sint.coerceIn(min: Long, max: Long) = this.coerceIn(min.s, max.s)
fun Sint.coerceIn(min: Sint, max: Long) = this.coerceIn(min, max.s)
fun Sint.coerceIn(min: Long, max: Sint) = this.coerceIn(min.s, max)
fun Int.coerceIn(min: Sint, max: Sint) = this.s.coerceIn(min, max)
fun Int.coerceIn(min: Int, max: Sint) = this.s.coerceIn(min.s, max)
fun Int.coerceIn(min: Sint, max: Int) = this.s.coerceIn(min, max.s)
fun Long.coerceIn(min: Sint, max: Sint) = this.s.coerceIn(min, max)
fun Long.coerceIn(min: Long, max: Sint) = this.s.coerceIn(min.s, max)
fun Long.coerceIn(min: Sint, max: Long) = this.s.coerceIn(min, max.s)

fun Sint.coerceAtLeast(min: Int) = this.coerceAtLeast(min.s)
fun Sint.coerceAtLeast(min: Long) = this.coerceAtLeast(min.s)
fun Int.coerceAtLeast(min: Sint) = this.s.coerceAtLeast(min)
fun Long.coerceAtLeast(min: Sint) = this.s.coerceAtLeast(min)

fun Sint.coerceAtMost(max: Int) = this.coerceAtMost(max.s)
fun Sint.coerceAtMost(max: Long) = this.coerceAtMost(max.s)
fun Int.coerceAtMost(max: Sint) = this.s.coerceAtMost(max)
fun Long.coerceAtMost(max: Sint) = this.s.coerceAtMost(max)

operator fun Collection<Sint>.contains(other: Int) = this.contains(other.s)
@JvmName("sintCollectionsContainsLong")
operator fun Collection<Sint>.contains(other: Long) = this.contains(other.s)
@JvmName("intCollectionsContainsSint")
operator fun Collection<Int>.contains(other: Sint) = this.contains(other.i)
@JvmName("longCollectionsContainsSint")
operator fun Collection<Long>.contains(other: Sint) = this.contains(other.l)

infix fun Sint.shl(other: Int) = this shl other.s
infix fun Sint.shl(other: Long) = this shl other.s
infix fun Int.shl(other: Sint) = this.s shl other
infix fun Long.shl(other: Sint) = this.s shl other

infix fun Sint.shr(other: Int) = this shr other.s
infix fun Sint.shr(other: Long) = this shr other.s
infix fun Int.shr(other: Sint) = this.s shr other
infix fun Long.shr(other: Sint) = this.s shr other

infix fun Sint.ushr(other: Int) = this ushr other.s
infix fun Sint.ushr(other: Long) = this ushr other.s
infix fun Int.ushr(other: Sint) = this.s ushr other
infix fun Long.ushr(other: Sint) = this.s ushr other

infix fun Sint.and(other: Int) = this and other.s
infix fun Sint.and(other: Long) = this and other.s
infix fun Int.and(other: Sint) = this.s and other
infix fun Long.and(other: Sint) = this.s and other

// endregion


// list stuff
operator fun <T>List<T>.get(index: Sint): T = this[index.i]
operator fun <T>List<T>.get(index: SintRange): List<T> = this.subList(index.start.i, index.endInclusive.i + 1)
operator fun <T>List<T>.get(index: SintProgression): List<T> = index.map { this[it.i] }
operator fun <T>MutableList<T>.set(index: Sint, item: T): T = this.set(index.i, item)
@JvmName("getMutable")
operator fun <T>MutableList<T>.get(index: SintRange): List<T> = this.subList(index.start.i, index.endInclusive.i + 1)
@JvmName("getMutable")
operator fun <T>MutableList<T>.get(index: SintProgression): List<T> = index.map { this[it.i] }
fun <T>MutableList<T>.add(index: Sint, item: T) = this.add(index.i, item)
fun List<*>.idx() = 0.s..<this.size.s
val List<*>.lastIdx: Sint get() = this.lastIndex.s

// array stuff
operator fun <T>Array<T>.get(index: Sint): T = this[index.i]
operator fun <T>Array<T>.get(index: SintRange): List<T> = this.slice(index.start.i..index.endInclusive.i)
operator fun <T>Array<T>.get(index: SintProgression): List<T> = index.map { this[it.i] }
operator fun <T>Array<T>.set(index: Sint, item: T) = this.set(index.i, item)
fun Array<*>.idx() = 0.s until this.size.s


infix fun Sint.divBy(other: Sint): Boolean = with(SintOpps) { this@divBy divByTrait other }
infix fun Sint.divBy(other: Int): Boolean = this divBy other.s
infix fun Sint.divBy(other: Long): Boolean = this divBy other.s
infix fun Int.divBy(other: Sint): Boolean = this.s divBy other
infix fun Long.divBy(other: Sint): Boolean = this.s divBy other



inline fun repeat(times: Sint, action: (Sint) -> Unit) {
//	contract { callsInPlace(action) }

	for (index in 0 until times) {
		action(index)
	}
}



infix fun Sint.mod(base:Sint) = this.l.mod(base.l).s
infix fun Sint.mod(base:Int) = this mod base.s
infix fun Sint.mod(base:Long) = this mod base.s
infix fun Int.mod(base:Sint) = this.s mod base
infix fun Long.mod(base:Sint) = this.s mod base



fun Iterable<Sint>.sum(): Sint = reduce { a, b -> a + b }

fun <T>Iterable<T>.sumOf(selector: (T) -> Sint): Sint = map(selector).sum()
fun Iterable<Sint>.cumSum(): List<Sint> = scan { a, b -> a + b }

fun <T>  Iterable<T>.cumSumOf(selector: (T) ->Sint): List<Sint> = map(selector).cumSum()

fun Iterable<Sint>.cumSum(initial:Sint): List<Sint> = scan(initial) { a, b -> a + b }

fun <T>  Iterable<T>.cumSumOf(initial:Sint, selector: (T) -> Sint): List<Sint> = map(selector).cumSum(initial)

fun abs(a: Sint) = if (a.l < 0) -a else a


fun Sint.toDouble(): Double = this.l.toDouble()

val IntRange.s: SintRange get() = SintRange(this.start.s, this.endInclusive.s)
val LongRange.s: SintRange get() = SintRange(this.start.s, this.endInclusive.s)

operator fun Sint.rem(range: SintRange) = range.first + (this - range.first mod range.last - range.first + 1)
operator fun Sint.rem(range: IntRange) = this % range.s
operator fun Sint.rem(range: LongRange) = this % range.s
operator fun Int.rem(range: SintRange) = this.s % range
operator fun Long.rem(range: SintRange) = this.s % range
infix fun Sint.mod(base: SintRange) = this % base
infix fun Sint.mod(base: IntRange) = this % base
infix fun Sint.mod(base: LongRange) = this % base
infix fun Int.mod(base: SintRange) = this.s % base
infix fun Long.mod(base: SintRange) = this.s % base
