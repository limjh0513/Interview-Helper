package kr.hs.dgsw.juyeop.interview

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 예외 처리 기준 정리 - 2020.10.04
 * GET 메소드 (200, 404)
 * POST 메소드 (200, 400, 401, 409)
 */

@RestController
class InterviewController {
    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    fun index(): String = "Welcome to visit Interview Spring Boot Server!"
}