package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.solution.DeleteSolutionUseCase
import javax.inject.Inject

class MyQuestionReplyViewModelFactory @Inject constructor(
    private val context: Context,
    private val deleteSolutionUseCase: DeleteSolutionUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            DeleteSolutionUseCase::class.java
        ).newInstance(context, deleteSolutionUseCase)
    }
}