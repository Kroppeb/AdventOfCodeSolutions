package helpers

import kotlin.collections.ArrayList

fun <T> Iterator<T>.getNext(): T {
	hasNext()
	return next()
}

fun <T> Iterator<T>.getNextOrNull(): T? {
	if (hasNext())
		return next()
	return null
}

fun String.e() = map { it }
@JvmName("e2")
fun Iterable<String>.e() = map { it.e() }

@JvmName("e3")
fun Iterable<Iterable<String>>.e() = map { it.e() }

inline fun <T, R> Iterable<Iterable<T>>.map2(convert: (T) -> R): List<List<R>> = map { it.map(convert) }

inline fun <T, R> Iterable<T>.rleDecode(value: (T) -> R, length: (T) -> Int) = flatMap { listOf(value(it)).repeat(length(it)) }
inline fun <T, R> Iterable<T>.rleEncode(convert: (T, Int) -> R) = groupBy { it }.map { convert(it.key, it.value.size) }

fun <E> List<E>.repeat(length: Int): List<E> {
	if (size == 0 || length == 0)
		return emptyList()
	val list = ArrayList<E>(size * length)
	repeat(length) {
		list.addAll(this)
	}
	return list
}

operator fun <T> Set<T>.times(other: Set<T>): Set<T> = intersect(other)
operator fun <T> Set<T>.plus(other: Set<T>): Set<T> = union(other)

fun <K, A> Map<K, A>.intersect(other: Iterable<K>) = this.keys.intersect(other).associateWith { this[it]!! }

fun <K, A, B> Map<K, A>.union(other: Map<K, B>) = this.keys.union(other.keys).associateWith { this[it] to other[it] }
inline fun <K, A> Map<K, A>.merge(other: Map<K, A>, m: (A, A) -> A) = this.keys.union(other.keys).associateWith {
	this[it]?.let { a -> other[it]?.let { b -> m(a, b) } ?: a } ?: other[it]!!
}

inline fun <K, A, B, R> Map<K, A>.mergeMap(other: Map<K, B>, m: (A?, B?) -> R) = this.union(other).mapValues { (_, v) ->
	m(v.first, v.second)
}

fun <K, A, B> Map<K, A>.intersect(other: Map<K, B>): Map<K, Pair<A, B>> = this.entries.mapNotNull { (key, value) ->
	if (key in other) key to (value to other[key]!!)
	else null
}.toMap()

inline fun <K, A, B, R> Map<K, A>.intersectMap(other: Map<K, B>, m: (A, B) -> R): Map<K, R> = this.entries.mapNotNull { (key, value) ->
	if (key in other) key to m(value, other[key]!!)
	else null
}.toMap()

fun <K, V : Comparable<V>> Map<K, V>.minByValue() = minBy { it.value }!!.key
fun <K : Comparable<K>, V> Map<K, V>.minByKey() = minBy { it.key }!!.value

operator fun <E> List<E>.times(count: Int) = repeat(count)

/**
 * Seed isn't returned, the retured list has length times
 */
fun <T> generateTimes(times: Int, seed: T, next: (T) -> T): List<T> {
	var acc = seed
	val ret = mutableListOf<T>()
	repeat(times) {
		acc = next(acc)
		ret.add(acc)
	}
	return ret
}

/**
 * Seed isn't returned, the retured list has length times
 */
fun <S, T> generateStateTimes(times: Int, seed: S, next: (state: S) -> Pair<S, T>): List<T> {
	var acc = seed
	val ret = mutableListOf<T>()
	repeat(times) {
		val (s, t) = next(acc)
		acc = s
		ret.add(t)
	}
	return ret
}

inline fun <T, R> Iterable<T>.scan(start: R, transform: (R, T) -> R): List<R> {
	var acc = start
	val ret = mutableListOf<R>()
	for (i in this) {
		acc = transform(acc, i)
		ret.add(acc)
	}
	return ret
}

inline fun <T, S, R> Iterable<T>.stateScan(start: S, transform: (S, T) -> Pair<S, R>): List<R> {
	var acc = start
	val ret = mutableListOf<R>()
	for (i in this) {
		val (s, r) = transform(acc, i)
		acc = s
		ret.add(r)
	}
	return ret
}

inline fun <T> Iterable<T>.scan(transform: (T, T) -> T): List<T> {
	val iter = iterator()
	if (!iter.hasNext())
		return emptyList()
	var acc = iter.next()
	val ret = mutableListOf<T>(acc)
	for (i in iter) {
		acc = transform(acc, i)
		ret.add(acc)
	}
	return ret
}

fun <T> Iterable<T>.countEach(): Map<T, Int> {
	val counts = mutableMapOf<T, Int>()
	for (element in this)
		counts.merge(element, 1, Int::plus)
	return counts
}

fun <T> Iterable<T>.blockCounts(): List<Pair<T, Int>> {
	val iter = iterator()
	if (!iter.hasNext())
		return emptyList()
	var acc = iter.next()
	var count = 1
	val ret = mutableListOf<Pair<T, Int>>()
	for (i in iter) {
		if (acc == i)
			count++
		else {
			ret.add(acc to count)
			acc = i
			count = 1

		}
	}
	ret.add(acc to count)
	return ret
}

