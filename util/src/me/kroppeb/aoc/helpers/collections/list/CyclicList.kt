package me.kroppeb.aoc.helpers.collections.list

import iterators.repeatingIterator

class CyclicList<out T>(private val inner:List<T>) : List<T> by inner, InfiniteList<T> {
	override fun get(index: Int): T = inner[index % inner.size]

	override fun iterator() = inner.repeatingIterator()

	override fun listIterator(): ListIterator<T> {
		TODO("Implement listIterator")
	}

	override fun listIterator(index: Int): ListIterator<T> {
		TODO("Implement listIterator")
	}

	override fun subList(fromIndex: Int, toIndex: Int): List<T> {
		TODO("Implement subList")
	}
}

