@file:Suppress("MemberVisibilityCanBePrivate")

package me.kroppeb.aoc.helpers.graph

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

class CGraph<T>(val neighbours: Map<T, Map<T, Sint>>) {  // neighbours[a][b] is the cost of moving from a to b
	private val nodeMap = neighbours.keys.associateWith { GNode(it) { this } }
	val nodes: Collection<GNode<T>> get() = nodeMap.values.toSet()
	val values: Set<T> get() = neighbours.keys

	fun bfs(start: T, isEnd: (T) -> Boolean): BfsResult<T>? {
		return bfsPath(start, isEnd) { neighbours[it]?.keys ?: emptyList() }
	}

	fun bfs(start: Iterable<T>, isEnd: (T) -> Boolean): BfsResult<T>? {
		return bfslPath(start.toList(), isEnd) { neighbours[it]?.keys ?: emptyList() }
	}

	fun bfs(start: T, end: T): BfsResult<T>? {
		return bfs(start) { it == end }
	}

	fun bfs(start: Iterable<T>, end: T): BfsResult<T>? {
		return bfs(start) { it == end }
	}


	fun bfs(start: Iterable<T>, end: Iterable<T>): BfsResult<T>? {
		val check = end.toSet()
		return bfs(start) { it in check }
	}

	fun dijkstra(start: T, isEnd: (T) -> Boolean): DijkstraResult<T>? {
		return dijkstra(start, isEnd) {
			neighbours[it]?.entries?.map { (k, v) -> k to v } ?: emptyList()
		}
	}

	fun dijkstra(start: Iterable<T>, isEnd: (T) -> Boolean): DijkstraResult<T>? {
		return dijkstral(start.toList(), isEnd) {
			neighbours[it]?.entries?.map { (k, v) -> k to v } ?: emptyList()
		}
	}

	fun dijkstra(start: T, end: T): DijkstraResult<T>? {
		return dijkstra(start) { it == end }
	}

	fun dijkstra(start: Iterable<T>, end: T): DijkstraResult<T>? {
		return dijkstra(start) { it == end }
	}


	fun dijkstra(start: Iterable<T>, end: Iterable<T>): DijkstraResult<T>? {
		val check = end.toSet()
		return dijkstra(start) { it in check }
	}

	fun floodFill(start: T): Set<T> {
		return floodFill(start) { neighbours[it]?.keys ?: emptyList() }
	}

	fun floodFill(start: Iterable<T>): Set<T> {
		return floodFilll(start.toList()) { neighbours[it]?.keys ?: emptyList() }
	}

	operator fun get(value: T): GNode<T> {
		return nodeMap[value] ?: run {
			// TODO: WARN?
			GNode(value) { this }
		}
	}
}

class GNode<T>(val value: T, val accessor: () -> CGraph<T>) {
	val neighbours: List<Pair<GNode<T>, Sint>>
		get() = accessor().neighbours[value]?.map { (k, v) -> GNode(k, accessor) to v } ?: emptyList()
	val ns: List<GNode<T>>
		get() = accessor().neighbours[value]?.keys?.map { GNode(it, accessor) } ?: emptyList()

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is GNode<*>) return false
		if (value != other.value) return false
		return true
	}

	override fun hashCode(): Int {
		return value.hashCode()
	}
}

fun <T> buildGraph(block: GraphBuilder<T>.() -> Unit): CGraph<T> {
	return GraphBuilder<T>().apply(block).build()
}

class GraphBuilder<T> {
	private val neighbours = mutableMapOf<T, MutableMap<T, Sint>>()
	private var built: CGraph<T>? = null

	fun connect(a: T, b: T, cost: Sint) {
		require(built == null) { "Graph already built" }
		neighbours.getOrPut(a) { mutableMapOf() }[b] = cost
		neighbours.getOrPut(b) { mutableMapOf() }[a] = cost
	}

	@JvmName("TTWeightS")
	infix fun Pair<T, T>.weight(weight: Sint) = connect(first, second, weight)

	@JvmName("TTWeightI")
	infix fun Pair<T, T>.weight(weight: Int) = this weight weight.s

	@JvmName("TTWeightL")
	infix fun Pair<T, T>.weight(weight: Long) = this weight weight.s

	@JvmName("TTCon")
	infix fun T.con(other: T) = this to other weight 1

	@JvmName("TTCon0")
	infix fun T.con0(other: T) = this to other weight 0

	@JvmName("TITWeightS")
	infix fun Pair<T, Iterable<T>>.weight(weight: Sint) {
		for (t in second) {
			connect(first, t, weight)
		}
	}

	@JvmName("TITWeightI")
	infix fun Pair<T, Iterable<T>>.weight(weight: Int) = this weight weight.s

	@JvmName("TITWeightL")
	infix fun Pair<T, Iterable<T>>.weight(weight: Long) = this weight weight.s

	@JvmName("TITConAll")
	infix fun T.conAll(other: Iterable<T>) = this to other weight 1

	@JvmName("TITConAll0")
	infix fun T.conAll0(other: Iterable<T>) = this to other weight 0

