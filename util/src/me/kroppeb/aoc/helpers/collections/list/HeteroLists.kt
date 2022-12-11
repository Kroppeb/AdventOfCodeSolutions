package me.kroppeb.aoc.helpers.collections.list

interface HetN

interface HetL1<out A> : HetN {
	val a: A
}

interface HetL2<out A, out B> : HetL1<A> {
	val b: B
}

interface HetL3<out A, out B, out C> : HetL2<A, B> {
	val c: C
}

interface HetL4<out A, out B, out C, out D> : HetL3<A, B, C> {
	val d: D
}

interface HetL5<out A, out B, out C, out D, out E> : HetL4<A, B, C, D> {
	val e: E
}

interface HetL6<out A, out B, out C, out D, out E, out F> : HetL5<A, B, C, D, E> {
	val f: F
}

interface HetL7<out A, out B, out C, out D, out E, out F, out G> : HetL6<A, B, C, D, E, F> {
	val g: G
}

interface HetL8<out A, out B, out C, out D, out E, out F, out G, out H> : HetL7<A, B, C, D, E, F, G> {
	val h: H
}

interface HetL9<out A, out B, out C, out D, out E, out F, out G, out H, out I> : HetL8<A, B, C, D, E, F, G, H> {
	val i: I
}

interface HetL10<out A, out B, out C, out D, out E, out F, out G, out H, out I, out J> :
	HetL9<A, B, C, D, E, F, G, H, I> {
	val j: J
}

interface HetR1<out Z> : HetN {
	val z: Z
}

interface HetR2<out Y, out Z> : HetR1<Z> {
	val y: Y
}

interface HetR3<out X, out Y, out Z> : HetR2<Y, Z> {
	val x: X
}

interface HetR4<out W, out X, out Y, out Z> : HetR3<X, Y, Z> {
	val w: W
}

interface HetR5<out V, out W, out X, out Y, out Z> : HetR4<W, X, Y, Z> {
	val v: V
}

interface HetR6<out U, out V, out W, out X, out Y, out Z> : HetR5<V, W, X, Y, Z> {
	val u: U
}

interface HetR7<out T, out U, out V, out W, out X, out Y, out Z> : HetR6<U, V, W, X, Y, Z> {
	val t: T
}

interface HetR8<out S, out T, out U, out V, out W, out X, out Y, out Z> : HetR7<T, U, V, W, X, Y, Z> {
	val s: S
}

interface HetR9<out R, out S, out T, out U, out V, out W, out X, out Y, out Z> : HetR8<S, T, U, V, W, X, Y, Z> {
	val r: R
}

interface HetR10<out Q, out R, out S, out T, out U, out V, out W, out X, out Y, out Z> :
	HetR9<R, S, T, U, V, W, X, Y, Z> {
	val q: Q
}

data class Het1<out A>(override val a: A) : HetL1<A>, HetR1<A> {
	override val z: A get() = a
}

data class Het2<out A, out B>(override val a: A, override val b: B) : HetL2<A, B>, HetR2<A, B> {
	override val y: A get() = a
	override val z: B get() = b
}

data class Het3<out A, out B, out C>(override val a: A, override val b: B, override val c: C) : HetL3<A, B, C>,
	HetR3<A, B, C> {
	override val x: A get() = a
	override val y: B get() = b
	override val z: C get() = c
}

data class Het4<out A, out B, out C, out D>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D
) :
	HetL4<A, B, C, D>, HetR4<A, B, C, D> {
	override val w: A get() = a
	override val x: B get() = b
	override val y: C get() = c
	override val z: D get() = d
}

data class Het5<out A, out B, out C, out D, out E>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D,
	override val e: E
) : HetL5<A, B, C, D, E>, HetR5<A, B, C, D, E> {
	override val v: A get() = a
	override val w: B get() = b
	override val x: C get() = c
	override val y: D get() = d
	override val z: E get() = e
}

