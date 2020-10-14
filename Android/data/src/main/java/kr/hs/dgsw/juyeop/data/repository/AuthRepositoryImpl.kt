package kr.hs.dgsw.juyeop.data.repository

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.data.datasource.AuthDataSource
import kr.hs.dgsw.juyeop.domain.repository.AuthRepository
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {

    override fun postLogin(loginRequest: LoginRequest): Completable {
        return authDataSource.postLogin(loginRequest)
    }

    override fun postRegister(registerRequest: RegisterRequest): Completable {
        return authDataSource.postRegister(registerRequest)
    }
}