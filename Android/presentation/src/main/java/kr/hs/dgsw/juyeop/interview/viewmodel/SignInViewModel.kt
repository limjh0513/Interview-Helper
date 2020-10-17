package kr.hs.dgsw.juyeop.interview.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.Auth
import kr.hs.dgsw.juyeop.domain.request.auth.LoginRequest
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostLoginUseCase
import kr.hs.dgsw.juyeop.interview.base.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager

class SignInViewModel(
    private val context: Context,
    private val postLoginUseCase: PostLoginUseCase
) : BaseViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val onSuccessEvent = SingleLiveEvent<Unit>()

    fun signInEvent() {
        val loginRequest = LoginRequest(id.value.toString(), pw.value.toString())

        addDisposable(postLoginUseCase.buildUseCaseObservable(PostLoginUseCase.Params(loginRequest)),
        object : DisposableSingleObserver<Auth>() {
            override fun onSuccess(auth: Auth) {
                SharedPreferencesManager.setUserId(context,auth.id)
                onSuccessEvent.call()
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }
}