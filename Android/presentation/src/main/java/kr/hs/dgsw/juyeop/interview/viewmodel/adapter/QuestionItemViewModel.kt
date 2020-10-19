package kr.hs.dgsw.juyeop.interview.viewmodel.adapter

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel

class QuestionItemViewModel : BaseViewModel() {

    val question = MutableLiveData<String>()

    fun bind(question: Question) {
        this.question.value = question.question
    }
}