	@JvmName("TMTConAllS")
	infix fun T.conAll(other: Map<T, Sint>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("TMTConAllI")
	infix fun T.conAll(other: Map<T, Int>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("TMTConAllL")
	infix fun T.conAll(other: Map<T, Long>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("GTWeightS")
	infix fun Pair<GNode<T>, T>.weight(weight: Sint) {
		first.value to second weight weight
	}

	@JvmName("GTWeightI")
	infix fun Pair<GNode<T>, T>.weight(weight: Int) = this weight weight.s

	@JvmName("GTWeightL")
	infix fun Pair<GNode<T>, T>.weight(weight: Long) = this weight weight.s

	@JvmName("GTCon")
	infix fun GNode<T>.con(other: T) = this to other weight 1

	@JvmName("GTCon0")
	infix fun GNode<T>.con0(other: T) = this to other weight 0

	@JvmName("GITWeightS")
	infix fun Pair<GNode<T>, Iterable<T>>.weight(weight: Sint) {
		for (t in second) {
			connect(first.value, t, weight)
		}
	}

	@JvmName("GITWeightI")
	infix fun Pair<GNode<T>, Iterable<T>>.weight(weight: Int) = this weight weight.s

	@JvmName("GITWeightL")
	infix fun Pair<GNode<T>, Iterable<T>>.weight(weight: Long) = this weight weight.s

	@JvmName("GITConAll")
	infix fun GNode<T>.conAll(other: Iterable<T>) = this to other weight 1

	@JvmName("GITConAll0")
	infix fun GNode<T>.conAll0(other: Iterable<T>) = this to other weight 0

	@JvmName("GMTConAllS")
	infix fun GNode<T>.conAll(other: Map<T, Sint>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("GMTConAllI")
	infix fun GNode<T>.conAll(other: Map<T, Int>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("GMTConAllL")
	infix fun GNode<T>.conAll(other: Map<T, Long>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("TGWeightS")
	infix fun Pair<T, GNode<T>>.weight(weight: Sint) {
		connect(first, second.value, weight)
	}

	@JvmName("TGWeightI")
	infix fun Pair<T, GNode<T>>.weight(weight: Int) = this weight weight.s

	@JvmName("TGWeightL")
	infix fun Pair<T, GNode<T>>.weight(weight: Long) = this weight weight.s

	@JvmName("TGCon")
	infix fun T.con(other: GNode<T>) = this to other weight 1

	@JvmName("TGCon0")
	infix fun T.con0(other: GNode<T>) = this to other weight 0

	@JvmName("TIGWeightS")
	infix fun Pair<T, Iterable<GNode<T>>>.weight(weight: Sint) {
		for (t in second) {
			connect(first, t.value, weight)
		}
	}

	@JvmName("TIGWeightI")
	infix fun Pair<T, Iterable<GNode<T>>>.weight(weight: Int) = this weight weight.s

	@JvmName("TIGWeightL")
	infix fun Pair<T, Iterable<GNode<T>>>.weight(weight: Long) = this weight weight.s

	@JvmName("TIGConAll")
	infix fun T.conAll(other: Iterable<GNode<T>>) = this to other weight 1

	@JvmName("TIGConAll0")
	infix fun T.conAll0(other: Iterable<GNode<T>>) = this to other weight 0

	@JvmName("TMGConAllS")
	infix fun T.conAll(other: Map<GNode<T>, Sint>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("TMGConAllI")
	infix fun T.conAll(other: Map<GNode<T>, Int>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("TMGConAllL")
	infix fun T.conAll(other: Map<GNode<T>, Long>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("GGWeightS")
	infix fun Pair<GNode<T>, GNode<T>>.weight(weight: Sint) {
		first.value to second.value weight weight
	}

	@JvmName("GGWeightI")
	infix fun Pair<GNode<T>, GNode<T>>.weight(weight: Int) = this weight weight.s

	@JvmName("GGWeightL")
	infix fun Pair<GNode<T>, GNode<T>>.weight(weight: Long) = this weight weight.s

	@JvmName("GGCon")
	infix fun GNode<T>.con(other: GNode<T>) = this to other weight 1

	@JvmName("GGCon0")
	infix fun GNode<T>.con0(other: GNode<T>) = this to other weight 0

	@JvmName("GIGWeightS")
	infix fun Pair<GNode<T>, Iterable<GNode<T>>>.weight(weight: Sint) {
		for (t in second) {
			connect(first.value, t.value, weight)
		}
	}

	@JvmName("GIGWeightI")
	infix fun Pair<GNode<T>, Iterable<GNode<T>>>.weight(weight: Int) = this weight weight.s

	@JvmName("GIGWeightL")
	infix fun Pair<GNode<T>, Iterable<GNode<T>>>.weight(weight: Long) = this weight weight.s

	@JvmName("GIGConAll")
	infix fun GNode<T>.conAll(other: Iterable<GNode<T>>) = this to other weight 1

	@JvmName("GIGConAll0")
	infix fun GNode<T>.conAll0(other: Iterable<GNode<T>>) = this to other weight 0

	@JvmName("GMGConAllS")
	infix fun GNode<T>.conAll(other: Map<GNode<T>, Sint>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("GMGConAllI")
	infix fun GNode<T>.conAll(other: Map<GNode<T>, Int>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	@JvmName("GMGConAllL")
	infix fun GNode<T>.conAll(other: Map<GNode<T>, Long>) {
		for ((t, w) in other) {
			this to t weight w
		}
	}

	fun node(value: T): GNode<T> {
		return GNode(value) { this.built ?: error("Graph not yet ready") }
	}


	fun build(): CGraph<T> {
		require(built == null) { "Graph already built" }
		return CGraph(neighbours)
	}
}

fun <T> Map<T, Map<T, Sint>>.toCGraph(): CGraph<T> {
	return CGraph(this)
}

@JvmName("toCGraphPair")
fun <T> Map<Pair<T, T>, Sint>.toCGraph(): CGraph<T> =
	this.entries
		.groupBy { it.key.first }
		.mapValues { (_, v) -> v.associate { it.key.second to it.value } }
		.toCGraph()