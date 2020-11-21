package kr.hs.dgsw.juyeop.interview.viewmodel.activity

import android.content.Context
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.usecase.user.GetUserUseCase
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager

class MainViewModel(
    private val context: Context,
    private val getUserUseCase: GetUserUseCase
): BaseViewModel() {

    init {
        setUsersolution()
    }

    fun setUsersolution() {
        val userId = SharedPreferencesManager.getUserId(context)
        addDisposable(getUserUseCase.buildUseCaseObservable(GetUserUseCase.Params(userId!!)),
            object : DisposableSingleObserver<User>() {
                override fun onSuccess(user: User) {
                    SharedPreferencesManager.setUserSolution(context, user.solution)
                }
                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
        })
    }
}