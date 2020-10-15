package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.request.solution.PutSolutionReqeust

interface SolutionRepository {

    fun getAllSolution(userId: String): Single<List<Solution>>

    fun getSolution(questionIdx: Int, userId: String): Single<Solution>

    fun postSolution(postSolutionReqeust: PostSolutionReqeust): Completable

    fun putSolution(idx: Int, putSolutionReqeust: PutSolutionReqeust): Completable

    fun deleteSolution(idx: Int): Completable
}