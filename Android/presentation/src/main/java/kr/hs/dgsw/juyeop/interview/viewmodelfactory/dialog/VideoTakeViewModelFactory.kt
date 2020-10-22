package kr.hs.dgsw.juyeop.interview.viewmodelfactory.dialog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class VideoTakeViewModelFactory @Inject constructor(
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java
        ).newInstance(context)
    }
}