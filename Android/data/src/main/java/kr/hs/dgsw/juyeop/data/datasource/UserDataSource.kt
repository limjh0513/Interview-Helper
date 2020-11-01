package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.entity.UserData
import kr.hs.dgsw.juyeop.data.network.remote.UserRemote
import javax.inject.Inject

class UserDataSource @Inject constructor(
    override val remote: UserRemote,
    override val cache: Any
): BaseDataSource<UserRemote, Any>() {

    fun getAllUser(): Single<List<UserData>> = remote.getAllUser()

    fun getUser(userId: String): Single<UserData> = remote.getUser(userId)
}