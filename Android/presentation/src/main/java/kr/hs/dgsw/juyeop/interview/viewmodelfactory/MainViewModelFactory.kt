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

class MainViewModelFactory @Inject constructor(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}