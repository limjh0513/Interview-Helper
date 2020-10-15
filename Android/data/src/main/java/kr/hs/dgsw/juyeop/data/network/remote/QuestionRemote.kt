package kr.hs.dgsw.juyeop.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseRemote
import kr.hs.dgsw.juyeop.data.entity.QuestionData
import kr.hs.dgsw.juyeop.data.network.service.QuestionService

class QuestionRemote(override val service: QuestionService) : BaseRemote<QuestionService>() {

    fun getAllQuestion(): Single<List<QuestionData>> {
        return service.getAllQuestion().map(getResponse())
    }

    fun getQuestion(idx: Int): Single<QuestionData> {
        return service.getQuestion(idx).map(getResponse())
    }
}