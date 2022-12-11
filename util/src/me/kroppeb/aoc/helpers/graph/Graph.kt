package me.kroppeb.aoc.helpers.graph
/*
interface VertexPollingGraph<in V>{
	fun adjacent(a:V, b:V):Boolean
	fun isVertexInGraph(v:V):Boolean
}

interface EdgePollingGraph<in E>{
	fun isEdgeInGraph(e:E): Boolean
}

interface MutatingEdgeGraph<in V, in E>: VertexPollingGraph<V>, EdgePollingGraph<E>, MutatingGraph<V>{
	fun addEdge(a:V, b:V, e:E) : Boolean
	fun removeEdge(e:E) : Boolean
}

interface WeightedPollingGraph<in V> : VertexPollingGraph<V>, EdgePollingGraph<WeightedPollingGraph.WeightedEdge>{
	interface WeightedEdge{
		val weight:Int
	}
}

interface WeightedGraph<V> : WeightedPollingGraph<V>, EdgeGraph<V, WeightedPollingGraph.WeightedEdge>



interface MutableWeightedGraph<V> : WeightedGraph<V>, MutableEdgeGraph<V, MutableWeightedGraph.MutableWeightedEdge<V>> {
	interface MutableWeightedEdge<V>:WeightedGraph.WeightedEdge<V>{
		var weight:Int
	}
}

interface MutatingGraph<in V>: VertexPollingGraph<V>{
	fun addEdge(a: V, b:V):Boolean
	fun removeEdge(a:V, b:V):Boolean
}

interface MutableGraph<V>:Graph<V>, MutatingGraph<V>{
}

interface VertexToEdgeGraph<in V, out E>: VertexPollingGraph<V>{
	fun getEdge(a:V, b:V) : E = getEdges(a,b).single()
	fun getEdges(a:V, b:V): List<E>
}

interface EdgeToVertexGraph<out V, in E>:EdgePollingGraph<E>{
	fun getEdgeEndpoints(e:E): Pair<V,V>
}


interface Graph<V>:VertexPollingGraph<V>{
	fun neighbors(a:V):Iterable<V>
}

interface EdgeGraph<V, E> : Graph<V>, VertexToEdgeGraph<V, E>, EdgeToVertexGraph<V, E>

interface MutableEdgeGraph<V, E> : EdgeGraph<V, E>, MutableGraph<V>, MutatingEdgeGraph<V,E>



interface DirectedGraph<V> : Graph<V>{
	val sinks:List<V>
	fun getChildren(v:V) : Iterable<V>
}

interface BiDirectedGraph<V> : DirectedGraph<V>{
	val sources:List<V>
	fun getParents(v:V) : Iterable<V>
}

interface DirectedAcyclicGraph<V> : DirectedGraph<V>{
	fun getLeavesOf(v:V) : Iterable<V>
	fun getDescendants(v:V) : Iterable<V>
	fun getLeaves() : Iterable<V> = sinks
	fun getCommonLeaves(a:V, b:V) : Iterable<V> = getLeavesOf(a).intersect(getLeavesOf(b))
	fun getCommonDescendants(a:V, b:V) : Iterable<V> = getDescendants(a).intersect(getDescendants(b))
	fun isAncestor(parent:V, child:V) : Boolean
}

interface BiDirectedAcyclicGraph<V> : DirectedAcyclicGraph<V>, BiDirectedGraph<V>{
	fun getRootsOf(v:V) : Iterable<V>
	fun getAncestors(v:V) : Iterable<V>
	fun getRoots() : Iterable<V> = sources
	fun getCommonRoots(a:V, b:V) : Iterable<V> = getRootsOf(a).intersect(getRootsOf(b))
	fun getCommonAncestors(a:V, b:V) : Iterable<V> = getAncestors(a).intersect(getAncestors(b))
}

interface TreeGraph<V> : BiDirectedAcyclicGraph<V>{
	val root:V
	override val sources: List<V> get() = listOf(root)
	override fun getRootsOf(v: V): Iterable<V> = listOf(root)
	fun firstCommonAncestor(a:V, b:V)
}

interface UnDirectedGraph<V> : Graph<V>



/* types of graphs (java T point)
	null graph => no edges, == List
	trivial graph => 1 vertex (ubclass of nullgraph)
	simple graph => undirected, no parallel and no self loops
	undirected
	directed
	complete graph => each vertex is connected pairwise
	connected graph => every node is connected to every other node
	disconnected graph => can be written as the union of multiple graphs
	regular graph => all edgecounts in each vertex is constant
	cycle graph => n nodes in a cycle
	cyclic graph => has a cycle
	acyclic graph => no cycle
	bipartite => vertex set can be split in 2 groups so that in each group there are no inner connections
	complete bipartite => the edges are the carthesian product of the 2 groups
	star graph => at most 1 edge can have a degree > 1
	weighed graph => edges have weights
	multigraph => parallel edges and weights
	planar => can be embedded in the plane without edges crossing
	non-planar => can't be embedded ...

	tutorialspoint
	wheel graph => like a star but the endpoints make a cycle
	complement of a graph => e is an edge if e is not in the original graph

	web.cecs.pdx.edu
	vertex labeled graph => each vertex has a certain label eg color
	edge labeled graph => each vertex has a certain label eg color
	directed acyclic graphs

	storage:
	sets: Vertex set+ Edge set: quick to look up adjacency but slow for enumeration
	Adjacency list
		Mapping of vertex to list/map of vertex
		* Additionally a way to get the vertices from an edge in O(1) can be provided
	Adjacency matrix
		A two-dimensional matrix, in which the rows represent source vertices and columns represent destination vertices. Data on edges and vertices must be stored externally. Only the cost for one edge can be stored between each pair of vertices.
	Incidence matrix
		A two-dimensional Boolean matrix, in which the rows represent the vertices and columns represent the edges. The entries indicate whether the vertex at a row is incident to the edge at a column.



 */