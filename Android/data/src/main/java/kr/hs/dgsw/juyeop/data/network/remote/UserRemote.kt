package kr.hs.dgsw.juyeop.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseRemote
import kr.hs.dgsw.juyeop.data.entity.UserData
import kr.hs.dgsw.juyeop.data.network.service.UserService

class UserRemote(override val service: UserService): BaseRemote<UserService>() {

    fun getAllUser(): Single<List<UserData>> {
        return service.getAllUser().map(getResponse())
    }

    fun getUser(userId: String): Single<UserData> {
        return service.getUser(userId).map(getResponse())
    }
}