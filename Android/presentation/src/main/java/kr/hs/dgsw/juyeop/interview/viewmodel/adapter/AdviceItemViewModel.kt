package kr.hs.dgsw.juyeop.interview.viewmodel.adapter

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel

class AdviceItemViewModel : BaseViewModel() {

    val name = MutableLiveData<String>()
    val adviceAt = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    fun bind(advice: Advice, userItemList: List<User>) {
        name.value = userItemList.filter { user -> user.id == advice.user_id }[0].name
        adviceAt.value = advice.advice_at
        title.value = advice.title
        content.value = advice.content
    }
}