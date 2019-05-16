package com.didispace.xueyp.shutdown

import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Component

@Component
class TestDisposableBean : DisposableBean {
    override fun destroy() {
        println("bean is destoryed")
    }
}