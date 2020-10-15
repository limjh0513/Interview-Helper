package kr.hs.dgsw.juyeop.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseRemote
import kr.hs.dgsw.juyeop.data.entity.AdviceData
import kr.hs.dgsw.juyeop.data.network.service.AdviceService

class AdviceRemote (override val service: AdviceService): BaseRemote<AdviceService>() {

    fun getAllAdvice(): Single<List<AdviceData>> {
        return service.getAllAdvice().map(getResponse())
    }

    fun getAdvice(idx: Int): Single<AdviceData> {
        return service.getAdvice(idx).map(getResponse())
    }
}