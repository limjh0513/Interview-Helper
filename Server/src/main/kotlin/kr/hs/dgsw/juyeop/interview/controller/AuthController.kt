package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.exception.BadRequestException
import kr.hs.dgsw.juyeop.interview.exception.UnauthorizedException
import kr.hs.dgsw.juyeop.interview.exception.ConflictException
import kr.hs.dgsw.juyeop.interview.model.db.AuthEntity
import kr.hs.dgsw.juyeop.interview.model.request.LoginRequest
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.AuthRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class AuthController(val authRepository: AuthRepository) {

    @PostMapping
    @RequestMapping("/auth/login")
    fun loginUser(@RequestBody loginRequest: LoginRequest): HashMap<String, Any> {
        if (checkLoginEmpty(loginRequest)) {
            val target = checkExist(loginRequest)
            if (!target.id.isNullOrEmpty()) {
                return JsonResponse().returnJsonResponse("200", "로그인을 정상적으로 수행하였습니다.", target)
            } else {
                throw UnauthorizedException("아이디 또는 비밀번호가 일치하지 않습니다.")
            }
        } else {
            throw BadRequestException("검증 오류가 발생하였습니다")
        }
    }

    @PostMapping
    @RequestMapping("/auth/register")
    fun registerUser(@RequestBody authEntity: AuthEntity): HashMap<String, Any> {
        if (checkRegisterEmpty(authEntity)) {
            if (checkOverapping(authEntity.id!!)) {
                authRepository.save(authEntity)
                return JsonResponse().returnJsonResponse("200", "회원가입을 정상적으로 수행하였습니다.", Unit)
            } else {
                throw ConflictException("이미 해당 아이디로 가입된 사용자가 존재합니다.")
            }
        } else {
            throw BadRequestException("검증 오류가 발생하였습니다.")
        }
    }

    fun checkLoginEmpty(loginRequest: LoginRequest): Boolean {
        with(loginRequest) {
            return !(id.isNullOrEmpty() || pw.isNullOrEmpty())
        }
    }
    fun checkExist(loginRequest: LoginRequest): AuthEntity {
        try {
            val target = authRepository.findById(loginRequest.id!!).get()

            if (target.pw == loginRequest.pw) return target
            else return AuthEntity()
        } catch (e : NoSuchElementException) {
            return AuthEntity()
        }
    }

    fun checkRegisterEmpty(authEntity: AuthEntity): Boolean {
        with(authEntity) {
            return !(id.isNullOrEmpty() || pw.isNullOrEmpty() || name.isNullOrEmpty() || solution == null)
        }
    }
    fun checkOverapping(id: String): Boolean {
        try {
            val target = authRepository.findById(id).get()
            return false
        } catch (e : NoSuchElementException) {
            return true
        }
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handelr(error: BadRequestException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("400", error.message.toString(), Unit)
    }
    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handler(error: UnauthorizedException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("401", error.message.toString(), Unit)
    }
    @ExceptionHandler(ConflictException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handler(error: ConflictException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("409", error.message.toString(), Unit)
    }
}