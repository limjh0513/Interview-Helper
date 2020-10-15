package kr.hs.dgsw.juyeop.domain.usecase.advice

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.BaseUseCase
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.repository.AdviceRepository
import javax.inject.Inject

class GetAdviceUseCase @Inject constructor(
    private val adviceRepository: AdviceRepository
): ParamUseCase<GetAdviceUseCase.Params, Single<Advice>>() {

    override fun buildUseCaseObservable(params: Params): Single<Advice> {
        return adviceRepository.getAdvice(params.idx)
    }

    data class Params(
        val idx: Int
    )
}