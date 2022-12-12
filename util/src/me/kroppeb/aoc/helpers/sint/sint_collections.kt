package me.kroppeb.aoc.helpers.sint

/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */


import me.kroppeb.aoc.helpers.contextual.divBy
import kotlin.random.*

fun <T> Iterable<T>.elementAt(idx: Sint): T {
	if (this is List)
		return get(idx)
	return elementAtOrElse(idx) { throw IndexOutOfBoundsException("Collection doesn't contain element at idx $idx.") }
}


fun <T> List<T>.elementAt(idx: Sint): T {
	return get(idx)
}


inline fun <T> Iterable<T>.elementAtOrElse(idx: Sint, defaultValue: (Sint) -> T): T {
	if (this is List)
		return this.getOrElse(idx, defaultValue)
	if (idx < 0)
		return defaultValue(idx)
	val iterator = iterator()
	var count = 0.s
	while (iterator.hasNext()) {
		val element = iterator.next()
		if (idx == count++)
			return element
	}
	return defaultValue(idx)
}


inline fun <T> List<T>.elementAtOrElse(idx: Sint, defaultValue: (Sint) -> T): T {
	return if (idx >= 0 && idx <= lastIdx) get(idx) else defaultValue(idx)
}


fun <T> Iterable<T>.elementAtOrNull(idx: Sint): T? {
	if (this is List)
		return this.getOrNull(idx)
	if (idx < 0)
		return null
	val iterator = iterator()
	var count = 0.s
	while (iterator.hasNext()) {
		val element = iterator.next()
		if (idx == count++)
			return element
	}
	return null
}


inline fun <T> List<T>.elementAtOrNull(idx: Sint): T? {
	return this.getOrNull(idx)
}

inline fun <T> List<T>.getOrElse(idx: Sint, defaultValue: (Sint) -> T): T {
	return if (idx >= 0 && idx <= lastIdx) get(idx) else defaultValue(idx)
}


fun <T> List<T>.getOrNull(idx: Sint): T? {
	return if (idx >= 0 && idx <= lastIdx) get(idx) else null
}


fun <T> Iterable<T>.idxOf(element: T): Sint {
	if (this is List) return this.idxOf(element)
	var idx = 0.s
	for (item in this) {
		if (element == item)
			return idx
		idx++
	}
	return -1.s
}

fun <T> List<T>.idxOf(element: T): Sint {
	return indexOf(element).s
}


inline fun <T> Iterable<T>.idxOfFirst(predicate: (T) -> Boolean): Sint {
	var idx = 0.s
	for (item in this) {
		if (predicate(item))
			return idx
		idx++
	}
	return -1.s
}


inline fun <T> List<T>.idxOfFirst(predicate: (T) -> Boolean): Sint {
	var idx = 0.s
	for (item in this) {
		if (predicate(item))
			return idx
		idx++
	}
	return -1.s
}


inline fun <T> Iterable<T>.idxOfLast(predicate: (T) -> Boolean): Sint {
	var lastIdx = -1.s
	var idx = 0.s
	for (item in this) {
		if (predicate(item))
			lastIdx = idx
		idx++
	}
	return lastIdx
}

fun <T>ListIterator<T>.nextIdx() = nextIndex().s
fun <T>ListIterator<T>.previousIdx() = previousIndex().s

inline fun <T> List<T>.idxOfLast(predicate: (T) -> Boolean): Sint {
	val iterator = this.listIterator(size)
	while (iterator.hasPrevious()) {
		if (predicate(iterator.previous())) {
			return iterator.nextIdx()
		}
	}
	return -1.s
}


fun <T> Iterable<T>.lastIdxOf(element: T): Sint {
	if (this is List) return this.lastIdxOf(element)
	var lastIdx = -1.s
	var idx = 0.s
	for (item in this) {
		if (element == item)
			lastIdx = idx
		idx++
	}
	return lastIdx
}


fun <T> List<T>.lastIdxOf(element: T): Sint {
	return lastIndexOf(element).s
}

