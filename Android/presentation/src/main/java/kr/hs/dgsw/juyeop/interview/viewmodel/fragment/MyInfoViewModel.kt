package kr.hs.dgsw.juyeop.interview.viewmodel.fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.usecase.user.GetAllUserUseCase
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager

class MyInfoViewModel(
    private val context: Context,
    private val getAllUserUseCase: GetAllUserUseCase
) : BaseViewModel() {

    val name = MutableLiveData<String>()
    val solutionCount = MutableLiveData<String>()

    val onLogoutEvent = SingleLiveEvent<Unit>()
    val onMySolutionEvent = SingleLiveEvent<Unit>()

    fun getAllUser() {
        addDisposable(getAllUserUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(userList: List<User>) {
                val userId = SharedPreferencesManager.getUserId(context)
                val element = userList.filter { user -> user.id == userId }[0]

                name.value = element.name
                solutionCount.value = "${element.solution}개"
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }

    fun logoutEvent() {
        onLogoutEvent.call()
    }
    fun mySolutionEvent() {
        onMySolutionEvent.call()
    }
}