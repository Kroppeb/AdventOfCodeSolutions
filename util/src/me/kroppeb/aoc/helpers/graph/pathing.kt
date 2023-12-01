package me.kroppeb.aoc.helpers.graph

import me.kroppeb.aoc.helpers.Loggable
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import java.util.*

data class BfsResult<out State>(val path: List<State>) : Loggable {
	val start get() = path.first()
	val end get() = path.last()
	val length get() = path.size - 1

	override fun getCopyString(): String = length.toString()

	override fun toString(): String {
		return "BfsResult(start=$start, end=$end, length=$length)"
	}
}

inline fun <State> bfs(start: State, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> {
	val seen = mutableSetOf(start)
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) return Res(current, dist)

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
	return Res(null, dist)
}

data class Res<State>(val state: State?, val dist: Int) : Loggable {
	val first get() = state
	val second get() = dist
	override fun getCopyString(): String = dist.toString()
}

inline fun <State> bfsl(starts: List<State>, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) return Res(current, dist)

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
	return Res(null, dist)
}


inline fun <State> bfsDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> {
	val seen = mutableSetOf(start)
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
		// each dist needs its own "seen" set
		seen.clear()
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current, dist)) return Res(current, dist)

			for (i in next(current, dist)) {
				if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return Res(null, dist)
}


inline fun <State> bfslDist(
	starts: List<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	var dist = -1

	while (queue.isNotEmpty()) {
		// each dist needs its own "seen" set
		seen.clear()
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current, dist)) return Res(current, dist)

			for (i in next(current, dist)) {
				if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return Res(null, dist)
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


inline fun <State> bfslPath(
	starts: List<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	val back = mutableMapOf<State, State>()
	var dist = -1.s

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

inline fun <State> bfsPathDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? {
	val seen = mutableSetOf(start)
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<State>()
	val back = mutableMapOf<State, State>()
	var dist = -1.s

	while (queue.isNotEmpty()) {
		// each dist needs its own "seen" set
		seen.clear()
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current, dist.i)) {
				val path = mutableListOf(current)
				var c = current
				while (true) {
					c = back[c] ?: break
					path += c
				}
				return BfsResult(path)
			}

			for (i in next(current, dist.i)) {
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


inline fun <State> bfslPathDist(
	starts: List<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.toMutableSet()
	var queue = starts.toMutableList()
	var nextQueue = mutableListOf<State>()
	val back = mutableMapOf<State, State>()
	var dist = -1

	while (queue.isNotEmpty()) {
		// each dist needs its own "seen" set
		seen.clear()
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current, dist)) {
				val path = mutableListOf(current)
				var c = current
				while (true) {
					c = back[c] ?: break
					path += c
				}
				return BfsResult(path)
			}

			for (i in next(current, dist)) {
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


data class DfsResult<out State>(val path: List<State>) : Loggable {
	val start get() = path.first()
	val end get() = path.last()
	val length get() = path.size - 1

	override fun getCopyString(): String = length.toString()

	override fun toString(): String {
		return "DfsResult(start=$start, end=$end, length=$length)"
	}
}


inline fun <State> dfs(start: State, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> {
	val seen = mutableSetOf(start)
	var stack = mutableListOf(start to 0)
	var maxDist = 0

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()
		if (isEnd(current)) return Res(current, dist)
		maxDist = maxOf(maxDist, dist)

		for (i in next(current).reversed()) {
			if (seen.add(i))
				stack.add(i to dist + 1)
		}
	}
	return Res(null, maxDist)
}


inline fun <State> dfsl(starts: List<State>, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Res<State> {
	val seen = starts.toMutableSet()
	var stack = starts.mapTo(mutableListOf()) { it to 0 }
	var maxDist = 0

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()
		if (isEnd(current)) return Res(current, dist)
		maxDist = maxOf(maxDist, dist)

		for (i in next(current).reversed()) {
			if (seen.add(i))
				stack.add(i to dist + 1)
		}
	}
	return Res(null, maxDist)
}

inline fun <State> dfsDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> {
	val seen = mutableSetOf(start to 0)
	var stack = mutableListOf(start to 0)
	var maxDist = 0

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()
		if (isEnd(current, dist)) return Res(current, dist)
		maxDist = maxOf(maxDist, dist)

		for (i in next(current, dist).reversed()) {
			if (seen.add(i to dist + 1))
				stack.add(i to dist + 1)
		}
	}
	return Res(null, maxDist)
}


inline fun <State> dfslDist(
	starts: List<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): Res<State> {
	val seen = starts.map { it to 0 }.toMutableSet()
	var stack = starts.mapTo(mutableListOf()) { it to 0 }
	var maxDist = 0

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()
		if (isEnd(current, dist)) return Res(current, dist)
		maxDist = maxOf(maxDist, dist)

		for (i in next(current, dist).reversed()) {
			if (seen.add(i to dist + 1))
				stack.add(i to dist + 1)
		}
	}
	return Res(null, maxDist)
}


inline fun <State> dfsPath(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? {
	val seen = mutableSetOf(start to 0)
	var stack = mutableListOf(start to 0)
	val back = mutableMapOf<State, State>()

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()

		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return BfsResult(path)
		}

		for (i in next(current).reversed()) {
			if (seen.add(i to dist + 1)) {
				stack.add(i to dist + 1)
				back[i] = current
			}
		}
	}
	return null
}


inline fun <State> dfslPath(
	starts: List<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.map { it to 0 }.toMutableSet()
	var stack = starts.mapTo(mutableListOf()) { it to 0 }
	val back = mutableMapOf<State, State>()

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()

		if (isEnd(current)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return BfsResult(path)
		}

		for (i in next(current).reversed()) {
			if (seen.add(i to dist + 1)) {
				stack.add(i to dist + 1)
				back[i] = current
			}
		}
	}
	return null
}


