package kr.hs.dgsw.juyeop.data.network.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseRemote
import kr.hs.dgsw.juyeop.data.entity.SolutionData
import kr.hs.dgsw.juyeop.data.network.service.SolutionService
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.request.solution.PutSolutionReqeust

class SolutionRemote(override val service: SolutionService) : BaseRemote<SolutionService>() {

    fun getAllSolution(userId: String): Single<List<SolutionData>> {
        return service.getAllSolution(userId).map(getResponse())
    }

    fun getSolution(questionIdx: Int, userId: String): Single<SolutionData> {
        return service.getSolution(questionIdx, userId).map(getResponse())
    }

    fun postSolution(postSolutionReqeust: PostSolutionReqeust): Completable {
        return service.postSolution(postSolutionReqeust)
    }

    fun putSolution(idx: Int, putSolutionReqeust: PutSolutionReqeust): Completable {
        return service.putSolution(idx, putSolutionReqeust)
    }

    fun deleteSolution(idx: Int): Completable {
        return service.deleteSolution(idx)
    }
}