package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.entity.QuestionData
import kr.hs.dgsw.juyeop.data.network.remote.QuestionRemote
import javax.inject.Inject

class QuestionDataSource @Inject constructor(
    override val remote: QuestionRemote,
    override val cache: Any
) : BaseDataSource<QuestionRemote, Any>() {

    fun getAllQuestion(): Single<List<QuestionData>> = remote.getAllQuestion()

    fun getQuestion(idx: Int): Single<QuestionData> = remote.getQuestion(idx)
}