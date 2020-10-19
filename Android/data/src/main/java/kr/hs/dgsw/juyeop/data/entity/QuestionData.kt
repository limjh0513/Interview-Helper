package kr.hs.dgsw.juyeop.data.entity

import java.io.Serializable

data class QuestionData(
    val idx: Int,
    val category: Int,
    val question: String
): Serializable