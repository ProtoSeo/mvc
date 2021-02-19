package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

// 이렇게 작성하면 클래스 전체가 지정한 namingstrategy를 따른다.
// 또 다른 방법   ; objectMapper를 bean에 등록할때, application.properties 에 naming 규칙을 적어줄수도있다.
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest (

    @field:NotEmpty
    @field:Size(min=2,max=8)
    var name:String? = null,

    @field:PositiveOrZero   //0 < 숫자를 검증, 0도 포
    var age:Int? = null,

    @field:Email    //email 양식
    var email:String? =null,

    @field:NotBlank    // 공백을 검
    var address:String? =null,

    // rest api 에서는 snake case(phone_number)가 많다. 이럴경우 데이터를 받을 수 없다.
    // objectMapper를 활용하자
    //    @JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")  //정규식 검
    var phoneNumber:String?=null,

    @field:StringFormatDateTime(pattern="yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createAt:String?=null   // ex) yyyy-MM-dd HH:mm:ss 이렇게 까다로운 것은 직접 만들어서 해결 가능하다.
){
    // 기존의 어노테이션으로 검증을 하기 힘들때 만들어서 사용
//    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 이어야 합니다.")
//    private fun isValidateCreateAt():Boolean{   //정상 : true, 비정상 : false
//        return try{
//            LocalDateTime.parse(this.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            true
//        }catch(e:Exception){
//            false
//        }
//    }
}

/*  이런 복잡한 JSON을 주고 받아야한다면 어떻게 해야할까?
{
    "result":{
        "result_code":"OK",
        "result_message" : "성공",
    },
    "description":"~~~",
    "user":[
        {"name":"",
         "age":"",
         "email":"",
         "phoneNumber":"
        },
        {"name":"",
         "age":"",
         "email":""
         "phoneNumber":"
        },
        {"name":"",
         "age":"",
         "email":""
         "phoneNumber":"
        }
    ]
}
*/