fun <T> Iterable<T>.drop(n: Sint): List<T> {
	require(n >= 0) { "Requested element count $n is less than zero." }
	if (n == 0.s) return toList()
	val list: ArrayList<T>
	if (this is Collection<*>) {
		val resultSize = size - n
		if (resultSize <= 0)
			return emptyList()
		if (resultSize == 1.s)
			return listOf(last())
		list = ArrayList<T>(resultSize.i)
		if (this is List<T>) {
			if (this is RandomAccess) {
				for (idx in n until size)
					list.add(this[idx])
			} else {
				for (item in listIterator(n.i))
					list.add(item)
			}
			return list
		}
	} else {
		list = ArrayList<T>()
	}
	var count = 0
	for (item in this) {
		if (count >= n) list.add(item) else ++count
	}
	return list.optimizeReadOnlyList()
}


fun <T> List<T>.dropLast(n: Sint): List<T> {
	require(n >= 0) { "Requested element count $n is less than zero." }
	return take((size - n).coerceAtLeast(0))
}


inline fun <T> Iterable<T>.filterIdx(predicate: (idx: Sint, T) -> Boolean): List<T> {
	return filterIdxTo(ArrayList<T>(), predicate)
}


inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterIdxTo(
	destination: C,
	predicate: (idx: Sint, T) -> Boolean
): C {
	forEachIdx { idx, element ->
		if (predicate(idx, element)) destination.add(element)
	}
	return destination
}

fun <T> List<T>.slice(indices: SintRange): List<T> {
	if (indices.isEmpty()) return listOf()
	return this[indices]
}


@PublishedApi
internal fun <T> Iterable<T>.collectionSizeOrDefault(default: Int): Int = if (this is Collection<*>) this.size else default


fun <T> List<T>.slice(indices: Iterable<Sint>): List<T> {
	val size = indices.collectionSizeOrDefault(10)
	if (size == 0) return emptyList()
	val list = ArrayList<T>(size)
	for (idx in indices) {
		list.add(get(idx))
	}
	return list
}


fun <T> Iterable<T>.take(n: Sint): List<T> {
	require(n >= 0) { "Requested element count $n is less than zero." }
	if (n == 0.s) return emptyList()
	if (this is Collection<T>) {
		if (n >= size) return toList()
		if (n == 1.s) return listOf(first())
	}
	var count = 0.s
	val list = ArrayList<T>(n.i)
	for (item in this) {
		list.add(item)
		if (++count == n)
			break
	}
	return list.optimizeReadOnlyList()
}


fun <T> List<T>.takeLast(n: Sint): List<T> {
	require(n >= 0) { "Requested element count $n is less than zero." }
	if (n == 0.s) return emptyList()
	val size = size.s
	if (n >= size) return toList()
	if (n == 1.s) return listOf(last())
	val list = ArrayList<T>(n.i)
	if (this is RandomAccess) {
		for (idx in size - n until size)
			list.add(this[idx])
	} else {
		for (item in listIterator((size - n).i))
			list.add(item)
	}
	return list
}



@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapIdxIterable")

inline fun <T, R> Iterable<T>.flatMapIdx(transform: (idx: Sint, T) -> Iterable<R>): List<R> {
	return flatMapIdxTo(ArrayList<R>(), transform)
}



@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapIdxSequence")

inline fun <T, R> Iterable<T>.flatMapIdx(transform: (idx: Sint, T) -> Sequence<R>): List<R> {
	return flatMapIdxTo(ArrayList<R>(), transform)
}



@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapIdxedIterableTo")

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapIdxTo(
	destination: C,
	transform: (idx: Sint, T) -> Iterable<R>
): C {
	var idx = 0.s
	for (element in this) {
		val list = transform(idx++, element)
		destination.addAll(list)
	}
	return destination
}



@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapIdxedSequenceTo")

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapIdxTo(
	destination: C,
	transform: (idx: Sint, T) -> Sequence<R>
): C {
	var idx = 0.s
	for (element in this) {
		val list = transform(idx++, element)
		destination.addAll(list)
	}
	return destination
}

inline fun <T, R> Iterable<T>.mapIdx(transform: (idx: Sint, T) -> R): List<R> {
	return mapIdxTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
}


