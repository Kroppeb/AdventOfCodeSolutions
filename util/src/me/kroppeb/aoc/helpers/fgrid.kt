package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.point.PointI

fun PointI.asPair(): Pair<Int, Int> = Pair(x, y)
fun Pair<Int, Int>.asPoint(): PointI = PointI(first, second)

fun Pair<Int, Int>.getQuadNeighbours(): List<Pair<Int, Int>> = asPoint().getQuadNeighbours().map { it.asPair() }
fun Pair<Int, Int>.getOctNeighbours(): List<Pair<Int, Int>> = asPoint().getOctNeighbours().map { it.asPair() }
fun Pair<Int, Int>.getDiagonalNeighbours(): List<Pair<Int, Int>> = asPoint().getDiagonalNeighbours().map { it.asPair() }

operator fun <T> List<List<T>>.get(first: Int, second: Int): T = this[first][second]
operator fun <T> List<MutableList<T>>.set(first: Int, second: Int, value: T) {
    this[first][second] = value
}

operator fun <T> List<List<T>>.get(index: Pair<Int, Int>): T = this[index.first, index.second]
operator fun <T> List<MutableList<T>>.set(index: Pair<Int, Int>, value: T) {
    this[index.first, index.second] = value
}

fun <T> List<List<T>>.biIndexed() =
        this.mapIndexed { i, row -> row.mapIndexed { j, t -> Pair(i, j) to t } }.flatten()
fun <T, R> List<List<T>>.map2Indexed(map: (Pair<Int, Int>, T) -> R) =
        this.mapIndexed { i: Int, row: List<T> ->
            row.mapIndexed { j: Int, t: T ->
                map(i to j, t)
            }
        }

