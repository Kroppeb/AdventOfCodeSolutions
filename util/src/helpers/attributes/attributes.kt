package helpers.attributes

import helpers.context.InternalIndirectAddOp
import helpers.contextual.cumSum
import helpers.map2
import helpers.repeat
import helpers.rleDecode
import helpers.scan
import sun.net.util.IPAddressUtil.scan


//interface IterableOp<Trait : Kind1<*, *>, R> {
//	context(K)
//	fun <T, K:Kind1<Trait, T>> getOf(item:Iterable<T>): R
//}

object empty
object notEmpty
object size

object each

object distinct

@DslMarker
annotation class Infix_

@Infix_
inline fun <T> count(crossinline predicate: (T) -> Boolean): (Iterable<T>) -> Int = { it.count(predicate) }

@Infix_
inline fun <T, R> map(crossinline transform: (T) -> R): (Iterable<T>) -> List<R> = { it.map(transform) }

@Infix_
inline fun <T, R> map2(crossinline transform: (T) -> R): (Iterable<Iterable<T>>) -> List<List<R>> =
	{ it.map2(transform) }

@Infix_
inline fun <T> filter(crossinline predicate: (T) -> Boolean): (Iterable<T>) -> Iterable<T> = { it.filter(predicate) }

@Infix_
inline fun <T> filter(attr: distinct): (Iterable<T>) -> Iterable<T> = { it.filter(attr) }

context(InternalIndirectAddOp<K>)
@Infix_
fun <K, T : K> cumSum(initial: T): Iterable<T>.() -> List<T> = { this.cumSum(initial) }

@Infix_
inline fun <T, R> scan(start: R): Iterable<T>.() -> ((R, T) -> R) -> List<R> = {{ transform -> this.scan(start, transform)}}

@Infix_
fun <T, R> rleDecode(value: (T) -> R, length: (T) -> Int): Iterable<T>.() -> List<R> = {
	this.rleDecode(value, length)}