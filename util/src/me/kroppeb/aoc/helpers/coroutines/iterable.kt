package me.kroppeb.aoc.helpers.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend inline fun <T,R>Iterable<T>.parallelMap(crossinline transform:suspend (T)->R) = coroutineScope{
	this@parallelMap.map{async { transform(it) }}.awaitAll()
}