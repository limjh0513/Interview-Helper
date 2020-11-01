package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.entity.UserData
import kr.hs.dgsw.juyeop.data.util.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/user")
    fun getAllUser(): Single<retrofit2.Response<Response<List<UserData>>>>

    @GET("/user/{userId}")
    fun getUser(@Path("userId") userId: String): Single<retrofit2.Response<Response<UserData>>>
}