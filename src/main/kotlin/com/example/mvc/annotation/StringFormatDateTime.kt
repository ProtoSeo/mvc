package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
// field, getter, setter에 대해서는 위에 명시된 validator로 검증하겠다
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME) //런타임에만 활용할 수 있도록
@MustBeDocumented   //반드시 적어야함
annotation class StringFormatDateTime(
    val pattern:String = "yyyy-MM-dd HH:mm:ss",
    val message:String = "시간형식이 유효하지 않습니다.",
    // 아래부분은 default로 들어
    val groups:Array<KClass<*>> = [],
    val payload:Array<KClass<out Payload>> = []
)