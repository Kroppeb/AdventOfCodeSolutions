package grids

interface IGraph<T> {
    interface Node<T>

    fun toMutable(): MutableIGraph<T>
    fun <R> map(mapping: (T) -> R): IGraph<R>
}

interface MutableIGraph<T>: IGraph<T>