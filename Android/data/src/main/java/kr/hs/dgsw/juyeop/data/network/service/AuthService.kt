package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.entity.AuthData
import kr.hs.dgsw.juyeop.data.util.Response
import kr.hs.dgsw.juyeop.domain.request.auth.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.auth.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    fun postLogin(@Body loginRequest: LoginRequest): Single<retrofit2.Response<Response<AuthData>>>

    @POST("auth/register")
    fun postRegister(@Body registerRequest: RegisterRequest): Completable
}