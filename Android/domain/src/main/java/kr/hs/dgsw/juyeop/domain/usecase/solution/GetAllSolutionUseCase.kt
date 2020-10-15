package kr.hs.dgsw.juyeop.domain.usecase.solution

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import javax.inject.Inject

class GetAllSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
): ParamUseCase<GetAllSolutionUseCase.Params, Single<List<Solution>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<Solution>> {
        return solutionRepository.getAllSolution(params.userId)
    }

    data class Params(
        val userId: String
    )
}