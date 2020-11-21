package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.user.GetUserUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val context: Context,
    private val getUserUseCase: GetUserUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            GetUserUseCase::class.java
        ).newInstance(context, getUserUseCase)
    }
}