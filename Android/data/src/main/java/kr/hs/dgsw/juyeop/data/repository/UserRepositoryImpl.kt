package kr.hs.dgsw.juyeop.data.repository

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.datasource.UserDataSource
import kr.hs.dgsw.juyeop.data.mapper.toEntity
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {

    override fun getAllUser(): Single<List<User>> {
        return userDataSource.getAllUser().map { userList -> userList.map { it.toEntity() } }
    }

    override fun getUser(userId: String): Single<User> {
        return userDataSource.getUser(userId).map { user -> user.toEntity() }
    }
}