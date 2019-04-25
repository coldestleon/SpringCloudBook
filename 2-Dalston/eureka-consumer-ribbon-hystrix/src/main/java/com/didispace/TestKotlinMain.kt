package com.didispace

import org.bouncycastle.asn1.x500.style.RFC4519Style.name
import sun.text.normalizer.UCharacter.getAge
import java.util.concurrent.ConcurrentHashMap

class TestKotlinMain

fun main(args: Array<String>) {
    args.forEach { System.out.println(it) }
    val ls: List<User> = listOf(
        User("xue", 10),
        User("xue", 13),
        User("yapeng", 10),
        User("yapeng", 13))
//    ls.filter(distinctByKey{ it }).groupBy { it.name }
////        .mapValues { (name, value) -> value.sortedByDescending { it.age } }
//        .forEach{(name, value) -> run { print(name)
//            print(value)
//        }}
//    print(DateUnitEnum.YEAR.toString())
    ls.filter { it == null }.forEach { print(it) }
}

private fun <T> distinctByKey(keyExtractor: (T) -> Any): (T) -> Boolean {
    val map = ConcurrentHashMap<Any, Boolean>()
    return { t ->
        map.putIfAbsent(keyExtractor(t), java.lang.Boolean.TRUE) == null
    }
}


enum class DateUnitEnum {
    DAY, WEEK, MONTH, YEAR, DECADE, FULL
}

data class User constructor(val name: String, val age: Int){
    constructor(para: String): this("you", getAge(para))
    companion object{
        fun getAge(para: String): Int = 11
    }
}