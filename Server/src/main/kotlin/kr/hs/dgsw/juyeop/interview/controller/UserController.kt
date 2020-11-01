package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.exception.NotFoundException
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userRepository: UserRepository) {

    @GetMapping
    fun getAllUser(): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("200", "전체 유저 정보 조회를 정상적으로 수행하였습니다.", userRepository.findAll())
    }

    @RequestMapping(path = ["/{userId}"], method = [RequestMethod.GET])
    fun getUser(@PathVariable userId: String): HashMap<String, Any> {
        try {
            val target = userRepository.findById(userId).get()
            return JsonResponse().returnJsonResponse("200", "특정 유저 정보 조회를 정상적으로 수행하였습니다.", target)
        } catch (e : NoSuchElementException) {
            throw NotFoundException("존재하지 않는 유저입니다.")
        }
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handler(error: NotFoundException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("404", error.message.toString(), Unit)
    }
}