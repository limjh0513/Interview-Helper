package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.entity.SolutionData
import kr.hs.dgsw.juyeop.data.network.remote.SolutionRemote
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.request.solution.PutSolutionReqeust
import javax.inject.Inject

class SolutionDataSource @Inject constructor(
    override val remote: SolutionRemote,
    override val cache: Any
): BaseDataSource<SolutionRemote, Any>() {

    fun getAllSolution(userId: String): Single<List<SolutionData>> {
        return remote.getAllSolution(userId)
    }

    fun getSolution(questionIdx: Int, userId: String): Single<SolutionData> {
        return remote.getSolution(questionIdx, userId)
    }

    fun postSolution(postSolutionReqeust: PostSolutionReqeust): Completable {
        return remote.postSolution(postSolutionReqeust)
    }

    fun putSolution(idx: Int, putSolutionReqeust: PutSolutionReqeust): Completable {
        return remote.putSolution(idx, putSolutionReqeust)
    }

    fun deleteSolution(idx: Int): Completable {
        return remote.deleteSolution(idx)
    }
}