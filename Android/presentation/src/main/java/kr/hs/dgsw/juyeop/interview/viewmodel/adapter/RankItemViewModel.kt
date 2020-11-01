package kr.hs.dgsw.juyeop.interview.viewmodel.adapter

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel

class RankItemViewModel : BaseViewModel() {

    val rank = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val solution = MutableLiveData<String>()

    fun bind(user: User, position: Int) {
        if (user.solution == 0) rank.value = "-"
        else rank.value = position.toString()

        name.value = user.name
        solution.value = "면접 답변 ${user.solution}개"
    }
}