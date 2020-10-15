package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.entity.Auth
import kr.hs.dgsw.juyeop.domain.request.auth.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.auth.RegisterRequest

interface AuthRepository {

    fun postLogin(loginRequest: LoginRequest): Single<Auth>

    fun postRegister(registerRequest: RegisterRequest): Completable
}