package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    //@ Valid 해당 빈에 대해서만
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult:BindingResult): ResponseEntity<String> {
        if(bindingResult.hasErrors()){
            // 에러를 가지고 있다면 제대로 처리 x, 500 error 던지자
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = field.defaultMessage
                msg.append("${field.field} : $message")


            }
            return ResponseEntity.badRequest().body(msg.toString())
        }
    /*
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
        }.apply {

        }*/
        return ResponseEntity.ok("")
    }
}