inline fun <T, R : Any> Iterable<T>.mapIdxNotNull(transform: (idx: Sint, T) -> R?): List<R> {
	return mapIdxNotNullTo(ArrayList<R>(), transform)
}


inline fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapIdxNotNullTo(
	destination: C,
	transform: (idx: Sint, T) -> R?
): C {
	forEachIdx { idx, element -> transform(idx, element)?.let { destination.add(it) } }
	return destination
}


inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapIdxTo(
	destination: C,
	transform: (idx: Sint, T) -> R
): C {
	var idx = 0.s
	for (item in this)
		destination.add(transform(idx++, item))
	return destination
}

data class IdxValue<out T>(public val idx: Sint, public val value: T)


internal class IdxIterable<out T>(private val iteratorFactory: () -> Iterator<T>) : Iterable<IdxValue<T>> {
	override fun iterator(): Iterator<IdxValue<T>> = IdxIterator(iteratorFactory())
}
internal class IdxIterator<out T>(private val iterator: Iterator<T>) : Iterator<IdxValue<T>> {
	private var index = 0.s
	final override fun hasNext(): Boolean = iterator.hasNext()
	final override fun next(): IdxValue<T> = IdxValue(index++, iterator.next())
}
fun <T> Iterable<T>.withIdx(): Iterable<IdxValue<T>> {
	return IdxIterable { iterator() }
}


fun <T> Iterable<T>.cnt(): Sint {
	if (this is Collection) return size.s
	var count = 0.s
	for (element in this) ++count
	return count
}


inline fun <T> Collection<T>.cnt(): Sint {
	return size.s
}


inline fun <T> Iterable<T>.cnt(predicate: (T) -> Boolean): Sint {
	if (this is Collection && isEmpty()) return 0.s
	var count = 0.s
	for (element in this) if (predicate(element)) ++count
	return count
}


inline fun <T, R> Iterable<T>.foldIdx(initial: R, operation: (idx: Sint, acc: R, T) -> R): R {
	var idx = 0.s
	var accumulator = initial
	for (element in this) accumulator = operation(idx++, accumulator, element)
	return accumulator
}

inline fun <T, R> List<T>.foldRightIdx(initial: R, operation: (idx: Sint, T, acc: R) -> R): R {
	var accumulator = initial
	if (!isEmpty()) {
		val iterator = listIterator(size)
		while (iterator.hasPrevious()) {
			val idx = iterator.previousIdx()
			accumulator = operation(idx, iterator.previous(), accumulator)
		}
	}
	return accumulator
}

inline fun <T> Iterable<T>.forEachIdx(action: (idx: Sint, T) -> Unit): Unit {
	var idx = 0.s
	for (item in this) action(idx++, item)
}



inline fun <T, C : Iterable<T>> C.onEachIdx(action: (idx: Sint, T) -> Unit): C {
	return apply { forEachIdx(action) }
}


inline fun <S, T : S> Iterable<T>.reduceIdx(operation: (idx: Sint, acc: S, T) -> S): S {
	val iterator = this.iterator()
	if (!iterator.hasNext()) throw UnsupportedOperationException("Empty collection can't be reduced.")
	var idx = 1.s
	var accumulator: S = iterator.next()
	while (iterator.hasNext()) {
		accumulator = operation(idx++, accumulator, iterator.next())
	}
	return accumulator
}



inline fun <S, T : S> Iterable<T>.reduceIdxOrNull(operation: (idx: Sint, acc: S, T) -> S): S? {
	val iterator = this.iterator()
	if (!iterator.hasNext()) return null
	var idx = 1.s
	var accumulator: S = iterator.next()
	while (iterator.hasNext()) {
		accumulator = operation(idx++, accumulator, iterator.next())
	}
	return accumulator
}

inline fun <S, T : S> List<T>.reduceRightIdx(operation: (idx: Sint, T, acc: S) -> S): S {
	val iterator = listIterator(size)
	if (!iterator.hasPrevious())
		throw UnsupportedOperationException("Empty list can't be reduced.")
	var accumulator: S = iterator.previous()
	while (iterator.hasPrevious()) {
		val idx = iterator.previousIdx()
		accumulator = operation(idx, iterator.previous(), accumulator)
	}
	return accumulator
}



