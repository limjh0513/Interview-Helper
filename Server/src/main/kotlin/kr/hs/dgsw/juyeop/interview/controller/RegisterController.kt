package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.model.db.UserEntity
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth/register")
class RegisterController(val userRepository: UserRepository) {

    @PostMapping
    fun registerUser(@RequestBody userEntity: UserEntity): HashMap<String, Any> {
        if (checkEmpty(userEntity)) {
            if (checkOverapping(userEntity.id!!)) {
                userRepository.save(userEntity)
                return JsonResponse().returnJsonResponse("200", "회원가입을 정상적으로 수행하였습니다.", Unit)
            } else {
                return JsonResponse().returnJsonResponse("409", "이미 해당 아이디로 가입된 사용자가 존재합니다.", Unit)
            }
        } else {
            return JsonResponse().returnJsonResponse("400", "검증 오류가 발생하였습니다.", Unit)
        }
    }

    fun checkEmpty(userEntity: UserEntity): Boolean {
        with(userEntity) {
            return !(id.isNullOrEmpty() || pw.isNullOrEmpty() || name.isNullOrEmpty() || solution == null)
        }
    }

    fun checkOverapping(id: String): Boolean {
        try {
            val target = userRepository.findById(id).get()
            return false;
        } catch (e : NoSuchElementException) {
            return true;
        }
    }
}