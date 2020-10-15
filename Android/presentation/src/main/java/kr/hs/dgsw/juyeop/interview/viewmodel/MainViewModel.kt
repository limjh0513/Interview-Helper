package kr.hs.dgsw.juyeop.interview.viewmodel

import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.entity.Auth
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import kr.hs.dgsw.juyeop.domain.usecase.advice.GetAdviceUseCase
import kr.hs.dgsw.juyeop.domain.usecase.advice.GetAllAdviceUseCase
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostLoginUseCase
import kr.hs.dgsw.juyeop.domain.usecase.auth.PostRegisterUseCase
import kr.hs.dgsw.juyeop.domain.usecase.question.GetAllQuestionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.question.GetQuestionUseCase
import kr.hs.dgsw.juyeop.interview.base.BaseViewModel

class MainViewModel(
    private val postLoginUseCase: PostLoginUseCase,
    private val postRegisterUseCase: PostRegisterUseCase,
    private val getAllQuestionUseCase: GetAllQuestionUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val getAllAdviceUseCase: GetAllAdviceUseCase,
    private val getAdviceUseCase: GetAdviceUseCase
): BaseViewModel() {

    init {
        postLogin()
        postRegister()

        getAllQuestion()
        getQuestion()

        getAllAdvice()
        getAdvice()
    }

    fun postLogin() {
        addDisposable(postLoginUseCase.buildUseCaseObservable(PostLoginUseCase.Params(LoginRequest("kjy13299", "kjy25400"))),
            object : DisposableSingleObserver<Auth>() {
                override fun onSuccess(t: Auth) {

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

    fun getAllQuestion() {
        addDisposable(getAllQuestionUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<Question>>() {
                override fun onSuccess(t: List<Question>) {

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }
    fun getQuestion() {
        addDisposable(getQuestionUseCase.buildUseCaseObservable(GetQuestionUseCase.Params(1)),
            object : DisposableSingleObserver<Question>() {
                override fun onSuccess(t: Question) {

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun getAllAdvice() {
        addDisposable(getAllAdviceUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<Advice>>() {
                override fun onSuccess(t: List<Advice>) {

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }
    fun getAdvice() {
        addDisposable(getAdviceUseCase.buildUseCaseObservable(GetAdviceUseCase.Params(1)),
        object : DisposableSingleObserver<Advice>() {
            override fun onSuccess(t: Advice) {

            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }
}