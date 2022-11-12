package collections

class UnionFind {
	private val parents = mutableMapOf<Any, Element>()

	private fun getRoot(a: Any): Element {
		val parent = parents.getOrPut(a) { Element(0, null) }
		return parent.getRoot()
	}

	fun areJoined(a: Any, b: Any) = getRoot(a) === getRoot(b)

	fun join(a: Any, b: Any): Boolean {
		if (a == b) return true
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

	class Element(var rank: Int, var parent: Element?) {
		fun getRoot(): Element = parent?.getRoot()?.also{parent = it} ?: this
	}
}