package kr.hs.dgsw.juyeop.domain.entity

data class Auth (
    val id: String,
    val pw: String,
    val name: String,
    val solution: Int
)