package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {
    // http://localhost:8080/main
    @GetMapping("/main")
    fun main(): String {    //plain text로 가는것이 아님. 이것이 RestController가 붙는 것인지 Controller가 붙는지 구분
        println("init main")
        return "main.html"
    }
    // Rest api 처럼 작동된다
    @ResponseBody
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply{
            this.name = "seo"
        }
//        return "main.html"
    }
}