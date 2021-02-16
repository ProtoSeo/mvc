package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

// 이렇게 작성하면 클래스 전체가 지정한 namingstrategy를 따른다.
// 또 다른 방법   ; objectMapper를 bean에 등록할때, application.properties 에 naming 규칙을 적어줄수도있다.
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest (
    var name:String? = null,
    var age:Int? = null,
    var address:String? =null,
    var email:String? =null,

    // rest api 에서는 snake case(phone_number)가 많다. 이럴경우 데이터를 받을 수 없다.
    // objectMapper를 활용하자
    //    @JsonProperty("phone_number")
    var phoneNumber:String?=null
)