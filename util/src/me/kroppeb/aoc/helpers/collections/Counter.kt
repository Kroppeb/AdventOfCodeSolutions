package me.kroppeb.aoc.helpers.collections

class Counter<T> : MutableCollection<T> {
	val counts = mutableMapOf<T, Int>()

	/**
	 * Returns the size of the collection.
	 */
	override val size: Int = counts.size

	/**
	 * Checks if the specified element is contained in this collection.
	 */
	override fun contains(element: T): Boolean = element in counts

	/**
	 * Checks if all elements in the specified collection are contained in this collection.
	 */
	override fun containsAll(elements: Collection<T>): Boolean = counts.keys.containsAll(elements)

	/**
	 * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
	 */
	override fun isEmpty(): Boolean = counts.isEmpty()

	/**
	 * Adds the specified element to the collection.
	 *
	 * @return `true` if the element has been added, `false` if the collection does not support duplicates
	 * and the element is already contained in the collection.
	 */
	override fun add(element: T): Boolean {
		counts.merge(element, 1, Int::plus)
		return true
	}

	/**
	 * Adds all of the elements of the specified collection to this collection.
	 *
	 * @return `true` if any of the specified elements was added to the collection, `false` if the collection was not modified.
	 */
	override fun addAll(elements: Collection<T>): Boolean {
		for (i in elements)
			add(i)
		return elements.isNotEmpty()
	}

	/**
	 * Removes all elements from this collection.
	 */
	override fun clear() {
		counts.clear()
	}

	override fun iterator(): MutableIterator<T> = counts.keys.iterator()

	/**
	 * Removes a single instance of the specified element from this
	 * collection, if it is present.
	 *
	 * @return `true` if the element has been successfully removed; `false` if it was not present in the collection.
	 */
	override fun remove(element: T): Boolean {
		val n = counts.computeIfPresent(element) { _, b -> b - 1 }
		counts.remove(element, 0)
		return n != null
	}

	/**
	 * Removes all of this collection's elements that are also contained in the specified collection.
	 *
	 * @return `true` if any of the specified elements was removed from the collection, `false` if the collection was not modified.
	 */
	override fun removeAll(elements: Collection<T>): Boolean = counts.keys.removeAll(elements)

	/**
	 * Retains only the elements in this collection that are contained in the specified collection.
	 *
	 * @return `true` if any element was removed from the collection, `false` if the collection was not modified.
	 */
	override fun retainAll(elements: Collection<T>): Boolean = counts.keys.retainAll(elements)
}

fun <T> Iterable<T>.counter() = Counter<T>().also { it.addAll(this) }
