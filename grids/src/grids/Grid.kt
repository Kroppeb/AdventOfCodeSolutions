package grids

import grid.Clock
import helpers.Bounds
import helpers.Point
import helpers.map2
import helpers.toB

public class Grid<T>(entries: List<List<T>>) : IGraph<T> {
    val entries: List<List<Grid<T>.Node>>
    val bounds: Bounds

    init {
        this.entries = entries.zip(Clock.firstRange(entries.size))
                .map { (lst, first) ->
                    lst.zip(Clock.secondRange(lst.size))
                            .map { (t, second) ->
                                Node(Clock.fromIndices(first, second), t)
                            }
                }
        this.bounds = this.entries.first().first().point toB this.entries.last().last().point
    }

    override fun toMutable(): MutableGrid<T> {
        return MutableGrid(this.entries.map2 { it.data })
    }

    override fun <R> map(mapping: (T) -> R): IGraph<R> {
        return Grid(this.entries.map2{mapping(it.data)})
    }

    public inner class Node(
            val point: Point,
            val data: T
    ) : IGraph.Node<T>{
        val grid
            get() = this@Grid
    }
}

public class MutableGrid<T>(entries: List<List<T>>) : MutableIGraph<T> {
    val entries: List<MutableList<MutableGrid<T>.Node>>
    val bounds: Bounds

    init {
        this.entries = entries.zip(Clock.firstRange(entries.size))
                .map { (lst, first) ->
                    lst.zip(Clock.secondRange(lst.size))
                            .map { (t, second) ->
                                Node(Clock.fromIndices(first, second), t)
                            }.toMutableList()
                }
        this.bounds = this.entries.first().first().point toB this.entries.last().last().point
    }


    override fun toMutable(): MutableGrid<T> {
        return MutableGrid(this.entries.map2 { it.data })
    }

    override fun <R> map(mapping: (T) -> R): IGraph<R> {
        return Grid(this.entries.map2{mapping(it.data)})
    }

    public inner class Node(
            val point: Point,
            var data: T
    ) : IGraph.Node<T> {
        val grid
            get() = this@MutableGrid
    }
}