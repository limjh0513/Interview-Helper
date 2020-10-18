package kr.hs.dgsw.juyeop.interview.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.request.auth.RegisterRequest
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostRegisterUseCase
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent

class SignUpViewModel(
    private val context: Context,
    private val postRegisterUseCase: PostRegisterUseCase
) : BaseViewModel() {

    var nextCount = 0

    val description = MutableLiveData(context.resources.getString(R.string.text_input_name))
    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val idVisibility = MutableLiveData(false)
    val pwVisibility = MutableLiveData(false)

    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onCompleteEvent = SingleLiveEvent<Unit>()

    fun signUpEvent() {
        if (checkEmpty()) {
            val registerRequest = RegisterRequest(id.value.toString(), pw.value.toString(), name.value.toString(), 0)

            addDisposable(postRegisterUseCase.buildUseCaseObservable(PostRegisterUseCase.Params(registerRequest)),
                object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        onCompleteEvent.call()
                    }
                    override fun onError(e: Throwable) {
                        onErrorEvent.value = e
                    }
                })
        } else onEmptyEvent.call()
    }

    fun checkEmpty(): Boolean {
        return !(name.value.isNullOrEmpty() || id.value.isNullOrEmpty() || pw.value.isNullOrEmpty())
    }

    fun nextEvent() {
        when (nextCount) {
            0 -> {
                description.value = context.resources.getString(R.string.text_input_id)
                idVisibility.value = true
                nextCount++
            }
            1 -> {
                description.value = context.resources.getString(R.string.text_input_pw)
                pwVisibility.value = true
                nextCount++
            }
            2 -> {
                description.value = context.resources.getString(R.string.text_check_data)
                nextCount++
            }
            3 -> {
                signUpEvent()
            }
        }
    }
}