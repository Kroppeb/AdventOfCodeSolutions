package helpers

fun msa(): MutableSet<Any> = mutableSetOf()
fun <T> mst(): MutableSet<T> = mutableSetOf()

fun mmaa(): MutableMap<Any, Any> = mutableMapOf()
fun <T> mmat(): MutableMap<Any, T> = mutableMapOf()
fun <K, V> mmkv(): MutableMap<K, V> = mutableMapOf()

fun mla(): MutableList<Any> = mutableListOf()
fun <T> mlt(): MutableList<T> = mutableListOf()

fun aoi(size: Int): IntArray = IntArray(size)
fun aoi(size: Int, value:Int): IntArray = IntArray(size).apply { fill(value) }
fun aod(size: Int): DoubleArray = DoubleArray(size)
fun aod(size: Int, value:Double): DoubleArray = DoubleArray(size).apply { fill(value) }
fun aol(size: Int): LongArray = LongArray(size)
fun aol(size: Int, value:Long): LongArray = LongArray(size).apply { fill(value) }
