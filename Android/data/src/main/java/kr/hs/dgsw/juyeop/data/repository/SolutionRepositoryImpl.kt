package kr.hs.dgsw.juyeop.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.datasource.SolutionDataSource
import kr.hs.dgsw.juyeop.data.mapper.toEntity
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.request.solution.PutSolutionReqeust
import javax.inject.Inject

class SolutionRepositoryImpl @Inject constructor(
    private val solutionDataSource: SolutionDataSource
): SolutionRepository {

    override fun getAllSolution(userId: String): Single<List<Solution>> {
        return solutionDataSource.getAllSolution(userId).map { solutionList -> solutionList.map { it.toEntity() } }
    }

    override fun getSolution(questionIdx: Int, userId: String): Single<Solution> {
        return solutionDataSource.getSolution(questionIdx, userId).map { solution -> solution.toEntity() }
    }

    override fun postSolution(postSolutionReqeust: PostSolutionReqeust): Completable {
        return solutionDataSource.postSolution(postSolutionReqeust)
    }

    override fun putSolution(idx: Int, putSolutionReqeust: PutSolutionReqeust): Completable {
        return solutionDataSource.putSolution(idx, putSolutionReqeust)
    }

    override fun deleteSolution(idx: Int): Completable {
        return solutionDataSource.deleteSolution(idx)
    }
}