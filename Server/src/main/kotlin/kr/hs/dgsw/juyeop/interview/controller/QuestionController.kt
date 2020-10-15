package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.exception.NotFoundException
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.QuestionRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/question")
class QuestionController(val questionRepository: QuestionRepository) {

    @GetMapping
    fun getAllQuestion(): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("200", "전체 면접 질문 조회를 정상적으로 수행하였습니다.", questionRepository.findAll())
    }

    @RequestMapping(path = ["/{idx}"], method = [RequestMethod.GET])
    fun getQuestionByIdx(@PathVariable("idx") idx: Int): HashMap<String, Any> {
        try {
            val target = questionRepository.findById(idx).get()
            return JsonResponse().returnJsonResponse("200", "특정 면접 질문 조회를 정상적으로 수행하였습니다.", target)
        } catch (e : NoSuchElementException) {
            throw NotFoundException("존재하지 않는 면접 질문입니다.")
        }
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handler(error: NotFoundException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("404", error.message.toString(), Unit)
    }
}