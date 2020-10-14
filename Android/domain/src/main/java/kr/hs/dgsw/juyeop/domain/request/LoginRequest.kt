package kr.hs.dgsw.juyeop.domain.request

import kr.hs.dgsw.juyeop.domain.util.Utils
import java.security.NoSuchAlgorithmException

class LoginRequest(
    var id: String,
    var pw: String
) {
    init {
        this.pw = Utils.encryptSHA512(pw).toString()
    }
}