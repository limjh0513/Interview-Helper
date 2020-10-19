package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.solution.PostSolutionUseCase
import javax.inject.Inject

class QuestionReplyViewModelFactory @Inject constructor(
    private val context: Context,
    private val postSolutionUseCase: PostSolutionUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            PostSolutionUseCase::class.java
        ).newInstance(context, postSolutionUseCase)
    }
}