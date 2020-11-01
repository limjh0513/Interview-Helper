package kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.user.GetAllUserUseCase
import kr.hs.dgsw.juyeop.domain.usecase.user.GetUserUseCase
import javax.inject.Inject

class RankViewModelFactory @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetAllUserUseCase::class.java
        ).newInstance(getAllUserUseCase)
    }
}