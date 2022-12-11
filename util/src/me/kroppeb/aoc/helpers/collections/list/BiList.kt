package me.kroppeb.aoc.helpers.collections.list

class BiList<T>(val center: Int = 0) : Collection<T> {
	val left = mutableListOf<T>()
	val right = mutableListOf<T>()

	override val size: Int
		get() = left.size + right.size

	override fun containsAll(elements: Collection<T>): Boolean {
		val set = elements.toMutableSet()
		left.forEach(set::remove)
		right.forEach(set::remove)
		return set.isEmpty()
	}

	fun get(index: Int): T {
		if (index >= center){
			return right[index - center]
		} else {
			return left[center - index]
		}
	}

	fun set(index: Int, element: T): T {
		if (index >= center){
			return right.set(index - center, element)
		} else {
			return left.set(center - index, element)
		}
	}

	fun addRight(element: T) = right.add(element)
	fun addLeft(element: T) = left.add(element)

	override fun contains(element: T): Boolean {
		return left.contains(element) || right.contains(element)
	}

	override fun isEmpty(): Boolean = left.isEmpty() && right.isEmpty()

	override fun iterator(): Iterator<T> = object : Iterator<T> {
		var index = center - left.size
		override fun hasNext(): Boolean = index < center + right.size

		override fun next(): T = get(index++)
	}
}