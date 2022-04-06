package kr.hs.dgsw.juyeop.interview.viewmodel.adapter

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseItemViewModel
import kr.hs.dgsw.juyeop.interview.widget.navigator.QuestionItemNavigator

class QuestionItemViewModel : BaseItemViewModel<QuestionItemNavigator>() {

    lateinit var question: Question
    val name = MutableLiveData<String>()

    fun bind(question: Question) {
        this.question = question
        name.value = question.question
    }

    fun replyEvent() {
        getNavigator().replyEvent(question)
    }


    // UI 관련 코드는 ViewModel보다는 View에서, 또 이런거는 xml variable 부분에서 int 변수 하나 만들고 bindingAdapter로 사용해도 되지 않을까요?
    fun getColorResource(category: Int): Int {
        var colorResource = R.color.colorCategory1
        when(category) {
            1 -> colorResource = R.color.colorCategory1
            2 -> colorResource = R.color.colorCategory2
            3 -> colorResource = R.color.colorCategory3
            4 -> colorResource = R.color.colorCategory4
            5 -> colorResource = R.color.colorCategory5
            6 -> colorResource = R.color.colorCategory6
            7 -> colorResource = R.color.colorCategory7
        }
        return colorResource
    }
}