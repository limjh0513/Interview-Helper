package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.question.GetAllQuestionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.solution.GetAllSolutionUseCase
import javax.inject.Inject

class MySolutionViewModelFactory @Inject constructor(
    private val context: Context,
    private val getAllSolutionUseCase: GetAllSolutionUseCase,
    private val getAllQuestionUseCase: GetAllQuestionUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            GetAllSolutionUseCase::class.java,
            GetAllQuestionUseCase::class.java
        ).newInstance(context, getAllSolutionUseCase, getAllQuestionUseCase)
    }
}