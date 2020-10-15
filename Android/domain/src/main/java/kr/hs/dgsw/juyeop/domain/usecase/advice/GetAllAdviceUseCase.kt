package kr.hs.dgsw.juyeop.domain.usecase.advice

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.BaseUseCase
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.repository.AdviceRepository
import javax.inject.Inject

class GetAllAdviceUseCase @Inject constructor(
    private val adviceRepository: AdviceRepository
): BaseUseCase<Single<List<Advice>>>() {

    override fun buildUseCaseObservable(): Single<List<Advice>> {
        return adviceRepository.getAllAdvice()
    }
}