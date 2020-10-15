package kr.hs.dgsw.juyeop.data.repository

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.datasource.QuestionDataSource
import kr.hs.dgsw.juyeop.data.mapper.toEntity
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val questionDataSource: QuestionDataSource
): QuestionRepository {

    override fun getAllQuestion(): Single<List<Question>> {
       return questionDataSource.getAllQuestion().map { questionList -> questionList.map { it.toEntity() } }
    }

    override fun getQuestion(idx: Int): Single<Question> {
        return questionDataSource.getQuestion(idx).map { question -> question.toEntity() }
    }
}