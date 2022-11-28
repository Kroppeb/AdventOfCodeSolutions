package collections

class UnionFind {
	val parents = mutableMapOf<Any, Element>()

	fun getRoot(a: Any): Element {
		val parent = parents.getOrPut(a) { Element(0, null, a) }
		return parent.getRoot()
	}

	fun areJoined(a: Any, b: Any) = getRoot(a) === getRoot(b)

	fun join(a: Any, b: Any): Boolean {
		val pa = getRoot(a)
		val pb = getRoot(b)
		if (pa === pb) return true
		when {
			pa.rank < pb.rank -> pa.parent = pb
			pa.rank > pb.rank -> pb.parent = pa
			else -> {
				pa.parent = pb
				pb.rank += 1
			}
		}
		return false
	}

	fun getAllRoots() = this.parents.entries.filter{it.value.parent == null}.map{it.key}

	fun getAllGroups() = this.parents.keys.groupBy { this.getRoot(it) }

	class Element(var rank: Int, var parent: Element?, var item:Any) {
		fun getRoot(): Element = parent?.getRoot()?.also{parent = it} ?: this
	}
}