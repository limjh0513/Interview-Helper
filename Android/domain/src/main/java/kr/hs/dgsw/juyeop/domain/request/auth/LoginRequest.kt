package kr.hs.dgsw.juyeop.domain.request.auth

import kr.hs.dgsw.juyeop.domain.util.Utils

class LoginRequest(
    var id: String,
    var pw: String
) {
    init {
        this.pw = Utils.encryptSHA512(pw).toString()
    }
}