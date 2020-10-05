package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.model.db.SolutionEntity
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.SolutionRepository
import org.springframework.web.bind.annotation.*
import java.lang.IndexOutOfBoundsException

/**
 * 1. 특정 사용자의 전체 답변 목록 조회 (getAllSolutionByUserId)
 * 2. 특정 사용자의 답변 목록 조회 (getSolutionByUserId)
 * 3. 특정 질문에 대한 답변 생성 (createSolution)
 * 4. 특정 질문에 대한 답변 수정 (updateSolution)
 * 5. 특정 질문에 대한 답변 삭제 (deleteSolution)
 */

@RestController
@RequestMapping("/solution")
class SolutionController(val solutionRepository: SolutionRepository) {

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
            solutionRepository.save(solutionEntity)
            return JsonResponse().returnJsonResponse("200", "면접 질문에 대한 사용자의 답변을 정상적으로 추가하였습니다.", Unit)
        } else {
            return JsonResponse().returnJsonResponse("400", "검증 오류가 발생하였습니다.", Unit)
        }
    }

    @RequestMapping(path = ["/{idx}"], method = [RequestMethod.PUT])
    fun updateSolution(@RequestBody solutionEntity: SolutionEntity, @PathVariable("idx") idx: Int): HashMap<String, Any> {
        if (checkEmpty(solutionEntity)) {
            val target = checkExist(idx)
            if (!target.user_id.isNullOrEmpty()) {
                with(target) {
                    solution_text = solutionEntity.solution_text
                    solution_audio = solutionEntity.solution_audio
                    solution_video = solutionEntity.solution_video

                    solutionRepository.save(target)
                    return JsonResponse().returnJsonResponse("200", "면접 질문에 대한 사용자의 답변을 정상적으로 수정하였습니다.", Unit)
                }
            } else {
                return JsonResponse().returnJsonResponse("404", "존재하지 않는 답변입니다.", Unit)
            }
        } else {
            return JsonResponse().returnJsonResponse("400", "검증 오류가 발생하였습니다.", Unit)
        }
    }

    @RequestMapping(path = ["/{idx}"], method = [RequestMethod.DELETE])
    fun deleteSolution(@PathVariable("idx") idx: Int): HashMap<String, Any> {
        val target = checkExist(idx)
        if (!target.user_id.isNullOrEmpty()) {
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