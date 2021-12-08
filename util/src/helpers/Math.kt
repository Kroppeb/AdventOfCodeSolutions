package helpers

fun gcd(a:Int, b:Int):Int = if(a == 0) b else gcd(b % a, a)
fun gcd(a:Long, b:Long):Long = if(a == 0L) b else gcd(b % a, a)

@Suppress("RemoveExplicitTypeArguments")
fun Iterable<Int>.gcd():Int = reduce<Int,Int>(::gcd)
@JvmName("longGcd")
@Suppress("RemoveExplicitTypeArguments")
fun Iterable<Long>.gcd():Long = reduce<Long, Long>(::gcd)

//fun gcd(vararg e:Int)

fun lcm(a:Int, b:Int):Int = a / gcd(a,b) * b
fun lcm(a:Long, b:Long):Long = a / gcd(a,b) * b

@Suppress("RemoveExplicitTypeArguments")
fun Iterable<Int>.lcm():Int = reduce<Int, Int>(::lcm)
@JvmName("longLcm")
@Suppress("RemoveExplicitTypeArguments")
fun Iterable<Long>.lcm():Long = reduce<Long, Long>(::lcm)

fun <T : Comparable<T>>Iterable<T>.min():T = minOrNull()!!
fun <T : Comparable<T>>Iterable<T>.max():T = maxOrNull()!!

fun <T : Comparable<T>>Array<T>.min():T = minOrNull()!!
fun <T : Comparable<T>>Array<T>.max():T = maxOrNull()!!

fun IntArray.min():Int = minOrNull()!!
fun IntArray.max():Int = maxOrNull()!!

fun LongArray.min():Long = minOrNull()!!
fun LongArray.max():Long = maxOrNull()!!

fun DoubleArray.min():Double = minOrNull()!!
fun DoubleArray.max():Double = maxOrNull()!!

inline fun <C, T : Comparable<T>>Iterable<C>.minBy(b:(C) -> T):C = minByOrNull(b)!!
inline fun <C, T : Comparable<T>>Iterable<C>.maxBy(b:(C) -> T):C = maxByOrNull(b)!!

inline fun <C, T : Comparable<T>>Array<C>.minBy(b:(C) -> T):C = minByOrNull(b)!!
inline fun <C, T : Comparable<T>>Array<C>.maxBy(b:(C) -> T):C = maxByOrNull(b)!!

inline fun <C : Comparable<C>>IntArray.minBy(b:(Int) -> C):Int = minByOrNull(b)!!
inline fun <C : Comparable<C>>IntArray.maxBy(b:(Int) -> C):Int = maxByOrNull(b)!!

inline fun <C : Comparable<C>>LongArray.minBy(b:(Long) -> C):Long = minByOrNull(b)!!
inline fun <C : Comparable<C>>LongArray.maxBy(b:(Long) -> C):Long = maxByOrNull(b)!!

inline fun <C : Comparable<C>>DoubleArray.minBy(b: (Double) -> C):Double = minByOrNull(b)!!
inline fun <C : Comparable<C>>DoubleArray.maxBy(b: (Double) -> C):Double = maxByOrNull(b)!!

@JvmName("minMaxVararg")
fun <T : Comparable<T>>minMax(vararg e:T):Pair<T, T> = e.min() to e.max()
fun <T : Comparable<T>>Iterable<T>.minMax():Pair<T, T> = min() to max()
fun <T : Comparable<T>>Array<T>.minMax():Pair<T, T> = min() to max()
fun IntArray.minMax():Pair<Int, Int> = min() to max()
fun LongArray.minMax():Pair<Long, Long> = min() to max()
fun DoubleArray.minMax():Pair<Double, Double> = min() to max()

@JvmName("minMaxIRIntsVararg")
fun minMaxIR(vararg e:Int):IntRange = e.min() .. e.max()
@JvmName("minMaxIRInts")
fun Iterable<Int>.minMaxIR():IntRange = min() .. max()
@JvmName("minMaxIRInts")
fun Array<Int>.minMaxIR():IntRange = min() .. max()
@JvmName("minMaxIRInts")
fun IntArray.minMaxIR():IntRange = min() .. max()

@JvmName("minMaxIRLongsVararg")
fun minMaxIR(vararg e:Long):LongRange = e.min() .. e.max()
@JvmName("minMaxIRLongs")
fun Iterable<Long>.minMaxIR():LongRange = min() .. max()
@JvmName("minMaxIRLongs")
fun Array<Long>.minMaxIR():LongRange = min() .. max()
@JvmName("minMaxIRLongs")
fun LongArray.minMaxIR():LongRange = min() .. max()




