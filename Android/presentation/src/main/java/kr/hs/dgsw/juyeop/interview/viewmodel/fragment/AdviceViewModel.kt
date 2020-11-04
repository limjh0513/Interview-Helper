package kr.hs.dgsw.juyeop.interview.viewmodel.fragment

import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.usecase.advice.GetAllAdviceUseCase
import kr.hs.dgsw.juyeop.domain.usecase.user.GetAllUserUseCase
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.view.adapter.AdviceItemAdapter

class AdviceViewModel(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val getAllAdviceUseCase: GetAllAdviceUseCase
) : BaseViewModel() {

    var userItemList = ArrayList<User>()
    var adviceItemList = ArrayList<Advice>()
    var adviceItemAdatper = AdviceItemAdapter()

    init {
        adviceItemAdatper.setData(adviceItemList, userItemList)
        getAllUser()
    }

    fun getAllUser() {
        addDisposable(getAllUserUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(userList: List<User>) {
                userItemList.addAll(userList)
                getAllAdvice()
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }

    fun getAllAdvice() {
        addDisposable(getAllAdviceUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<List<Advice>>() {
            override fun onSuccess(adviceList: List<Advice>) {
                adviceItemList.addAll(adviceList)
                adviceItemList.sortByDescending { advice -> advice.advice_at }
                adviceItemAdatper.notifyDataSetChanged()
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }
}