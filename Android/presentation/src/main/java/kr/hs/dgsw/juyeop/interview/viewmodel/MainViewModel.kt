package kr.hs.dgsw.juyeop.interview.viewmodel

import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import kr.hs.dgsw.juyeop.domain.usecase.PostLoginUseCase
import kr.hs.dgsw.juyeop.domain.usecase.PostRegisterUseCase
import kr.hs.dgsw.juyeop.interview.base.BaseViewModel

class MainViewModel(
    private val postLoginUseCase: PostLoginUseCase,
    private val postRegisterUseCase: PostRegisterUseCase
): BaseViewModel() {

    init {
        postLogin()
        postRegister()
    }

    fun postLogin() {
        addDisposable(postLoginUseCase.buildUseCaseObservable(PostLoginUseCase.Params(LoginRequest("kjy13299", "kjy25400"))),
            object : DisposableCompletableObserver() {
                override fun onComplete() {

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun postRegister() {
        addDisposable(postRegisterUseCase.buildUseCaseObservable(PostRegisterUseCase.Params(RegisterRequest("wnduq6392", "wnduq7114", "김주엽", 0))),
            object : DisposableCompletableObserver() {
                override fun onComplete() {

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }
}