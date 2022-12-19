package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s
import me.kroppeb.aoc.helpers.sint.sumOf

class BoundsTree<B : BoundsN<B, P, Sint>, P:PointN<P,Sint>, S>(val bounds: B, var state: S) {
	val children: MutableList<BoundsTree<B, P, S>> = mutableListOf()

	fun set(newBounds: B, newState: S) {
		require(bounds.contains(newBounds)) { "Bounds $newBounds is not contained in $bounds" }
		if (this.bounds == newBounds) {
			this.state = newState
			return
		} else if (children.isEmpty()) {
			if (state == newState) return
			children += newBounds.fracture(bounds).map { BoundsTree(it, if (it in newBounds) newState else state) }
		} else {
			for (child in children) {
				if (child.bounds.doesIntersect(newBounds)) {
					child.set(newBounds.intersect(child.bounds), newState)
				}
			}
		}
	}

	fun count(predicate: (S) -> Boolean): Sint {
		return if (children.isEmpty()) {
			if (predicate(state)) bounds.weight() else 0.s
		} else {
			children.sumOf { it.count(predicate) }
		}
	}

	fun filter(function: (S) -> Boolean): List<P> {
		return if (children.isEmpty()) {
			if (function(state)) bounds.toList() else emptyList()
		} else {
			children.flatMap { it.filter(function) }
		}
	}

}