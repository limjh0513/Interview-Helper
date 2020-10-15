package kr.hs.dgsw.juyeop.data.entity

data class AuthData(
    val id: String,
    val pw: String,
    val name: String,
    val solution: Int
)