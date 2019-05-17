package com.didispace.xueyp.util

class Singleton private constructor(){
    var field: String = ""
    private constructor(field: String): this() {
        println("init field")
        this.field = field
    }

    init {
        println("init Singleton")
    }

    object SingletonHolder {
        init {
            println("init SingletonHolder")
        }
    }
    val student = object {
        val name = "xueyp"
        fun getAge() = 12
    }
    companion object {
        val instance = Singleton("you")
    }
}


fun main(args: Array<String>) {
    println("1")
    val ss = Singleton.SingletonHolder
    val st = Singleton.SingletonHolder
    println("2")
    val s1 = Singleton.instance
    println("3")
    val s2 = Singleton.instance
    println(ss.hashCode())
    println(st.hashCode())
    println(s1.hashCode())
    println(s2.hashCode())
}
