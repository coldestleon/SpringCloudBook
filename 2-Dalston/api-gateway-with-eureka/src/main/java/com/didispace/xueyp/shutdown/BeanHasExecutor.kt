package com.didispace.xueyp.shutdown

import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Component
class BeanHasExecutor : ExecutorConfigurationSupport() {

    private var threadE: ThreadPoolExecutor? = null
    override  fun initializeExecutor(
        threadFactory: ThreadFactory, rejectedExecutionHandler: RejectedExecutionHandler): ExecutorService {
        this.setAwaitTerminationSeconds(1)
        this.setBeanName("BeanHasExecutor")
        this.setThreadGroupName("BeanHasExecutor")
        val temp = ThreadPoolExecutor(4, 4,
            1L, TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(10),
            threadFactory, rejectedExecutionHandler)
        this.threadE = temp
        return temp
    }

    fun submitTask(time: Long) {
        this.threadE!!.submit({
            println("run by " + Thread.currentThread().name)
            Thread.sleep(time*1000)
            println("sleep end of " + Thread.currentThread().name)
        })
    }
}