inline fun <State> dfsPathDist(
	start: State,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? {
	val seen = mutableSetOf(start to 0)
	var stack = mutableListOf(start to 0)
	val back = mutableMapOf<State, State>()

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()

		if (isEnd(current, dist)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return BfsResult(path)
		}

		for (i in next(current, dist).reversed()) {
			if (seen.add(i to dist + 1)) {
				stack.add(i to dist + 1)
				back[i] = current
			}
		}
	}
	return null
}


inline fun <State> dfsPathDist(
	starts: List<State>,
	isEnd: (State, Int) -> Boolean,
	next: (State, Int) -> Iterable<State>
): BfsResult<State>? {
	val seen = starts.map { it to 0 }.toMutableSet()
	var stack = starts.mapTo(mutableListOf()) { it to 0 }
	val back = mutableMapOf<State, State>()

	while (stack.isNotEmpty()) {
		val (current, dist) = stack.removeLast()

		if (isEnd(current, dist)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return BfsResult(path)
		}

		for (i in next(current, dist).reversed()) {
			if (seen.add(i to dist + 1)) {
				stack.add(i to dist + 1)
				back[i] = current
			}
		}
	}
	return null
}

data class DijkstraResult<out State>(val path: List<State>, val cost: Sint) : Loggable {
	val start get() = path.first()
	val end get() = path.last()
	val length get() = path.size - 1

	override fun getCopyString(): String = cost.toString()

	override fun toString(): String {
		return "DijkstraResult(start=$start, end=$end, length=$length, cost=$cost)"
	}
}

data class DijkstraResultD<out State>(val path: List<State>, val cost: Double) : Loggable {
	val start get() = path.first()
	val end get() = path.last()
	val length get() = path.size - 1

	override fun getCopyString(): String = cost.toString()

	override fun toString(): String {
		return "DijkstraResult(start=$start, end=$end, length=$length, cost=$cost)"
	}
}

