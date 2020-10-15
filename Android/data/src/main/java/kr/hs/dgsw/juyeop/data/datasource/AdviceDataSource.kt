package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.entity.AdviceData
import kr.hs.dgsw.juyeop.data.network.remote.AdviceRemote
import javax.inject.Inject

class AdviceDataSource @Inject constructor(
    override val remote: AdviceRemote,
    override val cache: Any
): BaseDataSource<AdviceRemote, Any>() {

    fun getAllAdvice(): Single<List<AdviceData>> {
        return remote.getAllAdvice()
    }

    fun getAdvice(idx: Int): Single<AdviceData> {
        return remote.getAdvice(idx)
    }
}