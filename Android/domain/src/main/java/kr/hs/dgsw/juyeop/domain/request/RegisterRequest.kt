package kr.hs.dgsw.juyeop.domain.request

import kr.hs.dgsw.juyeop.domain.util.Utils

class RegisterRequest(
    var id: String,
    var pw: String,
    var name: String,
    var solution: Int
) {
    init {
        pw = Utils.encryptSHA512(pw).toString()
    }
}