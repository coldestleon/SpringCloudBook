package com.didispace.xueyp.controller

import com.didispace.xueyp.service.MyExecutorService
import com.didispace.xueyp.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController @Autowired constructor(
    private val testService: TestService,
    private val executor: MyExecutorService
) {
    @GetMapping(value = ["bylazy"])
    fun testByLazy() {
        testService.testByLazy()
    }

    @GetMapping(value = ["observe"])
    fun testObserve() {
        testService.testObserve()
    }

    @GetMapping(value = ["value"])
    fun testValue() {
        testService.testValue()
    }

    @GetMapping(value = ["execute"])
    fun executor(@RequestParam("time") time: Int) {
        executor.submitTask(time)
    }
}