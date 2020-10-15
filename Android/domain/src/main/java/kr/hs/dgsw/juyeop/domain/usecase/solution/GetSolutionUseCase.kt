package kr.hs.dgsw.juyeop.domain.usecase.solution

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import javax.inject.Inject

class GetSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
): ParamUseCase<GetSolutionUseCase.Params, Single<Solution>>() {

    override fun buildUseCaseObservable(params: Params): Single<Solution> {
        return solutionRepository.getSolution(params.questionIdx, params.userId)
    }

    data class Params(
        val questionIdx: Int,
        val userId: String
    )
}