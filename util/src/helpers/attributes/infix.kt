package helpers.attributes

import helpers.applyNTimes
import helpers.applyNTimesOr0
import helpers.context.CanBeEmptyTrait
import helpers.context.HasSizeTrait
import helpers.intersect
import helpers.union


context(CanBeEmptyTrait<T>)
@Infix_
infix fun <T> T.iss(att: empty): Boolean = this.isEmpty()

context(CanBeEmptyTrait<T>)
@Infix_
infix fun <T> T.isNot(att: empty): Boolean = !this.isEmpty()

context(CanBeEmptyTrait<T>)
@Infix_
infix fun <T> T.iss(att: notEmpty): Boolean = this.isNotEmpty()

context(CanBeEmptyTrait<T>)
@Infix_
infix fun <T> T.isNot(att: notEmpty): Boolean = !this.isNotEmpty()

context(HasSizeTrait<T>)
@Infix_
infix fun <T> T.take(att: size): Int = this.size()

@Infix_
infix fun <T, R> ((T) -> R).pass(item: T): R = this(item)

@Infix_
infix fun <T, R> (T.() -> R).use(item: T): R = this(item)

@Infix_
infix fun <T, R, S> (((T) -> R) -> S).use(item: T.()->R): S = this(item)

@Infix_
infix fun <T1, T2, R, S> (((T1, T2) -> R) -> S).with(item: (T1,T2)->R): S = this(item)

@Infix_
infix fun <T> Iterable<Iterable<T>>.union(each: each) = this.union()

@Infix_
infix fun <T> Iterable<Iterable<T>>.intersect(each: each) = this.intersect()


@Infix_
infix fun <T> Pair<Iterable<T>, Iterable<T>>.union(each: each) = this.union()

@Infix_
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
infix fun <T, R, V> Pair<Iterable<T>, Iterable<R>>.intersect(each: each): Set<V> where V : T, V : R =
	this.intersect()


@Infix_
infix fun <T> Iterable<T>.filter(distinct: distinct) = this.distinct()





@Infix_
infix fun <T, R : T> T.applyNTimes(n: Int): ((T) -> R) -> R = { action -> applyNTimes(n, action) }

@Infix_
infix fun <T> T.applyNTimesOr0(n: Int): ((T)->T) -> T = { action -> applyNTimesOr0(n, action) }

@Infix_
inline infix fun <T, R> Iterable<T>.scan(start: R): ((R, T) -> R) -> List<R> = { transform -> this.scan(start, transform)}