inline fun <S, T : S> List<T>.reduceRightIdxOrNull(operation: (idx: Sint, T, acc: S) -> S): S? {
	val iterator = listIterator(size)
	if (!iterator.hasPrevious())
		return null
	var accumulator: S = iterator.previous()
	while (iterator.hasPrevious()) {
		val idx = iterator.previousIdx()
		accumulator = operation(idx, iterator.previous(), accumulator)
	}
	return accumulator
}




inline fun <T, R> Iterable<T>.runningFoldIdx(initial: R, operation: (idx: Sint, acc: R, T) -> R): List<R> {
	val estimatedSize = collectionSizeOrDefault(9)
	if (estimatedSize == 0) return listOf(initial)
	val result = ArrayList<R>(estimatedSize + 1).apply { add(initial) }
	var idx = 0.s
	var accumulator = initial
	for (element in this) {
		accumulator = operation(idx++, accumulator, element)
		result.add(accumulator)
	}
	return result
}



inline fun <S, T : S> Iterable<T>.runningReduceIdx(operation: (idx: Sint, acc: S, T) -> S): List<S> {
	val iterator = this.iterator()
	if (!iterator.hasNext()) return emptyList()
	var accumulator: S = iterator.next()
	val result = ArrayList<S>(collectionSizeOrDefault(10)).apply { add(accumulator) }
	var idx = 1.s
	while (iterator.hasNext()) {
		accumulator = operation(idx++, accumulator, iterator.next())
		result.add(accumulator)
	}
	return result
}




inline fun <T, R> Iterable<T>.scanIdx(initial: R, operation: (idx: Sint, acc: R, T) -> R): List<R> {
	return runningFoldIdx(initial, operation)
}

internal fun <T> windowedIterator(iterator: Iterator<T>, size: Sint, step: Sint, partialWindows: Boolean, reuseBuffer: Boolean): Iterator<List<T>> {
	if (!iterator.hasNext()) return emptyList<List<T>>().iterator()
	return iterator<List<T>> {
		val bufferInitialCapacity = size.coerceAtMost(1024)
		val gap = step - size
		if (gap >= 0) {
			var buffer = ArrayList<T>(bufferInitialCapacity.i)
			var skip = 0.s
			for (e in iterator) {
				if (skip > 0) { skip -= 1; continue }
				buffer.add(e)
				if (buffer.size == size.i) {
					yield(buffer)
					if (reuseBuffer) buffer.clear() else buffer = ArrayList(size.i)
					skip = gap
				}
			}
			if (buffer.isNotEmpty()) {
				if (partialWindows || buffer.size == size.i) yield(buffer)
			}
		} else {
			var buffer = RingBuffer<T>(bufferInitialCapacity)
			for (e in iterator) {
				buffer.add(e)
				if (buffer.isFull()) {
					if (buffer.size < size) { buffer = buffer.expanded(maxCapacity = size.i); continue }

					yield(if (reuseBuffer) buffer else ArrayList(buffer))
					buffer.removeFirst(step)
				}
			}
			if (partialWindows) {
				while (buffer.size > step) {
					yield(if (reuseBuffer) buffer else ArrayList(buffer))
					buffer.removeFirst(step)
				}
				if (buffer.isNotEmpty()) yield(buffer)
			}
		}
	}
}

internal class MovingSubList<out E>(private val list: List<E>) : AbstractList<E>(), RandomAccess {
	private var fromIndex: Sint = 0.s
	private var _size: Sint = 0.s

	fun move(fromIndex: Sint, toIndex: Sint) {
		checkRangeIndexes(fromIndex, toIndex, list.size.s)
		this.fromIndex = fromIndex
		this._size = toIndex - fromIndex
	}

	override fun get(index: Int): E {
		checkElementIndex(index.s, _size)

		return list[fromIndex + index]
	}

	override val size: Int get() = _size.i
}


/**
 * Provides ring buffer implementation.
 *
 * Buffer overflow is not allowed so [add] doesn't overwrite tail but raises an exception.
 */
