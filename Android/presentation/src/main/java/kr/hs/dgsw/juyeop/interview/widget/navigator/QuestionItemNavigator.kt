package kr.hs.dgsw.juyeop.interview.widget.navigator

import kr.hs.dgsw.juyeop.domain.entity.Question

interface QuestionItemNavigator {
    fun replyEvent(question: Question)
}