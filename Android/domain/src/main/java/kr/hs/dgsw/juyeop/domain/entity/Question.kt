package kr.hs.dgsw.juyeop.domain.entity

import java.io.Serializable

data class Question(
    val idx: Int,
    val category: Int,
    val question: String
): Serializable