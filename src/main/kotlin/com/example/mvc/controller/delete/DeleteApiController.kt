package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated  //@Validated javax.validation 을 통해서 검증을 사용 가능하게 하는..
class DeleteApiController {
    // 1. path variable
    // 2. request param
    @DeleteMapping(path=["delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(@PathVariable(name="name")
                          @Size(min=2,max=5, message = "name의 길이는 2에서 5사여야 합니다.") //최소 2자리에서부터 최대 5자리까지 가능
                          @NotNull
                          _name:String,

                          @NotNull(message = "age 값이 누락되었습니다.")
                          @Min(value=20,message="age는 20보다 값이 작습니다.")
                          @PathVariable(value="age") _age:Int): String {
        println("$_name $_age")
        return "$_name $_age"
    }

    @DeleteMapping(path=["/delete-mapping"])
    fun deleteMapping(
        @RequestParam(value = "name")_name:String,

        @NotNull(message = "age 값이 누락되었습니다.")
        @Min(value=20,message="age는 20보다 값이 작습니다.")
        @RequestParam(name="age") _age:Int
    ): String {
        println("$_name $_age")
        return "$_name $_age"
    }
}