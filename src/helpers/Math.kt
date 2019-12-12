package helpers

fun gcd(a:Int, b:Int):Int = if(a == 0) b else gcd(b % a, a)
fun lcm(a:Int, b:Int):Int = a / gcd(a,b) * b

