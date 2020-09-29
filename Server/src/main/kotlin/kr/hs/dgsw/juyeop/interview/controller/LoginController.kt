package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.model.db.UserEntity
import kr.hs.dgsw.juyeop.interview.model.request.LoginRequest
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/auth/login")
class LoginController(val userRepository: UserRepository) {

    @PostMapping
    fun loginUser(@RequestBody loginRequest: LoginRequest): HashMap<String, Any> {
        if (checkEmpty(loginRequest)) {
            val target = checkExistUser(loginRequest)
            if (!target.id.isNullOrEmpty()) {
                return JsonResponse().returnJsonResponse("200", "로그인을 정상적으로 수행하였습니다.", target)
            } else {
                return JsonResponse().returnJsonResponse("401", "아이디 또는 비밀번호가 일치하지 않습니다.", Unit)
            }
        } else {
            return JsonResponse().returnJsonResponse("404", "검증 오류가 발생하였습니다.", Unit)
        }
    }

    fun checkEmpty(loginRequest: LoginRequest): Boolean {
        with(loginRequest) {
            return !(id.isNullOrEmpty() || pw.isNullOrEmpty())
        }
    }

    fun checkExistUser(loginRequest: LoginRequest): UserEntity {
        try {
            val target = userRepository.findById(loginRequest.id!!).get()

            if (target.pw == loginRequest.pw) return target
            else return UserEntity()
        } catch (e : NoSuchElementException) {
            return UserEntity()
        }
    }
}