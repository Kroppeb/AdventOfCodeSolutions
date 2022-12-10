@file:Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
package helpers.contextual

import helpers.context.InternalIndirectAddOp
import helpers.scan

context(InternalIndirectAddOp<K>)
fun <K, T: K> Iterable<T>.sum(): T = reduce { a, b -> a + b }

context(InternalIndirectAddOp<K>)
fun <R, K, T: K> Iterable<R>.sumOf(selector: (R) -> T): T = map(selector).sum()

context(InternalIndirectAddOp<K>)
fun <K, T: K>  Iterable<T>.cumSum(): List<T> = scan { a, b -> a + b }

context(InternalIndirectAddOp<K>)
fun <R, K, T: K>  Iterable<R>.cumSumOf(selector: (R) -> T): List<T> = map(selector).cumSum()

context(InternalIndirectAddOp<K>)
infix fun <K, T: K>  Iterable<T>.cumSum(initial:T): List<T> = scan(initial) { a, b -> a + b }

context(InternalIndirectAddOp<K>)
fun <R, K, T: K>  Iterable<R>.cumSumOf(initial:T, selector: (R) -> T): List<T> = map(selector).cumSum(initial)