data class Het6<out A, out B, out C, out D, out E, out F>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D,
	override val e: E,
	override val f: F
) : HetL6<A, B, C, D, E, F>, HetR6<A, B, C, D, E, F> {
	override val u: A get() = a
	override val v: B get() = b
	override val w: C get() = c
	override val x: D get() = d
	override val y: E get() = e
	override val z: F get() = f
}

data class Het7<out A, out B, out C, out D, out E, out F, out G>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D,
	override val e: E,
	override val f: F,
	override val g: G
) : HetL7<A, B, C, D, E, F, G>, HetR7<A, B, C, D, E, F, G> {
	override val t: A get() = a
	override val u: B get() = b
	override val v: C get() = c
	override val w: D get() = d
	override val x: E get() = e
	override val y: F get() = f
	override val z: G get() = g
}

data class Het8<out A, out B, out C, out D, out E, out F, out G, out H>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D,
	override val e: E,
	override val f: F,
	override val g: G,
	override val h: H
) : HetL8<A, B, C, D, E, F, G, H>, HetR8<A, B, C, D, E, F, G, H> {
	override val s: A get() = a
	override val t: B get() = b
	override val u: C get() = c
	override val v: D get() = d
	override val w: E get() = e
	override val x: F get() = f
	override val y: G get() = g
	override val z: H get() = h
}

data class Het9<out A, out B, out C, out D, out E, out F, out G, out H, out I>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D,
	override val e: E,
	override val f: F,
	override val g: G,
	override val h: H,
	override val i: I
) : HetL9<A, B, C, D, E, F, G, H, I>, HetR9<A, B, C, D, E, F, G, H, I> {
	override val r: A get() = a
	override val s: B get() = b
	override val t: C get() = c
	override val u: D get() = d
	override val v: E get() = e
	override val w: F get() = f
	override val x: G get() = g
	override val y: H get() = h
	override val z: I get() = i
}

data class Het10<out A, out B, out C, out D, out E, out F, out G, out H, out I, out J>(
	override val a: A,
	override val b: B,
	override val c: C,
	override val d: D,
	override val e: E,
	override val f: F,
	override val g: G,
	override val h: H,
	override val i: I,
	override val j: J
) : HetL10<A, B, C, D, E, F, G, H, I, J>, HetR10<A, B, C, D, E, F, G, H, I, J> {
	override val q: A get() = a
	override val r: B get() = b
	override val s: C get() = c
	override val t: D get() = d
	override val u: E get() = e
	override val v: F get() = f
	override val w: G get() = g
	override val x: H get() = h
	override val y: I get() = i
	override val z: J get() = j
}

fun <A> HetL1<A>.first() = a
fun <A, B> HetL2<A, B>.pair() = a to b

fun <A, B> HetL2<A, B>.take2() = Het2(a, b)
fun <A, B, C> HetL3<A, B, C>.take3() = Het3(a, b, c)
fun <A, B, C, D> HetL4<A, B, C, D>.take4() = Het4(a, b, c, d)
fun <A, B, C, D, E> HetL5<A, B, C, D, E>.take5() = Het5(a, b, c, d, e)
fun <A, B, C, D, E, F> HetL6<A, B, C, D, E, F>.take6() = Het6(a, b, c, d, e, f)
fun <A, B, C, D, E, F, G> HetL7<A, B, C, D, E, F, G>.take7() = Het7(a, b, c, d, e, f, g)
fun <A, B, C, D, E, F, G, H> HetL8<A, B, C, D, E, F, G, H>.take8() = Het8(a, b, c, d, e, f, g, h)
fun <A, B, C, D, E, F, G, H, I> HetL9<A, B, C, D, E, F, G, H, I>.take9() = Het9(a, b, c, d, e, f, g, h, i)
fun <A, B, C, D, E, F, G, H, I, J> HetL10<A, B, C, D, E, F, G, H, I, J>.take10() = Het10(a, b, c, d, e, f, g, h, i, j)

fun <Z> HetR1<Z>.last() = z

