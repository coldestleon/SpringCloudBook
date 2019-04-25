package com.didispace.api.consumer

import feign.Feign
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController @Autowired constructor(
    private val userService: UserServiceClient
) {
    @GetMapping("/user/{id}")
    fun getBookById(@PathVariable("id") id: String): String{
        val user = userService.getBookById(id)
        print(user)
        return user
    }
}