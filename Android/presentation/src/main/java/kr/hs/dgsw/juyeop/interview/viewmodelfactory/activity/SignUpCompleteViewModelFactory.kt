package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SignUpCompleteViewModelFactory @Inject constructor(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}