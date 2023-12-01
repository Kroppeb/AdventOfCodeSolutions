package me.kroppeb.aoc.helpers.graph

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

fun <T> CGraph<T>.allPairsShortestPath(): Map<Pair<T, T>, Sint> {
	val dist = values.associateWith { mutableMapOf<T, Sint>() }.toMutableMap()
	for (node in values) {
		dist[node]!![node] = 0.s
	}
	for (node in values) {
		for ((neighbour, cost) in neighbours[node]!!) {
			dist[node]!![neighbour] = cost
		}
	}
	for (k in values) {
		for (i in values) {
			for (j in values) {
				val a = dist[i]!![k]
				val b = dist[k]!![j]
				val c = dist[i]!![j]
				if (a != null && b != null && (c == null || a + b < c)) {
					dist[i]!![j] = a + b
				}
			}
		}
	}

	return dist.flatMap { (a, b) -> b.map { (c, d) -> (a to c) to d } }.toMap()
}


fun <T> CGraph<T>.roi(predicate: (GNode<T>) -> Boolean): CGraph<T> { // also removes all self edges
	val keep = nodes.filter(predicate).map { it.value }.toSet()
	val remove = values - keep

	val dist = values.associateWith { mutableMapOf<T, Sint>() }.toMutableMap()
	for (node in values) {
		dist[node]!![node] = 0.s
	}
	for (node in values) {
		for ((neighbour, cost) in neighbours[node]!!) {
			dist[node]!![neighbour] = cost
		}
	}
	for (k in remove) {
		// we don't do a full all pairs shortest path, instead we only iterate over the nodes we want to remove
		for (i in values) {
			for (j in values) {
				val a = dist[i]!![k]
				val b = dist[k]!![j]
				val c = dist[i]!![j]
				if (a != null && b != null && (c == null || a + b < c)) {
					dist[i]!![j] = a + b
				}
			}
		}
	}

	return dist
		.filterKeys { it in keep }
		.flatMap { (a, b) -> b.filterKeys { it in keep && it != a }.map { (c, d) -> (a to c) to d } }
		.toMap()
		.toCGraph()
}