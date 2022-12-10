package helpers.attributes

import kotlin.collections.any as anyKt
import kotlin.collections.all as allKt
import kotlin.collections.none as noneKt
import kotlin.collections.map as mapKt
import kotlin.collections.count as countKt
import kotlin.let as letKt

@Infix_
inline infix fun <T> Iterable<T>.any(predicate: (T) -> Boolean): Boolean = anyKt(predicate)
@Infix_
inline infix fun <T> Iterable<T>.all(predicate: (T) -> Boolean): Boolean = allKt(predicate)
@Infix_
inline infix fun <T> Iterable<T>.none(predicate: (T) -> Boolean): Boolean = noneKt(predicate)
@Infix_
inline infix fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> = mapKt(transform)
@Infix_
inline infix fun <T> Iterable<T>.count(predicate: (T) -> Boolean): Int = countKt(predicate)
@Infix_
inline infix fun <T, R> T.let(transform: (T) -> R): R = letKt(transform)