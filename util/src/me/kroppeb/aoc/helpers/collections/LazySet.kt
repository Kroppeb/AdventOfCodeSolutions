package me.kroppeb.aoc.helpers.collections

enum class Mode {
	UNION, INTERSECTION, DIFFERENCE
}

class LazySet<T>(val a: Collection<T>, val b: Collection<T>, val mode: Mode) : Set<T> {
	private val cachedSet = mutableSetOf<T>()
	private val itemSet = mutableListOf<T>() // for iterators
	private val iteratorA = a.iterator()
	private var done = false

	private fun loadAll() {
		if (done) return
		while (iteratorA.hasNext()) {
			val next = iteratorA.next()
			when (mode) {
				Mode.UNION -> {
					cachedSet += next
					itemSet += next
				}

				Mode.INTERSECTION -> if (next in b) {
					cachedSet += next
					itemSet += next
				}

				Mode.DIFFERENCE -> if (next !in b) {
					cachedSet += next
					itemSet += next
				}
			}
		}
		if (mode == Mode.UNION) {
			cachedSet += b
			itemSet += b
		}
		done = true
	}

	override val size: Int
		get() {
			loadAll()
			return cachedSet.size
		}

	override fun isEmpty(): Boolean {
		if (done) {
			return cachedSet.isEmpty()
		}
		return !this.iterator().hasNext()
	}

	override fun iterator(): Iterator<T> = object : Iterator<T> {
		var i = 0
		override fun hasNext(): Boolean {
			if (i < itemSet.size) return true
			if (done) return false
			loadNext()
			return i < itemSet.size
		}

		override fun next(): T {
			if(!hasNext()) throw NoSuchElementException()
			return itemSet[i++]
		}

	}

	private fun loadNext() {
		if (done) return
		when (mode) {
			Mode.UNION -> {
				if (iteratorA.hasNext()) {
					val next = iteratorA.next()
					cachedSet += next
					itemSet += next
				} else {
					cachedSet += b
					itemSet += b
					done = true
				}
			}

			Mode.INTERSECTION -> {
				while (iteratorA.hasNext()) {
					val next = iteratorA.next()
					if (next in b) {
						cachedSet += next
						itemSet += next
						return
					}
				}
				done = true
			}

			Mode.DIFFERENCE -> {
				while (iteratorA.hasNext()) {
					val next = iteratorA.next()
					if (next !in b) {
						cachedSet += next
						itemSet += next
						return
					}
				}
				done = true
			}
		}
	}

	override fun containsAll(elements: Collection<T>): Boolean = if (done) {
		cachedSet.containsAll(elements)
	} else {
		when (mode) {
			Mode.UNION -> elements.all { it in a || it in b }
			Mode.INTERSECTION -> a.containsAll(elements) && b.containsAll(elements)
			Mode.DIFFERENCE -> a.containsAll(elements) && !b.containsAll(elements)
		}
	}

	override fun contains(element: T): Boolean = if (done) element in cachedSet else when (mode) {
		Mode.UNION -> element in a || element in b
		Mode.INTERSECTION -> element in a && element in b
		Mode.DIFFERENCE -> element in a && element !in b
	}

	companion object {
		private fun <T> Iterable<T>.asCol() = if (this is Collection<T>) this else toSet()
		fun <T> union(a: Iterable<T>, b: Iterable<T>):Set<T> = LazySet(a.asCol(), b.asCol(), Mode.UNION)
		fun <T> intersection(a: Iterable<*>, b: Iterable<*>):Set<T> = LazySet(a.asCol(), b.asCol(), Mode.INTERSECTION) as Set<T>
		fun <T> difference(a: Iterable<T>, b: Iterable<*>):Set<T> = LazySet(a.asCol(), b.asCol(), Mode.DIFFERENCE) as Set<T>
 	}

	override fun toString(): String = this.toSet().toString()
}