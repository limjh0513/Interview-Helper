package kr.hs.dgsw.juyeop.interview

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class InterviewController {
    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    fun index(): String = "Welcome to visit Interview Spring Boot Server!"
}