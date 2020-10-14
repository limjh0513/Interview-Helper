package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    fun postLogin(@Body loginRequest: LoginRequest): Completable

    @POST("auth/register")
    fun postRegister(@Body registerRequest: RegisterRequest): Completable
}