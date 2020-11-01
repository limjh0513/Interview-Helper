package kr.hs.dgsw.juyeop.interview.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.usecase.user.GetAllUserUseCase
import kr.hs.dgsw.juyeop.domain.usecase.user.GetUserUseCase
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.view.adapter.RankItemAdapter

class RankViewModel(
    private val getAllUserUseCase: GetAllUserUseCase
) : BaseViewModel() {

    var userItemList = ArrayList<User>()
    val rankItemAdapter = RankItemAdapter()

    val userCount = MutableLiveData<String>()

    init {
        getAllUser()
        rankItemAdapter.setList(userItemList)
    }

    fun getAllUser() {
        addDisposable(getAllUserUseCase.buildUseCaseObservable(), object: DisposableSingleObserver<List<User>>() {
            override fun onSuccess(userList: List<User>) {
                userCount.value = "총 ${userList.size}명"

                userItemList.addAll(userList)
                userItemList.sortByDescending { user -> user.solution }
                rankItemAdapter.notifyDataSetChanged()
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }
}