package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.network.remote.AuthRemote
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    override val remote: AuthRemote,
    override val cache: Any
): BaseDataSource<AuthRemote, Any>() {

    fun postLogin(loginRequest: LoginRequest): Completable = remote.postLogin(loginRequest)

    fun postRegister(registerRequest: RegisterRequest): Completable = remote.postRegister(registerRequest)
}