package kr.hs.dgsw.juyeop.domain.usecase.solution

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import javax.inject.Inject

class DeleteSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
): ParamUseCase<DeleteSolutionUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return solutionRepository.deleteSolution(params.idx)
    }

    data class Params(
        val idx: Int
    )
}