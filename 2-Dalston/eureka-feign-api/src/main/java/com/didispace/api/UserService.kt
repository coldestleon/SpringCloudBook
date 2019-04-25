package com.didispace.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

interface UserService{
    @GetMapping("/user/{id}")
    fun getBookById(@PathVariable("id") id: String): String
}