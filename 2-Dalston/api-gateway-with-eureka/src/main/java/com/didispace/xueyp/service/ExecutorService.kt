package com.didispace.xueyp.service

import com.didispace.xueyp.shutdown.BeanHasExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MyExecutorService @Autowired constructor(
    private val executor: BeanHasExecutor
) {
    fun submitTask(time: Int) {
        executor.submitTask(time.toLong())
    }
}