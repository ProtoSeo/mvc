package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception/hello")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("hello")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String,String>()
        queryParams.add("name","steve")
        queryParams.add("age","20")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("steve 20")
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String,String>()
        queryParams.add("name","steve")
        queryParams.add("age","5")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams)
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andExpect(
            MockMvcResultMatchers.content().contentType("application/json")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.result_code").value("Fail")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].value").value("5")
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun postTest(){
        val userRequest = UserRequest().apply {
            this.name = "proto"
            this.age = 10
            this.phoneNumber = "010-1234-1231"
            this.address = "dagasdf"
            this.email = "proto@naver.com"
            this.createAt = "2020-10-22 13:00:30"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.name").value("proto")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.age").value("10")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.email").value("proto@naver.com")
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun postFailTest(){
        val userRequest = UserRequest().apply {
            this.name = "proto"
            this.age = -1
            this.phoneNumber = "010-1234-1231"
            this.address = "dagasdf"
            this.email = "proto@naver.com"
            this.createAt = "2020-10-22 13:00:30"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }
}