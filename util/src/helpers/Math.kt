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


