class UnionFind {
	private val parents = mutableMapOf<Any,Element>()

	private fun getParent(a:Any): Element {
		val parent = parents.getOrPut(a){ Element(0,null) }
		return when(parent.parent){
			null -> parent
			else -> parent.getRoot().also { parents[a] = it }
		}
	}

	fun areJoined(a:Any, b:Any)= getParent(a) == getParent(b)

	fun join(a:Any, b:Any): Boolean {
		if(a == b)
			return true
		val pa = getParent(a)
		val pb = getParent(b)
		if(pa == pb)
			return true
		when{
			pa.rank < pb.rank -> pa.parent = pb
			pa.rank > pb.rank -> pb.parent = pa
			pa.rank == pb.rank -> {
				pa.parent = pb
				pb.rank += 1
			}
		}
		return false
	}

	class Element(var rank:Int, var parent:Element?){
		fun getRoot(): Element = parent?.also { parent=it.getRoot() }?:this
	}
}