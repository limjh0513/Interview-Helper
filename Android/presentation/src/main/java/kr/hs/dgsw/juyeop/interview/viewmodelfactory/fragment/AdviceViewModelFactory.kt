package kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.advice.GetAllAdviceUseCase
import kr.hs.dgsw.juyeop.domain.usecase.user.GetAllUserUseCase
import javax.inject.Inject

class AdviceViewModelFactory @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val getAllAdviceUseCase: GetAllAdviceUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetAllUserUseCase::class.java,
            GetAllAdviceUseCase::class.java
        ).newInstance(getAllUserUseCase, getAllAdviceUseCase)
    }
}