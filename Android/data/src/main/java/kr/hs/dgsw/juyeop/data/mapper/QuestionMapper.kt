package kr.hs.dgsw.juyeop.data.mapper

import kr.hs.dgsw.juyeop.data.entity.QuestionData
import kr.hs.dgsw.juyeop.domain.entity.Question

fun QuestionData.toEntity(): Question {
    return Question(
        idx = idx,
        category = category,
        question = question
    )
}