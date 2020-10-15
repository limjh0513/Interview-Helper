package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.exception.NotFoundException
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.AdviceRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/advice")
class AdviceController(val adviceRepository: AdviceRepository) {

    @GetMapping
    fun getAllAdvice(): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("200", "전체 조언 조회를 정상적으로 수행하였습니다.", adviceRepository.findAll())
    }

    @RequestMapping(path = ["/{idx}"], method = [RequestMethod.GET])
    fun getAdviceByIdx(@PathVariable("idx") idx: Int): HashMap<String, Any> {
        try {
            val target = adviceRepository.findById(idx).get()
            return JsonResponse().returnJsonResponse("200", "특정 조언 조회를 정상적으로 수행하였습니다.", target)
        } catch (e : NoSuchElementException) {
            throw NotFoundException("존재하지 않는 조언입니다.")
        }
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handler(error: NotFoundException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("404", error.message.toString(), Unit)
    }
}