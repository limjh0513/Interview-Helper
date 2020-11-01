package kr.hs.dgsw.juyeop.domain.usecase.user

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.BaseUseCase
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUserUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Single<List<User>>>() {

    override fun buildUseCaseObservable(): Single<List<User>> {
        return userRepository.getAllUser()
    }
}