package kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.user.GetAllUserUseCase
import javax.inject.Inject

class MyInfoViewModelFactory @Inject constructor(
    private val context: Context,
    private val getAllUserUseCase: GetAllUserUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            GetAllUserUseCase::class.java
        ).newInstance(context, getAllUserUseCase)
    }
}