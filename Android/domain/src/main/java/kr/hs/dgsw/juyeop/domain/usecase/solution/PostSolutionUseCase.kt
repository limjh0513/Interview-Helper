package kr.hs.dgsw.juyeop.domain.usecase.solution

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import javax.inject.Inject

class PostSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
): ParamUseCase<PostSolutionUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return solutionRepository.postSolution(params.postSolutionReqeust)
    }

    data class Params(
        val postSolutionReqeust: PostSolutionReqeust
    )
}