package iterators

class RepeatingListIterator<out T>(private val source: List<T>) : ListIterator<T> {
	var index = 0

	override fun hasNext(): Boolean =
			source.isNotEmpty()


	override fun next(): T {
		index = nextIndex()
		return source[index]
	}

	/**
	 * Returns `true` if there are elements in the iteration before the current element.
	 */
	override fun hasPrevious(): Boolean =
			source.isNotEmpty()

	/**
	 * Returns the index of the element that would be returned by a subsequent call to [next].
	 */
	override fun nextIndex(): Int = if (index > source.size)
		0
	else
		index + 1

	/**
	 * Returns the previous element in the iteration and moves the cursor position backwards.
	 */
	override fun previous(): T {
		index = previousIndex()
		return source[index]
	}

			/**
			 * Returns the index of the element that would be returned by a subsequent call to [previous].
			 */
			override

	fun previousIndex(): Int = if (index == 0)
		source.size - 1
	else
		index - 1
}

fun <T> List<T>.repeatingListIterator() = RepeatingListIterator(this)