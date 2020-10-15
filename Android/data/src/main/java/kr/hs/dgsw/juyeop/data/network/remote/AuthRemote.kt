package kr.hs.dgsw.juyeop.data.network.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseRemote
import kr.hs.dgsw.juyeop.data.entity.AuthData
import kr.hs.dgsw.juyeop.data.network.service.AuthService
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest

class AuthRemote(override val service: AuthService) : BaseRemote<AuthService>() {

    fun postLogin(loginRequest: LoginRequest): Single<AuthData> {
        return service.postLogin(loginRequest).map(getResponse())
    }

    fun postRegister(registerRequest: RegisterRequest): Completable {
        return service.postRegister(registerRequest)
    }
}