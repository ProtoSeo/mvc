package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("put-mapping")
    fun putMapping():String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT ],path =["request-mapping"])
    fun requestMapping():String{
        return "request -mapping - put mapping"
    }
    @PutMapping(path=["put-mapping/object"])    // 내용이 없으면 생성, 있으면 수정하는 PUT
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. Response
        return UserResponse().apply{
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~~~~~~~"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply{
                this.name= "A"
                this.age = 10
                this.email = "a@gmail.com"
                this.address = "Address"
                this.phoneNumber = "010-111123-1231"
            })
            userList.add(UserRequest().apply{
                this.name= "B"
                this.age = 20
                this.email = "B@gmail.com"
                this.address = "Bddress"
                this.phoneNumber = "0111-111123-1231"
            })

            this.userRequest = userList
        }
    }
}