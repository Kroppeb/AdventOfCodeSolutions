package me.kroppeb.aoc.helpers.collections.list

class PyList<T>(val inner:MutableList<T>): MutableList<T> by inner {
	override fun get(index: Int): T {
		if(index < 0)
			return inner[inner.size + index]
		return inner[index]
	}
}

fun <T>MutableList<T>.py() = PyList(this)