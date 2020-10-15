package kr.hs.dgsw.juyeop.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.datasource.AuthDataSource
import kr.hs.dgsw.juyeop.data.mapper.toEntity
import kr.hs.dgsw.juyeop.domain.entity.Auth
import kr.hs.dgsw.juyeop.domain.repository.AuthRepository
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {

    override fun postLogin(loginRequest: LoginRequest): Single<Auth> {
        return authDataSource.postLogin(loginRequest).map { auth -> auth.toEntity() }
    }

    override fun postRegister(registerRequest: RegisterRequest): Completable {
        return authDataSource.postRegister(registerRequest)
    }
}