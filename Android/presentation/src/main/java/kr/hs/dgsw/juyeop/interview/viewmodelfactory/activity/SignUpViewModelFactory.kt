package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostRegisterUseCase
import javax.inject.Inject

class SignUpViewModelFactory @Inject constructor(
    private val context: Context,
    private val postRegisterUseCase: PostRegisterUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            PostRegisterUseCase::class.java
        ).newInstance(context, postRegisterUseCase)
    }
}