package kr.hs.dgsw.juyeop.data.entity

data class AdviceData(
    val idx: Int,
    val user_id: String,
    val title: String,
    val content: String,
    val advice_at: String
)