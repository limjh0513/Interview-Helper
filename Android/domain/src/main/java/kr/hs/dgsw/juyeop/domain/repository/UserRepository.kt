package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.entity.User

interface UserRepository {

    fun getAllUser(): Single<List<User>>

    fun getUser(userId: String): Single<User>
}