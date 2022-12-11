package me.kroppeb.aoc.helpers.collections

class DefaultMap<K, V> constructor(val default: () -> V, val map: MutableMap<K, V>) : MutableMap<K, V> by map {
	override fun get(key: K): V = map[key] ?: default().also{map[key] = it}
}

fun <K, V> defaultMapOf(default: () -> V) = DefaultMap<K, V>(default, mutableMapOf())