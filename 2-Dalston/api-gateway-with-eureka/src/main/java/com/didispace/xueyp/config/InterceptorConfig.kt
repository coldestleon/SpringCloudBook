package com.didispace.xueyp.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
open class InterceptorConfig : WebMvcConfigurerAdapter() {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(MyInterceptor1()).addPathPatterns("/**")
        registry.addInterceptor(MyInterceptor2()).addPathPatterns("/**")
    }
}

class MyInterceptor1 : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println("MyInterceptor1::preHandle")
        return true
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
        e: Exception?) {
        println("MyInterceptor1::afterCompletion")
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
        modelAndView: ModelAndView?) {
        println("MyInterceptor1::postHandle")
    }
}

class MyInterceptor2 : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println("MyInterceptor2::preHandle")
        return true
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
        e: Exception?) {
        println("MyInterceptor2::afterCompletion")
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
        modelAndView: ModelAndView?) {
        println("MyInterceptor2::postHandle")
    }
}