private class RingBuffer<T>(private val buffer: Array<Any?>, filledSize: Sint) : AbstractList<T>(), RandomAccess {
	init {
		require(filledSize >= 0) { "ring buffer filled size should not be negative but it is $filledSize" }
		require(filledSize <= buffer.size) { "ring buffer filled size: $filledSize cannot be larger than the buffer size: ${buffer.size}" }
	}

	constructor(capacity: Sint) : this(arrayOfNulls<Any?>(capacity.i), 0.s)

	private val capacity = buffer.size.s
	private var startIndex: Sint = 0.s

	override var size: Int = filledSize.i
		private set

	override fun get(index: Int): T {
		checkElementIndex(index.s, size.s)
		@Suppress("UNCHECKED_CAST")
		return buffer[startIndex.forward(index.s)] as T
	}

	fun isFull() = size.s == capacity

	override fun iterator(): Iterator<T> = object : AbstractIterator<T>() {
		private var count = size
		private var index = startIndex

		override fun computeNext() {
			if (count == 0) {
				done()
			} else {
				@Suppress("UNCHECKED_CAST")
				setNext(buffer[index] as T)
				index = index.forward(1.s)
				count--
			}
		}
	}

	@Suppress("UNCHECKED_CAST")
	override fun <T> toArray(array: Array<T>): Array<T> {
		val result: Array<T?> =
			if (array.size < this.size) array.copyOf(this.size) else array as Array<T?>

		val size = this.size

		var widx = 0
		var idx = startIndex

		while (widx < size && idx < capacity) {
			result[widx] = buffer[idx] as T
			widx++
			idx++
		}

		idx = 0.s
		while (widx < size) {
			result[widx] = buffer[idx] as T
			widx++
			idx++
		}
		if (result.size > this.size) result[this.size] = null

		return result as Array<T>
	}

	override fun toArray(): Array<Any?> {
		return toArray(arrayOfNulls(size))
	}

	/**
	 * Creates a new ring buffer with the capacity equal to the minimum of [maxCapacity] and 1.5 * [capacity].
	 * The returned ring buffer contains the same elements as this ring buffer.
	 */
	fun expanded(maxCapacity: Int): RingBuffer<T> {
		val newCapacity = (capacity + (capacity shr 1) + 1).coerceAtMost(maxCapacity)
		val newBuffer = if (startIndex == 0.s) buffer.copyOf(newCapacity.i) else toArray(arrayOfNulls(newCapacity.i))
		return me.kroppeb.aoc.helpers.sint.RingBuffer(newBuffer, size.s)
	}

	/**
	 * Add [element] to the buffer or fail with [IllegalStateException] if no free space available in the buffer
	 */
	fun add(element: T) {
		if (isFull()) {
			throw IllegalStateException("ring buffer is full")
		}

		buffer[startIndex.forward(size.s)] = element
		size++
	}

	/**
	 * Removes [n] first elements from the buffer or fails with [IllegalArgumentException] if not enough elements in the buffer to remove
	 */
	fun removeFirst(n: Sint) {
		require(n >= 0) { "n shouldn't be negative but it is $n" }
		require(n <= size) { "n shouldn't be greater than the buffer size: n = $n, size = $size" }

		if (n > 0) {
			val start = startIndex
			val end = start.forward(n)

			if (start > end) {
				buffer.fill(null, start.i, capacity.i)
				buffer.fill(null, 0, end.i)
			} else {
				buffer.fill(null, start.i, end.i)
			}

			startIndex = end
			size -= n.i
		}
	}


	@Suppress("NOTHING_TO_INLINE")
	private inline fun Sint.forward(n: Sint): Sint = (this + n) % capacity
}

internal fun checkElementIndex(index: Sint, size: Sint) {
	if (index < 0 || index >= size) {
		throw IndexOutOfBoundsException("index: $index, size: $size")
	}
}

internal fun checkPositionIndex(index: Sint, size: Sint) {
	if (index < 0 || index > size) {
		throw IndexOutOfBoundsException("index: $index, size: $size")
	}
}

