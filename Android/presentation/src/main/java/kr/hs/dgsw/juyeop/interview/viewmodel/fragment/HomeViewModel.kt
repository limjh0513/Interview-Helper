package kr.hs.dgsw.juyeop.interview.viewmodel.fragment

import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel

class HomeViewModel : BaseViewModel() {
    fun getTitleResource(category: Int): Int {
        var titleResource = R.string.title_rank
        when(category) {
            0 -> titleResource = R.string.title_rank
            1 -> titleResource = R.string.title_advice
            2 -> titleResource = R.string.title_recruit
            3 -> titleResource = R.string.title_recommand
        }
        return titleResource
    }
}