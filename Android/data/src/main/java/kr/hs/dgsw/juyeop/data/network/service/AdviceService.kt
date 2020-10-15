package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.entity.AdviceData
import kr.hs.dgsw.juyeop.data.util.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AdviceService {

    @GET("advice")
    fun getAllAdvice(): Single<retrofit2.Response<Response<List<AdviceData>>>>

    @GET("advice/{idx}")
    fun getAdvice(@Path("idx") idx: Int): Single<retrofit2.Response<Response<AdviceData>>>
}