package me.kroppeb.aoc.helpers

inline fun <A, B> Pair<A, A>.map(mapping: (A) -> B): Pair<B, B> = mapping(first) to mapping(second)

inline fun <A, B, R> Pair<A, B>.on(block: (A,B) -> R): R = block(first, second)

