package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.entity.AuthData
import kr.hs.dgsw.juyeop.data.network.remote.AuthRemote
import kr.hs.dgsw.juyeop.domain.request.auth.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.auth.RegisterRequest
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    override val remote: AuthRemote,
    override val cache: Any
): BaseDataSource<AuthRemote, Any>() {

    fun postLogin(loginRequest: LoginRequest): Single<AuthData> = remote.postLogin(loginRequest)

    fun postRegister(registerRequest: RegisterRequest): Single<String> = remote.postRegister(registerRequest)
}