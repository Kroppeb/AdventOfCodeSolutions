package itertools

import iterators.RepeatingIterator


object Inf

fun count(start: Int = 0, step: Int = 1) = generateSequence(start) { it + step }

fun <T> Iterable<T>.cycle() = RepeatingIterator(this)
fun <T> Sequence<T>.cycle() = sequence { while(true) yieldAll(this@cycle) }

fun <T> repeat(value: T) = sequence { while (true) yield(value) }
fun <T> repeat(value: T, count: Int) = sequence { repeat(count) { yield(value) } }

fun <T>Iterable<T>.padRight(value:T) = this + repeat(value)
fun <T>Sequence<T>.padRight(value:T) = this + repeat(value)

fun <T>Iterable<T>.s() = asSequence()
@JvmName("ss")
fun <T>Iterable<Iterable<T>>.s():Sequence<Sequence<T>> = map(Iterable<T>::s).s()
fun <T>Sequence<Iterable<T>>.s():Sequence<Sequence<T>> = map(Iterable<T>::s)

