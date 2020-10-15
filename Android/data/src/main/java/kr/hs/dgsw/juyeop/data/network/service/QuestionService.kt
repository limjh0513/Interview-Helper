package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.entity.QuestionData
import kr.hs.dgsw.juyeop.data.util.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionService {

    @GET("/question")
    fun getAllQuestion(): Single<retrofit2.Response<Response<List<QuestionData>>>>

    @GET("/question/{idx}")
    fun getQuestion(@Path("idx") idx: Int): Single<retrofit2.Response<Response<QuestionData>>>
}