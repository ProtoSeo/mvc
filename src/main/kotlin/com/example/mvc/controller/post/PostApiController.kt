package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {   // post 요청은 body 에 내용을 담을 수 있다.

    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    //예전 방식
    @RequestMapping(method = [RequestMethod.POST],path=["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // object mapper
    // json -> object
    // object -> json
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        // json -> object
        println(userRequest)

        // object -> json
        return userRequest
    }
}