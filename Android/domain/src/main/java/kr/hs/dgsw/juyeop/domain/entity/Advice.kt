package kr.hs.dgsw.juyeop.domain.entity

data class Advice (
    val idx: Int,
    val user_id: String,
    val title: String,
    val content: String,
    val advice_at: String
)