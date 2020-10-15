package kr.hs.dgsw.juyeop.domain.usecase.solution

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import kr.hs.dgsw.juyeop.domain.request.solution.PutSolutionReqeust
import javax.inject.Inject

class PutSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
): ParamUseCase<PutSolutionUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return solutionRepository.putSolution(params.idx, params.putSolutionReqeust)
    }

    data class Params(
        val idx: Int,
        val putSolutionReqeust: PutSolutionReqeust
    )
}