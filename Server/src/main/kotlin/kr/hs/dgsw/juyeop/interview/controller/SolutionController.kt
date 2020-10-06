package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.model.db.AuthEntity
import kr.hs.dgsw.juyeop.interview.model.db.SolutionEntity
import kr.hs.dgsw.juyeop.interview.model.request.SolutionRequest
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.AuthRepository
import kr.hs.dgsw.juyeop.interview.repository.SolutionRepository
import org.springframework.web.bind.annotation.*
import java.lang.IndexOutOfBoundsException

@RestController
@RequestMapping("/solution")
class SolutionController(val solutionRepository: SolutionRepository, val authRepository: AuthRepository) {

    @RequestMapping(path = ["/{userId}"], method = [RequestMethod.GET])
    fun getAllSolutionByUserId(@PathVariable("userId") userId: String): HashMap<String, Any> {
        val targetList = solutionRepository.findAll()
        val userSolutionList: List<SolutionEntity>

        userSolutionList = targetList.filter { target ->
            target.user_id.equals(userId)
        }

        if (userSolutionList.isNullOrEmpty()) return JsonResponse().returnJsonResponse("404", "전체 면접 질문에 대한 사용자의 답변이 존재하지 않습니다.", Unit)
        else return JsonResponse().returnJsonResponse("200", "전체 면접 질문에 대한 사용자의 답변 조회를 정상적으로 수행하였습니다.", userSolutionList)
    }

    @GetMapping
    fun getSolutionByUserId(@RequestParam questionIdx: Int, @RequestParam userId: String): HashMap<String, Any> {
        val targetList = solutionRepository.findAll()
        val userSolution: SolutionEntity

        try {
            userSolution = targetList.filter { target ->
                target.question_idx!!.equals(questionIdx) && target.user_id.equals(userId)
            }[0]
            return JsonResponse().returnJsonResponse("200", "면접 질문에 대한 사용자의 답변 조회를 정상적으로 수행하였습니다.", userSolution)
        } catch (e: IndexOutOfBoundsException) {
            return JsonResponse().returnJsonResponse("404", "면접 질문에 대한 사용자의 답변이 존재하지 않습니다.", Unit)
        }
    }

    @PostMapping
    fun createSolution(@RequestBody solutionEntity: SolutionEntity): HashMap<String, Any> {
        if (checkEmpty(solutionEntity)) {
            val targetList = authRepository.findAll()
            val userAuth: AuthEntity

            userAuth = targetList.filter { target ->
                target.id.equals(solutionEntity.user_id)
            }[0]
            userAuth.solution = (userAuth.solution!! + 1)

            authRepository.save(userAuth)
            solutionRepository.save(solutionEntity)

            return JsonResponse().returnJsonResponse("200", "면접 질문에 대한 사용자의 답변을 정상적으로 추가하였습니다.", Unit)
        } else {
            return JsonResponse().returnJsonResponse("400", "검증 오류가 발생하였습니다.", Unit)
        }
    }

    @RequestMapping(path = ["/{idx}"], method = [RequestMethod.PUT])
    fun updateSolution(@RequestBody solutionRequest: SolutionRequest, @PathVariable("idx") idx: Int): HashMap<String, Any> {
        val target = checkExist(idx)
        if (!target.user_id.isNullOrEmpty()) {
            with(target) {
                solution_text = solutionRequest.solution_text
                solution_audio = solutionRequest.solution_audio
                solution_video = solutionRequest.solution_video

                solutionRepository.save(target)
                return JsonResponse().returnJsonResponse("200", "면접 질문에 대한 사용자의 답변을 정상적으로 수정하였습니다.", Unit)
            }
        } else {
            return JsonResponse().returnJsonResponse("404", "존재하지 않는 답변입니다.", Unit)
        }
    }

    @RequestMapping(path = ["/{idx}"], method = [RequestMethod.DELETE])
    fun deleteSolution(@PathVariable("idx") idx: Int): HashMap<String, Any> {
        val target = checkExist(idx)
        if (!target.user_id.isNullOrEmpty()) {
            val targetList = authRepository.findAll()
            val userAuth: AuthEntity

            userAuth = targetList.filter {
                it.id.equals(target.user_id)
            }[0]
            userAuth.solution = (userAuth.solution!! - 1)

            authRepository.save(userAuth)
            solutionRepository.deleteById(idx)

            return JsonResponse().returnJsonResponse("200", "면접 질문에 대한 사용자의 답변을 정상적으로 삭제하였습니다.", Unit)
        } else {
            return JsonResponse().returnJsonResponse("404", "존재하지 않는 답변입니다.", Unit)
        }
    }

    fun checkEmpty(solutionEntity: SolutionEntity): Boolean {
        with(solutionEntity) {
            return !(user_id.isNullOrEmpty() || question_idx == null || solution_at.isNullOrEmpty())
        }
    }
    fun checkExist(idx: Int): SolutionEntity{
        try {
            val target = solutionRepository.findById(idx).get()
            return target
        } catch (e: NoSuchElementException) {
            return SolutionEntity()
        }
    }
}