fun <Y, Z> HetR2<Y, Z>.takeLast2() = Het2(y, z)
fun <X, Y, Z> HetR3<X, Y, Z>.takeLast3() = Het3(x, y, z)
fun <W, X, Y, Z> HetR4<W, X, Y, Z>.takeLast4() = Het4(w, x, y, z)
fun <V, W, X, Y, Z> HetR5<V, W, X, Y, Z>.takeLast5() = Het5(v, w, x, y, z)
fun <U, V, W, X, Y, Z> HetR6<U, V, W, X, Y, Z>.takeLast6() = Het6(u, v, w, x, y, z)
fun <T, U, V, W, X, Y, Z> HetR7<T, U, V, W, X, Y, Z>.takeLast7() = Het7(t, u, v, w, x, y, z)
fun <S, T, U, V, W, X, Y, Z> HetR8<S, T, U, V, W, X, Y, Z>.takeLast8() = Het8(s, t, u, v, w, x, y, z)
fun <R, S, T, U, V, W, X, Y, Z> HetR9<R, S, T, U, V, W, X, Y, Z>.takeLast9() = Het9(r, s, t, u, v, w, x, y, z)
fun <Q, R, S, T, U, V, W, X, Y, Z> HetR10<Q, R, S, T, U, V, W, X, Y, Z>.takeLast10() =
	Het10(q, r, s, t, u, v, w, x, y, z)

infix fun <A, B> A.toH(b: B) = Het2(this, b)
infix fun <A, B, C> Het2<A, B>.toH(c: C) = Het3(a, b, c)
infix fun <A, B, C, D> Het3<A, B, C>.toH(d: D) = Het4(a, b, c, d)
infix fun <A, B, C, D, E> Het4<A, B, C, D>.toH(e: E) = Het5(a, b, c, d, e)
infix fun <A, B, C, D, E, F> Het5<A, B, C, D, E>.toH(f: F) = Het6(a, b, c, d, e, f)
infix fun <A, B, C, D, E, F, G> Het6<A, B, C, D, E, F>.toH(g: G) = Het7(a, b, c, d, e, f, g)
infix fun <A, B, C, D, E, F, G, H> Het7<A, B, C, D, E, F, G>.toH(h: H) = Het8(a, b, c, d, e, f, g, h)
infix fun <A, B, C, D, E, F, G, H, I> Het8<A, B, C, D, E, F, G, H>.toH(i: I) = Het9(a, b, c, d, e, f, g, h, i)
infix fun <A, B, C, D, E, F, G, H, I, J> Het9<A, B, C, D, E, F, G, H, I>.toH(j: J) = Het10(a, b, c, d, e, f, g, h, i, j)

fun <A, B> Het2<Iterable<A>, Iterable<B>>.zipped() = a.zip(b) { a, b -> a toH b }
fun <A, B, C> Het3<Iterable<A>, Iterable<B>, Iterable<C>>.zipped() =
	take2().zipped().zip(c) { (a, b), c -> a toH b toH c }

fun <A, B, C, D> Het4<Iterable<A>, Iterable<B>, Iterable<C>, Iterable<D>>.zipped() =
	take3().zipped().zip(d) { (a, b, c), d -> a toH b toH c toH d }

fun <A, B, C, D, E> Het5<Iterable<A>, Iterable<B>, Iterable<C>, Iterable<D>, Iterable<E>>.zipped() =
	take4().zipped().zip(e) { (a, b, c, d), e -> a toH b toH c toH d toH e }

fun <A, B, C, D, E, F> Het6<Iterable<A>, Iterable<B>, Iterable<C>, Iterable<D>, Iterable<E>, Iterable<F>>.zipped() =
	take5().zipped().zip(e) { (a, b, c, d, e), f -> a toH b toH c toH d toH e toH f }

fun <A, B, C, D, E, F, G> Het7<
		Iterable<A>,
		Iterable<B>,
		Iterable<C>,
		Iterable<D>,
		Iterable<E>,
		Iterable<F>,
		Iterable<G>>.zipped() =
	take6().zipped().zip(e) { (a, b, c, d, e, f), h -> a toH b toH c toH d toH e toH f toH h }