inline fun <State> dijkstraI(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Int>>
): DijkstraResult<State>? = dijkstra(start, isEnd) { next(it).map { (state, cost) -> state to cost.s } }

inline fun <State> dijkstra(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? {
	val costs = mutableMapOf(start to 0.s)
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += start to 0.s
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
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}


inline fun <State> dijkstral(
	starts: List<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
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
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}


inline fun <State> dijkstraD(
	start: State,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? {
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
			return DijkstraResultD(path, currentCost)
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


inline fun <State> dijkstralD(
	starts: List<State>,
	isEnd: (State) -> Boolean,
	next: (State) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? {
	val costs = starts.associateWith { 0.0 }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Double>>(compareBy { it.second })
	queue += starts.map { it to 0.0 }
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
			return DijkstraResultD(path, currentCost)
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


inline fun <State> dijkstraDist(
	start: State,
	isEnd: (State, Sint) -> Boolean,
	next: (State, Sint) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? {
	val costs = mutableMapOf(start to 0.s)
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += start to 0.s
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current, currentCost)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResult(path, currentCost)
		}

		for ((next, cost) in next(current, currentCost)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}


inline fun <State> dijkstralDist(
	starts: List<State>,
	isEnd: (State, Sint) -> Boolean,
	next: (State, Sint) -> Iterable<Pair<State, Sint>>
): DijkstraResult<State>? {
	val costs = starts.associateWith { 0.s }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Sint>>(compareBy { it.second })
	queue += starts.map { it to 0.s }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current, currentCost)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResult(path, currentCost)
		}

		for ((next, cost) in next(current, currentCost)) {
			val newCost = currentCost + cost
			val previousCost = costs[next]
			if (previousCost == null || newCost < previousCost) {
				costs[next] = newCost
				queue += next to newCost
				back[next] = current
			}
		}
	}

	return null
}

inline fun <State> dijkstraDDist(
	start: State,
	isEnd: (State, Double) -> Boolean,
	next: (State, Double) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? {
	val costs = mutableMapOf(start to 0.0)
	val queue = PriorityQueue<Pair<State, Double>>(compareBy { it.second })
	queue += start to 0.0
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current, currentCost)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResultD(path, currentCost)
		}

		for ((next, cost) in next(current, currentCost)) {
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


inline fun <State> dijkstralDDist(
	starts: List<State>,
	isEnd: (State, Double) -> Boolean,
	next: (State, Double) -> Iterable<Pair<State, Double>>
): DijkstraResultD<State>? {
	val costs = starts.associateWith { 0.0 }.toMutableMap()
	val queue = PriorityQueue<Pair<State, Double>>(compareBy { it.second })
	queue += starts.map { it to 0.0 }
	val back = mutableMapOf<State, State>()

	while (queue.isNotEmpty()) {
		val (current, currentCost) = queue.poll()
		if (costs[current] != currentCost) continue
		if (isEnd(current, currentCost)) {
			val path = mutableListOf(current)
			var c = current
			while (true) {
				c = back[c] ?: break
				path += c
			}
			return DijkstraResultD(path, currentCost)
		}

		for ((next, cost) in next(current, currentCost)) {
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


inline fun <State> floodFill(start: State, next: (State) -> Iterable<State>): Set<State> {
	val stack = mutableListOf(start)
	val visited = mutableSetOf<State>()

	while (stack.isNotEmpty()) {
		val current = stack.removeLast()
		if (current in visited) continue
		visited += current
		stack += next(current)
	}

	return visited
}

inline fun <State> floodFilll(starts: List<State>, next: (State) -> Iterable<State>): Set<State> {
	val stack = starts.toMutableList()
	val visited = mutableSetOf<State>()

	while (stack.isNotEmpty()) {
		val current = stack.removeLast()
		if (!visited.add(current)) {
			continue
		}
		stack += next(current)
	}

	return visited
}