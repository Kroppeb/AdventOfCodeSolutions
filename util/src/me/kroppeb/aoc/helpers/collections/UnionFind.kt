package me.kroppeb.aoc.helpers.collections

class UnionFind (){
	constructor(p0: Iterable<Any>): this(){
		p0.forEach(this::reg)
	}

	val parents = mutableMapOf<Any, Element>()
	val size: Int
		get() = getAllRoots().size

	fun reg(a: Any) {
		parents.computeIfAbsent(a) { Element(0, null, it) }
	}

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

fun <T:Any> Iterable<T>.uf() = UnionFind(this)