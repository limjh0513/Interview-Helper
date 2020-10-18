package kr.hs.dgsw.juyeop.interview.viewmodel.activity

import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent

class SignUpCompleteViewModel : BaseViewModel() {

    val onCancelEvent = SingleLiveEvent<Unit>()

    fun cancelEvent() {
        onCancelEvent.call()
    }
}