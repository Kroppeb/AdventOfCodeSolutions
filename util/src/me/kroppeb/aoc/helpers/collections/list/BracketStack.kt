package me.kroppeb.aoc.helpers.collections.list

import java.util.*

class BracketStack<T>(val stack:Stack<T> = Stack(), val annihilate: (old:T, new:T)-> Boolean): List<T> by stack, MutableCollection<T>{
	/**
	 * Adds the specified element to the collection.
	 *
	 * @return `true` if the element has been added, `false` if the element removed an item
	 * and the element is already contained in the collection.
	 */
	override fun add(element: T): Boolean = if(isNotEmpty() && annihilate(stack.peek(), element)){
		stack.pop()
		false
	} else{
		stack.add(element)
		true
	}

	/**
	 * Adds all of the elements of the specified collection to this collection.
	 *
	 * @return `true` if any of the specified elements was added to the collection
	 */
	override fun addAll(elements: Collection<T>): Boolean {
		elements.map(this::add)
		return true
	}

	/**
	 * Removes all elements from this collection.
	 */
	override fun clear() = stack.clear()

	/**
	 * Removes a single instance of the specified element from this
	 * collection, if it is present.
	 *
	 * @return `true` if the element has been successfully removed; `false` if it was not present in the collection.
	 */
	override fun remove(element: T) = stack.remove(element)

	/**
	 * Removes all of this collection's elements that are also contained in the specified collection.
	 *
	 * @return `true` if any of the specified elements was removed from the collection, `false` if the collection was not modified.
	 */
	override fun removeAll(elements: Collection<T>): Boolean = stack.removeAll(elements)

	/**
	 * Retains only the elements in this collection that are contained in the specified collection.
	 *
	 * @return `true` if any element was removed from the collection, `false` if the collection was not modified.
	 */
	override fun retainAll(elements: Collection<T>): Boolean = stack.retainAll(elements)

	override fun iterator(): MutableIterator<T> = stack.iterator()
}

fun <T>Iterable<T>.bsSelfDestroying():List<T> = BracketStack<T>{a,b -> a==b}.also { it.addAll(this) }.stack
fun <T>Iterable<T>.bracketStack(annihilate: (old: T, new: T) -> Boolean):List<T> = BracketStack<T>(annihilate = annihilate).also { it.addAll(this) }.stack