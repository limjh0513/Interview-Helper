package kr.hs.dgsw.juyeop.interview.viewmodel

import android.content.Context
import kr.hs.dgsw.juyeop.interview.base.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager

class SplashViewModel(
    private val context: Context
) : BaseViewModel() {

    val onNullEvent = SingleLiveEvent<Unit>()
    val onNotNullEvent = SingleLiveEvent<Unit>()

    init {
        if (SharedPreferencesManager.getUserId(context).isNullOrEmpty()) onNullEvent.call()
        else onNotNullEvent.call()
    }
}