fun <T> Iterable<T>.blocks(): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext())
		return emptyList()
	var acc = iter.next()
	var count = mutableListOf<T>(acc)
	val ret = mutableListOf<List<T>>()
	for (i in iter) {
		if (acc == i)
			count.add(i)
		else {
			ret.add(count)
			acc = i
			count = mutableListOf<T>(acc)

		}
	}
	ret.add(count)
	return ret
}

/**
 * Verifies by trying to sort
 */
fun <T : Comparable<T>> Iterable<T>.isSorted(): Boolean = this.sorted() == this.toList()

fun <T : Comparable<T>> Iterable<T>.isAscending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext())
		return true
	var acc = iter.next()
	for (i in iter) {
		if (acc > i)
			return false
		acc = i
	}
	return true
}

fun <T : Comparable<T>> Iterable<T>.isDescending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext())
		return true
	var acc = iter.next()
	for (i in iter) {
		if (acc < i)
			return false
		acc = i
	}
	return true
}

fun <T : Comparable<T>> Iterable<T>.isStrictAscending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext())
		return true
	var acc = iter.next()
	for (i in iter) {
		if (acc >= i)
			return false
		acc = i
	}
	return true
}

fun <T : Comparable<T>> Iterable<T>.isStrictDescending(): Boolean {
	val iter = iterator()
	if (!iter.hasNext())
		return true
	var acc = iter.next()
	for (i in iter) {
		if (acc <= i)
			return false
		acc = i
	}
	return true
}

/**
 *
 * @param transform called once for each item in the iterable
 */
inline fun <T, R> Iterable<T>.blockBy(transform: (T) -> R): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext())
		return emptyList()
	val start = iter.next()
	var key = transform(start)
	var count = mutableListOf(start)
	val result = mutableListOf<List<T>>()
	for (i in iter) {
		val ikey = transform(i)
		if (key == ikey)
			count.add(i)
		else {
			result.add(count)
			key = ikey
			count = mutableListOf(start)

		}
	}
	return result
}

fun Iterable<*>.areDistinct(): Boolean {
	val seen = mutableSetOf<Any?>()
	for (i in this)
		if (!seen.add(i))
			return false
	return true
}

inline fun <T, R> Iterable<T>.flatMapIndexed(transform: (Int, T) -> Iterable<R>): List<R> = mapIndexed(transform).flatten()

fun <T> Iterable<T>.pairWise(): List<Pair<T, T>> = flatMapIndexed { i, v -> drop(i + 1).map { v to it } }
fun <T> Iterable<T>.orderedPairWise(): List<Pair<T, T>> = flatMapIndexed { i, v ->
	filterIndexed { i2, _ -> i != i2 }.map { v to it }
}

fun <T> Iterable<T>.selfPairWise(): List<Pair<T, T>> = flatMapIndexed { i, v -> drop(i).map { v to it } }

fun <T> Iterable<T>.cartesianSquare(): List<Pair<T, T>> = flatMap { v -> map { v to it } }
fun <T, R> Iterable<T>.cartesianProduct(other: Iterable<R>): List<Pair<T, R>> = flatMap { v -> other.map { v to it } }
inline fun <T, R, S> Iterable<T>.cartesianProduct(other: Iterable<R>, transform: (T, R) -> S): List<S> = flatMap { v -> other.map { transform(v, it) } }
fun <T> Iterable<T>.cartesianPower(count: Int): List<List<T>> = if (count < 1) emptyList() else cartesianPower1(count)
fun <T> Iterable<T>.cartesianPower1(count: Int): List<List<T>> = if (count == 1) this.map { listOf(it) } else
	cartesianPower1(count - 1).cartesianProduct(this) { a, b -> a + b }


fun <T> Iterator<T>.powerSet(): List<List<T>> {
	val iter = iterator()
	if (!iter.hasNext())
		return emptyList()
	val pre = listOf(iter.getNext())
	val next = iter.powerSet()
	return next + next.map { pre + it }
}
fun <T> Iterable<T>.powerSet(): List<List<T>> = iterator().powerSet()

fun Iterable<Int>.cumSum() = scan(Int::plus)

val <T>Iterable<Pair<T, *>>.firsts get() = map { it.first }
val <T>Iterable<Pair<*, T>>.seconds get() = map { it.second }


//region list components
operator fun <T> List<T>.get(indexes: IntRange) = subList(indexes.first, indexes.last + 1)

