package com.didispace.xueyp.service

import org.springframework.stereotype.Service
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

@Service
class TestService {

    private val value: Int by DelegateUser()

    fun testByLazy() {
        val sample = LazySample()
        print("haha")
        println("lazy = ${sample.lazy}")
        println("lazy = ${sample.lazy}")
    }

    fun testObserve() {
        val user = User()
        println(user.name)
        user.name = "Carl"
        user.address = "abcd";
        println(user.address)
    }

    fun testValue() {
        println(value)
    }
}

class LazySample {
    val lazy: String by lazy {
        println("init!")
        "my lazy"
    }
}

class User {
    var name: String by Delegates.observable("oldName", { kProperty, old, new ->
        println("${kProperty.returnType}, $old -> $new")
    })

    var address: String by Delegates.vetoable("wan", { kProperty, oldValue, newValue ->
        println("oldValue：$oldValue | newValue：$newValue")
        newValue.contains("wang")
    })
}

class DelegateUser{
    var i = 0
    operator fun getValue(any: Any, property: KProperty<*>): Int = i++
}
