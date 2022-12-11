package itertools

fun <T>List<T>.prefixes() = indices.map { this.subList(0,it+1) }
fun <T>Sequence<T>.prefixes() = mapIndexed { index: Int, _: T -> this.take(index+1) }
fun <T>Iterable<T>.prefixes() = scan(emptyList<T>()){a,t-> a + listOf(t)}


fun <T>List<T>.suffixes() = indices.map { this.subList(it,this.size) }
fun <T>Sequence<T>.suffixes() = mapIndexed { index: Int, _: T -> this.drop(index) }


fun <T>Sequence<Sequence<T>>.flatten() = flatMap { it } + emptySequence<T>()

fun <T>Iterable<T>.compressWith(selector: Iterable<Boolean>) = zip(selector).filter(Pair<T,Boolean>::second)
fun <T>Sequence<T>.compressWith(selector: Sequence<Boolean>) = zip(selector).filter(Pair<T,Boolean>::second)

