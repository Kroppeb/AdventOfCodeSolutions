package iterators

import java.util.*

class ConcatIterator<T>(iterator: Iterator<T>) : Iterator<T> {
	private val store = ArrayDeque<Iterator<T>>()

	init {
		if (iterator.hasNext())
			store.add(iterator)
	}

	override fun hasNext(): Boolean = when {
		store.isEmpty() -> false
		else -> store.first.hasNext()  // isn't this always true?
	}

	override fun next(): T {
		val t = store.first.next()

		if (!store.first.hasNext())
			store.removeFirst()

		return t
	}

	operator fun plus(iterator: Iterator<T>): ConcatIterator<T> {
		if (iterator.hasNext())
			store.add(iterator)
		return this
	}
}


operator fun <T> Iterator<T>.plus(iterator: Iterator<T>): ConcatIterator<T> = ConcatIterator(this) + iterator