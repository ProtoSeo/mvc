package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController     // REST API Controller 동작
@RequestMapping("/api")     // http://localhost:8080/api
class GetApiController {

    @GetMapping("/hello")   // GET http://localhost:8080/api/hello, path 속성을 줄 수도 있다.
    fun hello():String {
        return "hello kotlin"   // 문자열 반환
    }

    // 옛날에 사용하던 방식
    // @RequestMapping("request-mapping") //이렇게 하면 http method 에 상관없이 동작하게됨
    @RequestMapping(method = [RequestMethod.GET],path = ["/request-mapping"]) //따라서 method를 설정하자
    //path 는 배열로 작성함. 여러가지 주소가 들어갈 수 있다.
    fun requestMapping(): String {
        return "request-mapping"
    }

    // path variable
    @GetMapping("/get-mapping/path-variable/{name}/{age}")    // GET http://localhost:8080/api/get-mapping/path-variable/steve
    fun pathVariable(@PathVariable name:String,@PathVariable age:Int): String {
        println("$name , $age")
        return "$name $age"
    }

    // 변수이름을 같게 맞출수 없을때, value 속성과, name 속성을 통해서 맞추어 줄 수 있다.
    @GetMapping("/get-mapping/path-variable2/{name}/{age}")    // GET http://localhost:8080/api/get-mapping/path-variable/steve
    fun pathVariable2(@PathVariable(value="name") _name:String,@PathVariable(name="age") age:Int): String {
        var name = "kotlin"
        println("$_name , $age")
        return "$_name $age"
    }

    // Query parameter ==> https://localhost:8080/api/page?key=value&key=value
    @GetMapping("/get-mapping/query-param") // ?name=steve&age=20
    fun queryParam(
        @RequestParam(name="name") name:String,
        @RequestParam(value = "age") age:Int
    ): String {
        println("$name , $age")

        return "$name $age"
    }

    //name, age, address, email 등등 이것을 한번에 처리하는 방법 => 객체로 받아서 처리

    //uri 대문자 사용 x : phoneNumber -> phone-number,phonenumber 이런식으로 작성할 것임
    // 하지만 kotlin 에서는 변수명에 -가 들어갈수없다. 이럴경우에는 @RequestParam을 쓰는 것이 적절하다.
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest  //@RestController 선언시 객체가 반환되면 json 으로 변환된다.
    }

    //phone-number ?? -> 사용가능 하이픈이 들어가면 map, @RequestParam 을 사용하자.
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map:Map<String,Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map["phone-number"]
        return map
    }
}