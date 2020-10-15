package kr.hs.dgsw.juyeop.interview.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.advice.GetAdviceUseCase
import kr.hs.dgsw.juyeop.domain.usecase.advice.GetAllAdviceUseCase
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostLoginUseCase
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostRegisterUseCase
import kr.hs.dgsw.juyeop.domain.usecase.question.GetAllQuestionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.question.GetQuestionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.solution.*
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val postRegisterUseCase: PostRegisterUseCase,
    private val getAllQuestionUseCase: GetAllQuestionUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val getAllAdviceUseCase: GetAllAdviceUseCase,
    private val getAdviceUseCase: GetAdviceUseCase,
    private val getAllSolutionUseCase: GetAllSolutionUseCase,
    private val getSolutionUseCase: GetSolutionUseCase,
    private val postSolutionUseCase: PostSolutionUseCase,
    private val putSolutionUseCase: PutSolutionUseCase,
    private val deleteSolutionUseCase: DeleteSolutionUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            PostLoginUseCase::class.java,
            PostRegisterUseCase::class.java,
            GetAllQuestionUseCase::class.java,
            GetQuestionUseCase::class.java,
            GetAllAdviceUseCase::class.java,
            GetAdviceUseCase::class.java,
            GetAllSolutionUseCase::class.java,
            GetSolutionUseCase::class.java,
            PostSolutionUseCase::class.java,
            PutSolutionUseCase::class.java,
            DeleteSolutionUseCase::class.java
        ).newInstance(postLoginUseCase, postRegisterUseCase, getAllQuestionUseCase, getQuestionUseCase,
                      getAllAdviceUseCase, getAdviceUseCase, getAllSolutionUseCase, getSolutionUseCase, postSolutionUseCase,
                      putSolutionUseCase, deleteSolutionUseCase)
    }
}