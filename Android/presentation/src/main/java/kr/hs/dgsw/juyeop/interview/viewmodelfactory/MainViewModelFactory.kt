package kr.hs.dgsw.juyeop.interview.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.PostLoginUseCase
import kr.hs.dgsw.juyeop.domain.usecase.PostRegisterUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val postRegisterUseCase: PostRegisterUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            PostLoginUseCase::class.java,
            PostRegisterUseCase::class.java
        ).newInstance(postLoginUseCase, postRegisterUseCase)
    }
}