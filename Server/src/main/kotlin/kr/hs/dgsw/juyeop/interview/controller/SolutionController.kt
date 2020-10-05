package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.repository.SolutionRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/solution")
class SolutionController(val solutionRepository: SolutionRepository) {

}