internal fun checkRangeIndexes(fromIndex: Sint, toIndex: Sint, size: Sint) {
	if (fromIndex < 0 || toIndex > size) {
		throw IndexOutOfBoundsException("fromIndex: $fromIndex, toIndex: $toIndex, size: $size")
	}
	if (fromIndex > toIndex) {
		throw IllegalArgumentException("fromIndex: $fromIndex > toIndex: $toIndex")
	}
}

internal fun checkBoundsIndexes(startIndex: Sint, endIndex: Sint, size: Sint) {
	if (startIndex < 0 || endIndex > size) {
		throw IndexOutOfBoundsException("startIndex: $startIndex, endIndex: $endIndex, size: $size")
	}
	if (startIndex > endIndex) {
		throw IllegalArgumentException("startIndex: $startIndex > endIndex: $endIndex")
	}
}

internal fun orderedHashCode(c: Collection<*>): Int {
	var hashCode = 1
	for (e in c) {
		hashCode = 31 * hashCode + (e?.hashCode() ?: 0)
	}
	return hashCode
}

internal fun orderedEquals(c: Collection<*>, other: Collection<*>): Boolean {
	if (c.size != other.size) return false

	val otherIterator = other.iterator()
	for (elem in c) {
		val elemOther = otherIterator.next()
		if (elem != elemOther) {
			return false
		}
	}
	return true
}

fun <T> Iterable<T>.chunked(size: Sint): List<List<T>> {
	return windowed(size, size, partialWindows = true)
}



fun <T, R> Iterable<T>.chunked(size: Sint, transform: (List<T>) -> R): List<R> {
	return windowed(size, size, partialWindows = true, transform = transform)
}


internal fun checkWindowSizeStep(size: Sint, step: Sint) {
	require(size > 0 && step > 0) {
		if (size != step)
			"Both size $size and step $step must be greater than zero."
		else
			"size $size must be greater than zero."
	}
}

fun <T> Iterable<T>.windowed(size: Sint, step: Sint = 1.s, partialWindows: Boolean = false): List<List<T>> {
	checkWindowSizeStep(size, step)
	if (this is RandomAccess && this is List) {
		val thisSize = this.size
		val resultCapacity = thisSize / step + if (thisSize % step == 0.s) 0 else 1
		val result = ArrayList<List<T>>(resultCapacity.i)
		var idx = 0.s
		while (idx in 0.s until thisSize) {
			val windowSize = size.coerceAtMost(thisSize - idx)
			if (windowSize < size && !partialWindows) break
			result.add(List(windowSize.i) { this[it + idx] })
			idx += step
		}
		return result
	}
	val result = ArrayList<List<T>>()
	windowedIterator(iterator(), size, step, partialWindows, reuseBuffer = false).forEach {
		result.add(it)
	}
	return result
}



fun <T, R> Iterable<T>.windowed(
	size: Sint,
	step: Sint = 1.s,
	partialWindows: Boolean = false,
	transform: (List<T>) -> R
): List<R> {
	checkWindowSizeStep(size, step)
	if (this is RandomAccess && this is List) {
		val thisSize = this.size.s
		val resultCapacity = thisSize / step + if (thisSize divBy step ) 0 else 1
		val result = ArrayList<R>(resultCapacity.i)
		val window = MovingSubList(this)
		var idx = 0.s
		while (idx in 0 until thisSize) {
			val windowSize = size.coerceAtMost(thisSize - idx)
			if (!partialWindows && windowSize < size) break
			window.move(idx, idx + windowSize)
			result.add(transform(window))
			idx += step
		}
		return result
	}
	val result = ArrayList<R>()
	windowedIterator(iterator(), size, step, partialWindows, reuseBuffer = true).forEach {
		result.add(transform(it))
	}
	return result
}

@JvmName("averageOfSint")
fun Iterable<Sint>.average(): Double {
	var sum: Double = 0.0
	var count: Sint = 0.s
	for (element in this) {
		sum += element.l.toDouble()
		++count
	}
	return if (count == 0.s) Double.NaN else sum / count.l.toDouble()
}

private fun <T> List<T>.optimizeReadOnlyList() = when (size) {
	0 -> emptyList()
	1 -> listOf(this[0])
	else -> this
}