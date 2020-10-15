package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.entity.Question

interface QuestionRepository {

    fun getAllQuestion(): Single<List<Question>>

    fun getQuestion(idx: Int): Single<Question>
}
