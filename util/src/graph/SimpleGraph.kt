package graph

// no double edges
class SimpleGraph<T> {
	val neighbours: MutableMap<T, MutableMap<T, Double>> = mutableMapOf()
	fun connect(a: T, b: T, weight:Double = 1.0) {
		neighbours.getOrPut(a) { mutableMapOf() }[b] = weight
		neighbours.getOrPut(b) { mutableMapOf() }[a] = weight
	}

	fun neighboursOf(node: T) = neighbours[node] ?: emptyMap()
}