fun <A, B, C, D, E, F, G, H> Het8<
		Iterable<A>,
		Iterable<B>,
		Iterable<C>,
		Iterable<D>,
		Iterable<E>,
		Iterable<F>,
		Iterable<G>,
		Iterable<H>>.zipped() =
	take7().zipped().zip(e) { (a, b, c, d, e, f, g), h -> a toH b toH c toH d toH e toH f toH g toH h }

fun <A, B, C, D, E, F, G, H, I> Het9<
		Iterable<A>,
		Iterable<B>,
		Iterable<C>,
		Iterable<D>,
		Iterable<E>,
		Iterable<F>,
		Iterable<G>,
		Iterable<H>,
		Iterable<I>>.zipped() =
	take8().zipped().zip(e) { (a, b, c, d, e, f, g, h), i -> a toH b toH c toH d toH e toH f toH g toH h toH i }

fun <A, B, C, D, E, F, G, H, I, J> Het10<
		Iterable<A>,
		Iterable<B>,
		Iterable<C>,
		Iterable<D>,
		Iterable<E>,
		Iterable<F>,
		Iterable<G>,
		Iterable<H>,
		Iterable<I>,
		Iterable<J>>.zipped() = take9().zipped()
	.zip(e) { (a, b, c, d, e, f, g, h, i), j -> a toH b toH c toH d toH e toH f toH g toH h toH i toH j }

fun <A, B> Iterable<A>.zipH(b: Iterable<B>) = (this toH b).zipped()
fun <A, B, C> Iterable<A>.zipH(b: Iterable<B>, c: Iterable<C>) = (this toH b toH c).zipped()
fun <A, B, C, D> Iterable<A>.zipH(b: Iterable<B>, c: Iterable<C>, d: Iterable<D>) = (this toH b toH c toH d).zipped()
fun <A, B, C, D, E> Iterable<A>.zipH(b: Iterable<B>, c: Iterable<C>, d: Iterable<D>, e: Iterable<E>) =
	(this toH b toH c toH d toH e).zipped()

fun <A, B, C, D, E, F> Iterable<A>.zipH(
	b: Iterable<B>,
	c: Iterable<C>,
	d: Iterable<D>,
	e: Iterable<E>,
	f: Iterable<F>
) = (this toH b toH c toH d toH e toH f).zipped()

fun <A, B, C, D, E, F, G> Iterable<A>.zipH(
	b: Iterable<B>,
	c: Iterable<C>,
	d: Iterable<D>,
	e: Iterable<E>,
	f: Iterable<F>,
	g: Iterable<G>
) = (this toH b toH c toH d toH e toH f toH g).zipped()

fun <A, B, C, D, E, F, G, H> Iterable<A>.zipH(
	b: Iterable<B>,
	c: Iterable<C>,
	d: Iterable<D>,
	e: Iterable<E>,
	f: Iterable<F>,
	g: Iterable<G>,
	h: Iterable<H>
) = (this toH b toH c toH d toH e toH f toH g toH h).zipped()

fun <A, B, C, D, E, F, G, H, I> Iterable<A>.zipH(
	b: Iterable<B>,
	c: Iterable<C>,
	d: Iterable<D>,
	e: Iterable<E>,
	f: Iterable<F>,
	g: Iterable<G>,
	h: Iterable<H>,
	i: Iterable<I>
) = (this toH b toH c toH d toH e toH f toH g toH h toH i).zipped()


fun <A, B, C, D, E, F, G, H, I, J> Iterable<A>.zipH(
	b: Iterable<B>,
	c: Iterable<C>,
	d: Iterable<D>,
	e: Iterable<E>,
	f: Iterable<F>,
	g: Iterable<G>,
	h: Iterable<H>,
	i: Iterable<I>,
	j: Iterable<J>
) = (this toH b toH c toH d toH e toH f toH g toH h toH i toH j).zipped()