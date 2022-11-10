package graph

import java.util.*

class BfsResult<out State>(val path: List<State>) {
	val start get() = path.first()
	val end get() = path.last()
	val length get() = path.size - 1
}

inline fun <State> bfs(start: State, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Pair<State?, Int> {
	val seen = mutableSetOf(start)
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) return current to dist

			for (i in next(current)) {
				if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return null to dist
}

inline fun <State> bfsPath(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? {
	val seen = mutableSetOf(start)
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<State>()
	val back = mutableMapOf<State, State>()
	var dist = -1

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) {
				val path = mutableListOf(current)
				var c = current
				while (true) {
					c = back[c] ?: break
					path += c
				}
				return BfsResult(path)
			}

			for (i in next(current)) {
				if (seen.add(i)) {
					nextQueue.add(i)
					back[i] = current
				}
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return null
}

class DijkstraResult<out State>(val path: List<State>, val cost: Double) {
	val start get() = path.first()
	val end get() = path.last()
	val length get() = path.size - 1
}

fun <State> dijkstra(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Int>>
): DijkstraResult<State>? = dijkstraD(start, isEnd) {
	next(it).map { val (state, cost) = it; state to cost.toDouble() }
}

inline fun <State> dijkstraD(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Double>>
): DijkstraResult<State>? {
	val costs = mutableMapOf(start to 0.0)
	val queue = PriorityQueue<Pair<State, Double>>(compareBy { it.second })
	queue += start to 0.0
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResult(path, currentCost)
		}

		for ((next, cost) in next(current)) {
			val newCost = currentCost + cost
			val previousCost = costs.getOrDefault(next, Double.POSITIVE_INFINITY)
			if (newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}