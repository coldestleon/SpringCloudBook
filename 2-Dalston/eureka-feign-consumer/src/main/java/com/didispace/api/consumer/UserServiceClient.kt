package com.didispace.api.consumer

import com.didispace.api.UserService
import org.springframework.cloud.netflix.feign.FeignClient

@FeignClient("eureka-client")
interface UserServiceClient : UserService