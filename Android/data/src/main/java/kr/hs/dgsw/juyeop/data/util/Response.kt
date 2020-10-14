package kr.hs.dgsw.juyeop.data.util

data class Response<T>(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
    val data: T
)