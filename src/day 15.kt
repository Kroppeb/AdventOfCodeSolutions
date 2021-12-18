@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d18

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*

val xxxxx = Clock(6, 3);

/*

*/


fun tryExplode(d:MutableList<Any>, depth:Int, explode: () -> Unit): Pair<Int?,Int?>?{
    val(l,r) = d
    if(l is MutableList<*>){
        val let = tryExplode(l as MutableList<Any>, depth + 1){d[0] = 0}

        if(let != null){
            var (u,v) = let
            if(v != null){
                var preR = d
                var rr = r
                while(rr !is Int){
                    preR = rr as MutableList<Any>
                    rr = preR[0]
                }

                if(preR == d){
                    d[1] = rr + v
                } else {
                    preR[0] = rr + v
                }
                return u to null
            }
            return let
        }
    }

    if(r is MutableList<*>) {
        val let = tryExplode(r as MutableList<Any>,depth+1){d[1] = 0}

        if(let != null){
            var (u,v) = let
            if(u != null){
                var preL = d
                var ll = l
                while(ll !is Int){
                    preL = ll as MutableList<Any>
                    ll = preL[1]
                }

                if(preL == d){
                    d[0] = ll + u
                } else {
                    preL[1] = ll + u
                }
                return null to v
            }
            return let
        }
    }

    if(l is Int && r is Int && depth >= 4){
        explode()
        return l to r
    }

    return null;
}

fun fullReduce(it: MutableList<Any>){
    while(true){
        //it.log()
        if(tryExplode(it as MutableList<Any>, 0){} != null)
            continue
        if(trySplit(it as MutableList<Any>))
            continue
        break;
    }
    return;
}

private fun part1() {
    val res = getData().fold(null as MutableList<Any>?) {a,b ->
        val c = b as MutableList<Any>
        a?:return@fold c
        val d = mutableListOf<Any>(a,b)
        fullReduce(d)
        d
    }!!
    magnitude(res).log()
}

fun magnitude(res: MutableList<Any>): Long {
    val (l , r) = res
    var c = 0L
    if(l is MutableList<*>){
        c += magnitude(l as MutableList<Any>) * 3
    } else if(l is Int){
        c += l.toLong() * 3
    }

    if(r is MutableList<*>){
        c += magnitude(r as MutableList<Any>) * 2
    } else if(r is Int){
        c += r.toLong() * 2
    }

    return c
}

fun trySplit(mutableList: MutableList<Any>): Boolean {
    val (l, r) = mutableList
    if(l is MutableList<*>) {
        if(trySplit(l as MutableList<Any>))
            return true
    }
    if(l is Int && l > 9){
        mutableList[0] = mutableListOf( l / 2, (l + 1) / 2)
        return true
    }

    if(r is MutableList<*>) {
        if(trySplit(r as MutableList<Any>))
            return true
    }

    if(r is Int && r > 9){
        mutableList[1] = mutableListOf( r / 2, (r + 1) / 2)
        return true
    }

    return false
}


fun main() {
    println("Day 18: ")
    part2()
}

fun clone(t:Any):Any{
    return when(t){
        is List<*> -> mutableListOf(clone(t[0]!!), clone(t[1]!!))
        is Int -> t
        else -> throw IllegalArgumentException("Unknown type")
    }
}

private fun part2() {
    val res = getData().orderedPairWise().map { (a,b) ->
        val d = mutableListOf<Any>(clone(a), clone(b))
        fullReduce(d)
        d
    }.map{magnitude(it)}.max().log()

}

fun <T> T.log(): T = also { println(this) }