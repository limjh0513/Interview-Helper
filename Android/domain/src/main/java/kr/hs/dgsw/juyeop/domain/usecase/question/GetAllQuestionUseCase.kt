package kr.hs.dgsw.juyeop.domain.usecase.question

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.BaseUseCase
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.repository.QuestionRepository
import javax.inject.Inject

class GetAllQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
): BaseUseCase<Single<List<Question>>>() {

    override fun buildUseCaseObservable(): Single<List<Question>> {
        return questionRepository.getAllQuestion()
    }
}