operator fun <E> List<E>.component6(): E = this[6]
operator fun <E> List<E>.component7(): E = this[7]
operator fun <E> List<E>.component8(): E = this[8]
operator fun <E> List<E>.component9(): E = this[9]
operator fun <E> List<E>.component10(): E = this[10]
operator fun <E> List<E>.component11(): E = this[11]
operator fun <E> List<E>.component12(): E = this[12]
operator fun <E> List<E>.component13(): E = this[13]
operator fun <E> List<E>.component14(): E = this[14]
operator fun <E> List<E>.component15(): E = this[15]
operator fun <E> List<E>.component16(): E = this[16]
operator fun <E> List<E>.component17(): E = this[17]
operator fun <E> List<E>.component18(): E = this[18]
operator fun <E> List<E>.component19(): E = this[19]
operator fun <E> List<E>.component20(): E = this[20]
operator fun <E> List<E>.component21(): E = this[21]
operator fun <E> List<E>.component22(): E = this[22]
operator fun <E> List<E>.component23(): E = this[23]
operator fun <E> List<E>.component24(): E = this[24]
operator fun <E> List<E>.component25(): E = this[25]
operator fun <E> List<E>.component26(): E = this[26]
operator fun <E> List<E>.component27(): E = this[27]
operator fun <E> List<E>.component28(): E = this[28]
operator fun <E> List<E>.component29(): E = this[29]
operator fun <E> List<E>.component30(): E = this[30]
operator fun <E> List<E>.component31(): E = this[31]
operator fun <E> List<E>.component32(): E = this[32]
operator fun <E> List<E>.component33(): E = this[33]
operator fun <E> List<E>.component34(): E = this[34]
operator fun <E> List<E>.component35(): E = this[35]
operator fun <E> List<E>.component36(): E = this[36]
operator fun <E> List<E>.component37(): E = this[37]
operator fun <E> List<E>.component38(): E = this[38]
operator fun <E> List<E>.component39(): E = this[39]
operator fun <E> List<E>.component40(): E = this[40]
operator fun <E> List<E>.component41(): E = this[41]
operator fun <E> List<E>.component42(): E = this[42]
operator fun <E> List<E>.component43(): E = this[43]
operator fun <E> List<E>.component44(): E = this[44]
operator fun <E> List<E>.component45(): E = this[45]
operator fun <E> List<E>.component46(): E = this[46]
operator fun <E> List<E>.component47(): E = this[47]
operator fun <E> List<E>.component48(): E = this[48]
operator fun <E> List<E>.component49(): E = this[49]
operator fun <E> List<E>.component50(): E = this[50]
operator fun <E> List<E>.component51(): E = this[51]
operator fun <E> List<E>.component52(): E = this[52]
operator fun <E> List<E>.component53(): E = this[53]
operator fun <E> List<E>.component54(): E = this[54]
operator fun <E> List<E>.component55(): E = this[55]
operator fun <E> List<E>.component56(): E = this[56]
operator fun <E> List<E>.component57(): E = this[57]
operator fun <E> List<E>.component58(): E = this[58]
operator fun <E> List<E>.component59(): E = this[59]
operator fun <E> List<E>.component60(): E = this[60]
operator fun <E> List<E>.component61(): E = this[61]
operator fun <E> List<E>.component62(): E = this[62]
operator fun <E> List<E>.component63(): E = this[63]
operator fun <E> List<E>.component64(): E = this[64]
operator fun <E> List<E>.component65(): E = this[65]
operator fun <E> List<E>.component66(): E = this[66]
operator fun <E> List<E>.component67(): E = this[67]
operator fun <E> List<E>.component68(): E = this[68]
operator fun <E> List<E>.component69(): E = this[69]
operator fun <E> List<E>.component70(): E = this[70]
operator fun <E> List<E>.component71(): E = this[71]
operator fun <E> List<E>.component72(): E = this[72]
operator fun <E> List<E>.component73(): E = this[73]
operator fun <E> List<E>.component74(): E = this[74]
operator fun <E> List<E>.component75(): E = this[75]
operator fun <E> List<E>.component76(): E = this[76]
operator fun <E> List<E>.component77(): E = this[77]
operator fun <E> List<E>.component78(): E = this[78]
operator fun <E> List<E>.component79(): E = this[79]
operator fun <E> List<E>.component80(): E = this[80]
operator fun <E> List<E>.component81(): E = this[81]
operator fun <E> List<E>.component82(): E = this[82]
operator fun <E> List<E>.component83(): E = this[83]
operator fun <E> List<E>.component84(): E = this[84]
operator fun <E> List<E>.component85(): E = this[85]
operator fun <E> List<E>.component86(): E = this[86]
operator fun <E> List<E>.component87(): E = this[87]
operator fun <E> List<E>.component88(): E = this[88]
operator fun <E> List<E>.component89(): E = this[89]
operator fun <E> List<E>.component90(): E = this[90]
operator fun <E> List<E>.component91(): E = this[91]
operator fun <E> List<E>.component92(): E = this[92]
operator fun <E> List<E>.component93(): E = this[93]
operator fun <E> List<E>.component94(): E = this[94]
operator fun <E> List<E>.component95(): E = this[95]
operator fun <E> List<E>.component96(): E = this[96]
operator fun <E> List<E>.component97(): E = this[97]
operator fun <E> List<E>.component98(): E = this[98]
operator fun <E> List<E>.component99(): E = this[99]
// endregion