package kr.hs.dgsw.juyeop.domain.usecase.question

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
): ParamUseCase<GetQuestionUseCase.Params, Single<Question>>() {

    override fun buildUseCaseObservable(params: Params): Single<Question> {
       return questionRepository.getQuestion(params.idx)
    }

    data class Params(
        val idx: Int
    )
}