package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest

interface AuthRepository {

    fun postLogin(loginRequest: LoginRequest): Completable

    fun postRegister(registerRequest: RegisterRequest): Completable
}