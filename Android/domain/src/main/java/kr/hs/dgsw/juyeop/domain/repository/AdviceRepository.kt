package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.entity.Advice

interface AdviceRepository {

    fun getAllAdvice(): Single<List<Advice>>

    fun getAdvice(idx: Int): Single<Advice>
}