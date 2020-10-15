package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.entity.SolutionData
import kr.hs.dgsw.juyeop.data.util.Response
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.request.solution.PutSolutionReqeust
import retrofit2.http.*

interface SolutionService {

    @GET("solution/{userId}")
    fun getAllSolution(@Path("userId") userId: String): Single<retrofit2.Response<Response<List<SolutionData>>>>

    @GET("solution")
    fun getSolution(@Query("questionIdx") questionIdx: Int, @Query("userId") userId: String): Single<retrofit2.Response<Response<SolutionData>>>

    @POST("solution")
    fun postSolution(@Body postSolutionReqeust: PostSolutionReqeust): Completable

    @PUT("solution/{idx}")
    fun putSolution(@Path("idx") idx: Int, @Body putSolutionReqeust: PutSolutionReqeust): Completable

    @DELETE("solution/{idx}")
    fun deleteSolution(@Path("idx") idx: Int): Completable
}