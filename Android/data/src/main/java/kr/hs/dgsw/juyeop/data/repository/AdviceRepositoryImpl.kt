package kr.hs.dgsw.juyeop.data.repository

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.datasource.AdviceDataSource
import kr.hs.dgsw.juyeop.data.mapper.toEntity
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.repository.AdviceRepository
import javax.inject.Inject

class AdviceRepositoryImpl @Inject constructor(
    private val adviceDataSource: AdviceDataSource
): AdviceRepository {

    override fun getAllAdvice(): Single<List<Advice>> {
        return adviceDataSource.getAllAdvice().map { adviceList -> adviceList.map { it.toEntity() } }
    }

    override fun getAdvice(idx: Int): Single<Advice> {
        return adviceDataSource.getAdvice(idx).map { advice -> advice.toEntity() }
    }
}